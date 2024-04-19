package es.a926666.APIconJWT.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.a926666.APIconJWT.JWT.JWTAuthenticatorFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private final JWTAuthenticatorFilter jwtAuthenticatorFilter;
	private final AuthenticationProvider autheticationProvider;
	public SecurityConfig(JWTAuthenticatorFilter jwtAuthenticatorFilter,AuthenticationProvider autheticationProvider) {
		this.jwtAuthenticatorFilter = jwtAuthenticatorFilter;
		this.autheticationProvider = autheticationProvider;
	}

	@Bean
	//Devuelve la cadena de filtrado(Por la que filtra la seguridad)
	public SecurityFilterChain security(HttpSecurity http) throws Exception
	{
		return http
				//Desabilitamos csrf ya que vamos a estar trabajando con JWT
				.csrf(csrf ->
					csrf
					.disable())
				.authorizeHttpRequests((authRequest) ->
					authRequest
						.requestMatchers("/auth/**").permitAll()
						.anyRequest().authenticated()
						)
				.sessionManagement(sessionManager->
					sessionManager
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authenticationProvider(autheticationProvider)
				.addFilterBefore(jwtAuthenticatorFilter, UsernamePasswordAuthenticationFilter.class)
				.build();	
	}
}
