package com.kostakuu.moviestar.security.token;

import com.kostakuu.moviestar.entity.User;
import com.kostakuu.moviestar.provider.TimeProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
public class TokenHelper {

    @Value("bank")
    private String APP_NAME;

    @Value("eem4ilV3ry43cr3tK3yTh4tI5K3ptF4r4w4yId0ntKn0wWh4t3l5et04ddeem4ilV3ry43cr3tK3yTh4tI5K3ptF4r4w4yId0ntKn0wWh4t3l5et04dd2o2o!")
    public String SECRET;

    @Value("300000")
    private int EXPIRES_IN;

    @Value("Authorization")
    private String AUTH_HEADER;

    private final TimeProvider timeProvider;

    public TokenHelper(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public String generateToken(String username) {
        return Jwts.builder().setIssuer(APP_NAME).setSubject(username)
                .setIssuedAt(timeProvider.now()).setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET).compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(timeProvider.now().getTime() + EXPIRES_IN * 1000L);
    }

    public int getExpiredIn() {
        return EXPIRES_IN;
    }

    public Boolean validateToken(String token, User user) {
        final String username = getUsernameFromToken(token);
        return (username != null && username.equals(user.getUsername()));
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }
}