package com.employee.employeebackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employee.employeebackend.service.serviceImpl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void globalUserDetails(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public JwtAuthenticationFilter authenticationTokenFilterBean() {
		return new JwtAuthenticationFilter();
	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//	    web.ignoring().antMatchers(IGNORE_URLS);
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
				.antMatchers(PUBLIC_URLS)
				.permitAll().anyRequest().authenticated().and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	
	private static final String[] PUBLIC_URLS = {
			"/",
			"/authenticate**",
            "/v2/api-docs",
            "rest/docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "swagger-ui/index.html",
            "/configuration/**",
            "/webjars/**",
            "/rest/docs"
	};
}
