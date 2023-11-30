package com.z.admin.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;

/**
 * @author zhy
 * @description
 * @date 2022/12/21
 */
@Slf4j
public class JwtUtil {
    /**
     * 这个秘钥是防止JWT被篡改的关键，随便写什么都好，但决不能泄露
     */
    private final static String secretKey = "lulalalulalalulalulalei";
    /**
     * 过期时间目前设置成1天，这个配置随业务需求而定
     */
    private final static Duration expiration = Duration.ofDays(1);

    /**
     * 生成JWT
     *
     * @param userName 用户名
     * @return JWT
     */
    public static String generate(String userName) {
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis() + expiration.toMillis());

        return Jwts.builder()
                .setSubject(userName) // 将userName放进JWT
                .setIssuedAt(new Date()) // 设置JWT签发时间
                .setExpiration(expiryDate)  // 设置过期时间
                .signWith(SignatureAlgorithm.HS512, secretKey) // 设置加密算法和秘钥
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        // 如果是空字符串直接返回null
        if (DataUtils.isEmpty(token)) {
            return null;
        }

        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置秘钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            if (log.isErrorEnabled()) {
                log.error("token解析失败:{}", e.toString());
            }
        }
        return claims;
    }
}
