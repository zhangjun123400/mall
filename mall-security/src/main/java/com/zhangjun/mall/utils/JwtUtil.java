package com.zhangjun.mall.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
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
public class JwtUtil {

    //有效期为一个小时
    public Long expiration=1L;

    //设置密钥明文(盐)
    public String secert="dongfeng";

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
     * @return
     */
    public String getUUID()
    {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 生成jwt
     * @param subject token中要存储的数据（json格式）
     * @return
     */
    public String createJWT(String subject)
    {
        return  createJWT(subject,Duration.ofHours(expiration));//设置过期时间
    }

    //生成JWT的业务逻辑代码
    private String createJWT(String subject, Duration ttlMillis)
    {

        long nowMillis = System.currentTimeMillis();//获取系统当前的时间戳

        long expMillis = nowMillis + ttlMillis.toMillis();
        Date exp = new Date();

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
     * 解析
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception
    {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
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


}
