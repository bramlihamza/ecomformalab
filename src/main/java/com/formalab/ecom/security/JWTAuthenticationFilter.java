package com.formalab.ecom.security;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.formalab.ecom.entites.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    String id ;
    
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super();
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        Utilisateur appuser = null;
        //String username=request.getParameter("username");
        try {
            appuser = new ObjectMapper().readValue(request.getInputStream(), Utilisateur.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("******************");
        System.out.println("email  " + appuser.getEmail());
        System.out.println("Mot de passe  " + appuser.getPassword());
        id= appuser.getEmail() ;
//        response.addHeader("Access-Control-Allow-Origin", "*");

        return authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(appuser.getEmail(), appuser.getPassword()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        User springuser = (User) authResult.getPrincipal();
        String jwt = Jwts.builder().setSubject(springuser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
                .claim("roles", springuser.getAuthorities())
                .compact();
//        response.addHeader("Access-Control-Allow-Origin", "*");
        System.out.println(springuser.getAuthorities().toString());
        response.getWriter().append( "{" +
        		'"' +"Authorization"+'"' +":" 
        		+ '"' + SecurityConstants.TOKEN_PREFIX +   jwt +'"'+ "," +
        		'"'+ "email" +'"'+ ":"  +'"'+ id +'"' + "}"
        		
        		);
        
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + jwt);

    }
}
