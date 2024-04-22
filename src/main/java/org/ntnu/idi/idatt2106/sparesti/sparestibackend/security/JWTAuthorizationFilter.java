package org.ntnu.idi.idatt2106.sparesti.sparestibackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter chain for JWT token that is executed for every request
 *
 * @author Harry L.X & Lars M.L.N
 * @since 17.4.24
 */
@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * The JWT filter
     * @param request HTTP request
     * @param response HTTP response
     * @param filterChain Filter chain that executes after JWT filter
     * @throws IOException If token is invalid
     */
    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain)
            throws IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            final String username;
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
            // Checks if user is not null and that it has not already been authenticated - else
            // there is
            // no point in re-authenticating
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Retrieve user details from database for validation
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                // If token is valid, update security context
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            String responseMsg = "Invalid or expired JWT token";
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.getWriter().write(responseMsg);
        }
    }
}
