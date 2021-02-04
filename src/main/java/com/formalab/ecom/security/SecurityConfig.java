package com.formalab.ecom.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	
    //liste des ressources swagger � autoriser
    private static final String[] AUTH_WHITELIST = {

            // -- swagger ui
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
        //debut de jwt
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //http.formLogin();
        //activer spring securité
        http.authorizeRequests().antMatchers("*").authenticated();
        //j ai pas besoin de s'authentifier pour faire le register
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/files/**").permitAll();
        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll();
        //	http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").permitAll();
        http.authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll();
        
      
        http
        .authorizeRequests()
            .antMatchers( "/favicon.ico").permitAll();
        //	http.authorizeRequests().antMatchers(HttpMethod.GET,"/users/**").hasAuthority("ADMIN");
        //http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
}
}
