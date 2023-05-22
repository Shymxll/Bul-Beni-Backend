package com.project.bulbeniback.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class JWTService {

    private  String JWT_SECRET;

   
    private int JWT_EXPIRATION_TIME_IN_MILLISECONDS;

    public String generateToken(String userName){
        Map<String, Object> claims = new HashMap<>();
        return this.tokenCreator(claims, userName);
    }

    public String tokenCreator(Map<String, Object> claims, String userName){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+this.JWT_EXPIRATION_TIME_IN_MILLISECONDS))
                .signWith(this.getSignedKey(), SignatureAlgorithm.HS256).compact();
    }

    public String extractUsernameFromToken(String theToken){
        return this.extractClaim(theToken, Claims ::getSubject);
    }
    public Date extractExpirationTimeFromToken(String theToken) {
        return this.extractClaim(theToken, Claims :: getExpiration);
    }

    public Boolean validateToken(String theToken, UserDetails userDetails){
        final String userName = this.extractUsernameFromToken(theToken);
        return (userName.equals(userDetails.getUsername()) && !this.isTokenExpired(theToken));
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getSignedKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
    private boolean isTokenExpired(String theToken) {
        return this.extractExpirationTimeFromToken(theToken).before(new Date());
    }
    private Key getSignedKey(){
        byte[] keyByte = Decoders.BASE64.decode(this.JWT_SECRET);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
