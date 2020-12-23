package sn.samaneCorporation.securiteJwtSpring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sn.samaneCorporation.securiteJwtSpring.services.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers(HttpMethod.OPTIONS,"/*").permitAll();
		http.authorizeRequests().antMatchers("/api/Client/add").hasAuthority("Admin");
		http.authorizeRequests().anyRequest().authenticated();
		
		//Ajouter les filtres
		http.addFilter(new JwtAuthentificationFilter(authenticationManager()))
				.addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		//Ne pas utiliser les Sessions
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// dire a spring security de ne pas utilisé les sessions
//		http.formLogin();
	}

	@Bean
	public PasswordEncoder password() {
		return NoOpPasswordEncoder.getInstance();
	}

}
