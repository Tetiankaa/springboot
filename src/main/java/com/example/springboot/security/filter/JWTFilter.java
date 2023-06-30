package com.example.springboot.security.filter;

import com.example.springboot.dao.ClientUserDAO;
import com.example.springboot.models.ClientUser;
import io.jsonwebtoken.Jwts;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private ClientUserDAO clientUserDAO;
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.replace("Bearer ", "");
            System.out.println("TOKEN "+token);

            String decodedData = Jwts.parser()
                    .setSigningKey("tanya".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            ClientUser client = clientUserDAO.findByEmail(decodedData);
            System.out.println("client:" + client);

            if (client != null){
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(
                                new UsernamePasswordAuthenticationToken(
                                      client.getEmail(),
                                        client.getPassword(),
                                        client.getAuthorities()

                                )
                        );
            }

        }
        filterChain.doFilter(request,response);
    }
}
