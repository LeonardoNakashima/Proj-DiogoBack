package org.example.apidiogo.Security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Model.Aluno;
import org.example.apidiogo.Model.Professor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "as8d7a9s8d7as9d8a7s9d8a7sd98a7sd98as7d9as8d7a9s8d7as98d7a9s8d7a";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 5;

    public String generateToken(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getUsername())
                .claim("usuario", admin.getUsuario())
                .claim("type", "ADMIN")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateToken(Professor professor) {
        return Jwts.builder()
                .setSubject(professor.getUsername())
                .claim("usuario", professor.getUsuario())
                .claim("type", "PROFESSOR")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateToken(Aluno aluno) {
        return Jwts.builder()
                .setSubject(aluno.getUsername())
                .claim("email", aluno.getEmail())
                .claim("type", "ALUNO")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isTokenValido(String token) {
        return extractClaim(token, Claims::getExpiration).after(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}