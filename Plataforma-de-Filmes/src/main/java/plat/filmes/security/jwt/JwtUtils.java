package plat.filmes.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;
import plat.filmes.service.UserDetailsImpl;

import javax.crypto.SecretKey;
import java.nio.charset.MalformedInputException;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    @Value("${projeto.jwtSecret}")
    private String jwtSecret;

    @Value("${projeto.jweExpiration}")
    private int jwtExpirationMs; //tempo do token expirar

    public String generateTokenFromUserDetailsImpl(UserDetailsImpl userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(getSigninKey(), SignatureAlgorithm.ES512).compact();

    }
    public Key getSigninKey (){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        return key;
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(getSigninKey()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            System.out.println("Token invalido" + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token nao suportado" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token Argumento invalido" + e.getMessage());
        }
        return false;
    }

}
