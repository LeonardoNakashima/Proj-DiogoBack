package org.example.apidiogo.Security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.apidiogo.Service.AdminDetailsService;
import org.example.apidiogo.Service.AlunoDetailsService;
import org.example.apidiogo.Service.ProfessorDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AdminDetailsService adminDetailsService;
    private final ProfessorDetailsService professorDetailsService;
    private final AlunoDetailsService alunoDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil,
                            AdminDetailsService adminDetailsService,
                            ProfessorDetailsService professorDetailsService,
                            AlunoDetailsService alunoDetailsService) {
        this.jwtUtil = jwtUtil;
        this.adminDetailsService = adminDetailsService;
        this.professorDetailsService = professorDetailsService;
        this.alunoDetailsService = alunoDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        String userType = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
                userType = jwtUtil.extractClaim(jwt, claims -> claims.get("type", String.class));
            } catch (Exception e) {
                logger.error("Erro ao processar token JWT");
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = switch (userType.toUpperCase()) {
                case "ADMIN" -> adminDetailsService.loadUserByUsername(username);
                case "PROFESSOR" -> professorDetailsService.loadUserByUsername(username);
                case "ALUNO" -> alunoDetailsService.loadUserByUsername(username);
                default -> null;
            };

            if (userDetails != null && jwtUtil.isTokenValido(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        chain.doFilter(request, response);
    }
}