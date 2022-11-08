package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.eraseCredentials(false);
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/welcome", "/user/**", "/css/**", "/img/**", "/js/**").permitAll();

		http.authorizeRequests().antMatchers("/client/**").access("hasAnyAuthority('Client')");

		http.authorizeRequests().antMatchers("/agent/**").access("hasAnyAuthority('Agent')");

		http.authorizeRequests().antMatchers("/employee/**").access("hasAnyAuthority('Employee')");

		http.authorizeRequests().antMatchers("/admin/**").access("hasAnyAuthority('Admin')");

//		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/user/login")
//				.failureUrl("/user/login?error=true").defaultSuccessUrl("/welcome", true).and().logout()
//				.logoutUrl("/user/logout").logoutSuccessUrl("/user/login?logout");
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/user/login").usernameParameter("username").passwordParameter("password").loginProcessingUrl("/welcome")
				.failureUrl("/login?error=true").defaultSuccessUrl("/welcome",true)
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")).logoutSuccessUrl("/user/login?logout");
	}

}
