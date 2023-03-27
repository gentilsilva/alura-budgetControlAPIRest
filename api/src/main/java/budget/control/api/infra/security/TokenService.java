package budget.control.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import budget.control.api.domain.model.User;

@Service
public class TokenService {

	@Value("{api.security.toke.secret}")
	private String secret;

	public String generateToken(User user) {
		try {
			var algorithm = Algorithm.HMAC256(secret);
			return JWT.create().withIssuer("API budget.control").withSubject(user.getLogin())
					.withClaim("id", user.getId()).withExpiresAt(exirationDate()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new RuntimeException("Erro ao gerar token jwt", exception);
		}
	}

	private Instant exirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
