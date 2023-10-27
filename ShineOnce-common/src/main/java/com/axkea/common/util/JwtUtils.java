package com.axkea.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtUtils {

    public static final long EXPIRE = 1000 * 60 * 60 * 24*7;//过期时间（毫秒为单位，此处为7天）
    public static final String APP_SECRET = "najiusuijishengchenglo";//秘钥（自己随便写）
    public static final String TOKEN_BODY_ID="id";//token 体中id值的key
    public static final String TOKEN_BODY_USERNAME="username";//token 体中id值的key


    public static String getJwtToken(Long id, String username){

        String JwtToken = Jwts.builder()
                .setHeaderParam("typ", "JWT")//jwet头信息
                .setHeaderParam("alg", "HS256")
                .setSubject("user_star")//分类（随便写）
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim(TOKEN_BODY_ID, id)//token载体（用户id和用户名）
                .claim(TOKEN_BODY_USERNAME, username)
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }

    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Integer getMemberIdByJwtToken(String  jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get(TOKEN_BODY_ID);
    }

    public static String getMemberNameByJwtToken(String  jwtToken) {
        if(!StringUtils.hasLength(jwtToken)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get(TOKEN_BODY_USERNAME);
    }

}
