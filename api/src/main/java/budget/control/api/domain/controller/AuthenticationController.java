package budget.control.api.domain.controller;

import budget.control.api.infra.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import budget.control.api.domain.model.User;
import budget.control.api.domain.model.dto.AuthenticationData;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenJWTData> performLogin(@RequestBody @Valid AuthenticationData authenticationData) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(authenticationData.login(), authenticationData.password());
		var authentication = authenticationManager.authenticate(authenticationToken);

		var tokenJWT = tokenService.generateToken((User)authentication.getPrincipal());
		
		return ResponseEntity.ok(new TokenJWTData(tokenJWT));
	}
	
}
