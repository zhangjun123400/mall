package com.zhangjun.mall.utils;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @Author zhangjun
 * @Date 2025/4/28 02:49
 * @Version 1.0
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    //有效期为一个小时
    @Value("${jwt.expiration}")
    public Long expiration;

    //设置密钥明文(盐)
    @Value("${jwt.secret}")
    public String secert;


    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private SecretKey SECRET_KEY;

    /**
     * 初始化密钥（自动处理密钥安全性校验）
     */
    @PostConstruct
    protected void init() {
        // 将原始密钥转换为满足 HMAC-SHA256 长度要求（≥256位）
        byte[] keyBytes = secert.getBytes(StandardCharsets.UTF_8);
        keyBytes = Arrays.copyOf(keyBytes, 32); // 自动填充/截断至256位（32字节）
        SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }



    //生成令牌
    /**
     * 生成UUID格式的JWT ID
     *
     */
    public String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 生成jwt
     * @param subject token中要存储的数据（json格式）
     *
     */
    public String createJWT(String subject)
    {
        return  createJWT(subject,expiration);//设置过期时间
    }

    //生成JWT的业务逻辑代码
    private String createJWT(String subject, Long ttlMillis)
    {

        long nowMillis = System.currentTimeMillis();//获取系统当前的时间戳

        long expMillis = nowMillis + ttlMillis*1000;

        return Jwts.builder()
                .id(getUUID())  //唯一的ID
                .subject(subject)  //主题 可以是JSON数据
                .issuer("zhangjun") //签发者
                .issuedAt(new Date())   //签发时间
                .expiration(new Date(expMillis))
                .signWith(SECRET_KEY,Jwts.SIG.HS256)
                .compact();

    }



    /**
     * 解析Token
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt)
    {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(jwt)
                    .getPayload();
        }
        catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", jwt);
        }
        return claims;
    }


    /**
     * 从token中获取登录用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token){

        String userName;
        try {
            Claims claims = parseJWT(token);
            String subject= claims.getSubject();
            //UserDetails loginUser = JSON.parseObject(subject, UserDetails.class);

            //userName = loginUser.getUsername();
            userName =  JSON.parseObject(subject, String.class);

        } catch (Exception e) {
            userName = null;
        }
        return userName;
    }


    /**
     * 从token中获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDateFromToken(String token){
        Claims claims =  claims = parseJWT(token);
        return claims.getExpiration();
    }

    private static Date getExpiration(Claims claims) {
        return claims.getExpiration();
    }

    /**
     * 判断token是否已经失效
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token){
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 验证token是否还有效
     * @param token  客户端传入的token
     * @param userDetails 从数据库中查询出来的用户信息
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    /**
     * 安全解析JWT（推荐）- 包含异常处理
     *
     * @param jwt 令牌字符串
     * @return Claims 对象，解析失败返回null
     */
    public Claims safeParse(String jwt) {
        try {
            return parseJWT(jwt);
        } catch (JwtException e) {
            // 日志记录异常
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String refreshHeadToken(String oldToken){
        if (oldToken == null || oldToken.isEmpty()){
            return null;
        }

        //token校验不通过
        Claims claims = parseJWT(oldToken);
        if (claims == null){
            return null;
        }

        //如果token已经过期，不支持刷新
        if (isTokenExpired(oldToken)){
            return null;
        }

        //如果token在30分钟之内刚刷新过，返回原token
        if(tokenRefreshJustBefore(oldToken,30*60)){
            return oldToken;
        }
        else {
            String subject = parseJWT(oldToken).getSubject();
           return createJWT(subject);
        }
    }

    /**
     *判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     * @return
     */
    private boolean tokenRefreshJustBefore(String token,int time){
        Claims claims = parseJWT(token);
        Date createdDate = claims.getIssuedAt();
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(createdDate) && refreshDate.before(DateUtil.offsetSecond(createdDate, time));
    }


}
