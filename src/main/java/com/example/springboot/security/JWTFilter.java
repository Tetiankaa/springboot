package com.example.springboot.security;

import com.example.springboot.dao.CustomerDAO;
import com.example.springboot.models.Customer;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private CustomerDAO customerDAO;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization!=null && authorization.startsWith("Bearer ")){
            String token = authorization.replace("Bearer ", "");

            String parsedToken = Jwts.parser()
                    .setSigningKey("tanya".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            Customer client = customerDAO.findByLogin(parsedToken);
            System.out.println(client);

            if (client != null){
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        client.getLogin(),client.getPassword(),client.getAuthorities()
                ));
            }
        }
    filterChain.doFilter(request,response);

    }
}
