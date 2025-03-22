package com.thiagoleite.GastroHubSolo.dataprovider.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UserDetailsService userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtAuthenticationFilter processando requisição para: " + request.getRequestURI());

        if (request.getRequestURI().startsWith("/api/auth/")) {
            System.out.println("Ignorando verificação de token para endpoint de autenticação");
            filterChain.doFilter(request, response);
            return;
        }

        // Obter token JWT da requisição
        String token = getTokenFromRequest(request);

        if (token != null) {
            System.out.println("Token encontrado: " + token.substring(0, Math.min(10, token.length())) + "...");
        } else {
            System.out.println("Nenhum token encontrado na requisição");
        }

        // Validar token
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
            try {
                // Obter username do token
                String username = tokenProvider.getUsernameFromToken(token);
                System.out.println("Username extraído do token: " + username);

                // Carregar detalhes do usuário
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                System.out.println("Detalhes do usuário carregados para: " + username);

                // Criar autenticação
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Definir autenticação no contexto de segurança
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                System.out.println("Autenticação configurada no SecurityContext");
            } catch (Exception e) {
                System.err.println("Erro ao processar token: " + e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}