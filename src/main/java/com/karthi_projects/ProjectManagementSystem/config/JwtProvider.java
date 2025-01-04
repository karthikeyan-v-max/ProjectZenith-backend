package com.karthi_projects.ProjectManagementSystem.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;

public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    public static String generateToken(Authentication auth) {
        // Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        System.out.println(auth.getName());
        return Jwts.builder().setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+864000000))
                .claim("email" , auth.getName())
                .signWith(key)
                .compact();
    }

    public static String getEmailFromToken(String jwt) {

        jwt = jwt.substring(7);
         Claims claims = Jwts.parser().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

        //Claims claims = Jwts.parser().build().parseEncryptedClaims(jwt).getPayload();

        return String.valueOf(claims.get("email"));
    }
}
