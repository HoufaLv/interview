package com.iw.jwutil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.iw.entity.Account;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * JwUtil
 */
public class JwUtil {
    // TODO: 2018/5/18 0018 创建JwUtil类 封装创建Token,验证Token的方法

    /**
     * 设置过期时间为15分钟
     */
    public static final long TOKEN_EXPIRES = 500 * 60 * 60 * 24 * 30L;

    /**
     * 创建Token
     * @param userId    用户id
     * @param password  作为密钥
     * @return
     */
    public String createToken(String userId,String password){
        try {
            Algorithm algorithm = Algorithm.HMAC384(password);
            return JWT.create().withClaim("userId",userId)
                    .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES))
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("创建JWT Token 异常");
        }
    }


    /**
     * 从Token 中获取账号的ID
     * @param token
     * @return
     */
    public Integer getUserIdFromToken(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim("userId").asInt();
    }

    public void verifyToken(Integer userId,String password,String token){
        try {
            Algorithm algorithm = Algorithm.HMAC384(password);
            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("userId", userId).build();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

}
