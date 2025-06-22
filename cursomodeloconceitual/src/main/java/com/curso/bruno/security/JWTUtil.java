package com.curso.bruno.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import org.hibernate.internal.log.SubSystemLogging;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.mysql.cj.log.Log;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			String username = claims.getSubject();
			Date expirateDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if (username != null && expirateDate != null && now.before(expirateDate)) {
				return true;
			}
		}
		return false;
	}

	private Claims getClaims(String token) {
		try {
			SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
			JwtParserBuilder parser = Jwts.parser();
			JwtParserBuilder verifyWith = parser.verifyWith(key);
			JwtParser jwtParser = verifyWith.build();
			Jws<Claims> signedClaims = jwtParser.parseSignedClaims(token);
			Claims payloadClaims = signedClaims.getPayload();
			return payloadClaims;
		} catch (Exception e) {
			return null;
		}
	}

	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if (claims != null) {
			return claims.getSubject();
		}

		return null;
	}

}
