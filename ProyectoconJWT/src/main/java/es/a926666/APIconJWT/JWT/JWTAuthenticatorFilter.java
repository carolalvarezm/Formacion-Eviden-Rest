package es.a926666.APIconJWT.JWT;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import es.a926666.APIconJWT.User.User;
import es.a926666.APIconJWT.User.UserService;

import org.springframework.util.StringUtils;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticatorFilter extends OncePerRequestFilter {

	private final JWTService jwtService;
	private final UserService userService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Obtenemos el token de la petición
		final String token = getTokenFromRequest(request);
		final String username;
		if (token ==null)
		{
			//Cuando el token no existe hace el filtro y sale de la función(Nos devolverá al login)
			filterChain.doFilter(request,response);
			return;
		}
		//Traemos el username del token
		username= jwtService.getUsernameFromToken(token);
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
			User user=userService.findByUsername(username);
			if(jwtService.isTokenValid(token,user)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
		//Cuando existe realizamos el filtrado
		filterChain.doFilter(request, response);
	}
	public JWTAuthenticatorFilter(JWTService jwtService, UserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}
	private String getTokenFromRequest(HttpServletRequest request) {
		//Recogemos la cabecera de la petición.La parte de "Autorización"
		final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
		//Si se encuentra la cabecera y esta empieza con Bearer devolvemos lo que aparece a continuación(El token).
		//La cabecera es Autorization : <type> <token> . Donde el type es Bearer
		if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
		{
			return authHeader.substring(7);
		}
		return null;
	}
	

}
