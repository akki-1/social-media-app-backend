package com.socialApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.socialApp.security.CustomUserDetailService;
import com.socialApp.security.JwtAuthEntryPoint;
import com.socialApp.security.JwtAuthFilter;

// we are using Spring Security to protect our backend APIs so can without proper authentication, no one can call them.
// this is configuration class for the same
// we can configure here which APIs should be open for all for ex. signup, login because anyone can register in our app and
// anyone can try to login also

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailService uds;

	@Autowired
	JwtAuthFilter jfilter;

	@Autowired
	JwtAuthEntryPoint unauthoHandler;

	// this is a password encryption technology which we are going to use in this
	// app so if by any chance someone
	// hack our database then still he can't see password of our users
	@Bean
	public BCryptPasswordEncoder passEnco() {

		return new BCryptPasswordEncoder();
	}

	// with this overrided configure method, we are telling spring security builder
	// to authenticate details with our database
	// user details by providing customUserDetailService
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(this.uds).passwordEncoder(passEnco());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().cors().disable().authorizeRequests().antMatchers("/app/login", "/app/signup", "/app/test")
		.permitAll().antMatchers(HttpMethod.OPTIONS).permitAll().anyRequest().authenticated().and()
		.exceptionHandling().authenticationEntryPoint(this.unauthoHandler).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jfilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

}
