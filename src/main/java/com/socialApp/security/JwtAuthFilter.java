package com.socialApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private CustomUserDetailService uds;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// extracting token
		String extractToken = request.getHeader("Authorization");

		String token = null;
		String username = null;

		if (extractToken != null && extractToken.startsWith("Bearer")) {

			token = extractToken.substring(7); // this is our actual token i.e without Bearer;
			try {
				
				username = this.jwtUtils.extractUsername(token);
				
			} catch (UsernameNotFoundException ex) {
				System.out.println("Username not found");
			} catch (IllegalArgumentException iaex) {
				System.out.println("token not found");
			} catch (Exception ex) {
				System.out.println("Something went wrong");
			}

		}

		else {
			System.out.println("Something went wrong with token");
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.uds.loadUserByUsername(username);

			if (this.jwtUtils.validateToken(token, userDetails)) {

				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);

			} else {
				System.out.println("Token not valid");
			}

		}

		else {
			System.out.println("Username is null or context is already set");
		}

		filterChain.doFilter(request, response);
	}

}
