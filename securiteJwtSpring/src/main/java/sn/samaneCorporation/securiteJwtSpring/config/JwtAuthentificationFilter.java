package sn.samaneCorporation.securiteJwtSpring.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import sn.samaneCorporation.securiteJwtSpring.entities.User;

public class JwtAuthentificationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;

	/**
	 * @param authenticationManager
	 */
	public JwtAuthentificationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}
	
	//Tester l'authentification
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		User user = null;
		
		try {
			
			user  = new User();

			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
	}
	
	//Générer le Token aprés l'Authentification
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
		
		
		String jwt = Jwts.builder()
				.setSubject(u.getUsername())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXTIMEPIRATION_))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.KEYS)
				.claim("roles", u.getAuthorities())
				.compact();
			
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
	}
	
	

}
