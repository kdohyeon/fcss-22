package fast.campus.movieservice.security.filter;

import fast.campus.movieservice.security.OtpAuthentication;
import fast.campus.movieservice.security.OtpAuthenticationProvider;
import fast.campus.movieservice.security.UsernamePasswordAuthentication;
import fast.campus.movieservice.security.UsernamePasswordAuthenticationProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class InitialAuthenticationFilter extends OncePerRequestFilter {

    private final OtpAuthenticationProvider otpAuthenticationProvider;
    private final UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Value("${jwt.signing-key}")
    private String jwtKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");

        if (StringUtils.isBlank(otp)) {
            UsernamePasswordAuthentication authentication = new UsernamePasswordAuthentication(username, password);
            usernamePasswordAuthenticationProvider.authenticate(authentication);
        } else {
            Authentication authentication = new OtpAuthentication(username, otp);
            otpAuthenticationProvider.authenticate(authentication);

            SecretKey secretKey = Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .claim("username", username)
                    .signWith(secretKey)
                    .compact();

            response.setHeader("Authorization", jwt);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !request.getServletPath().equals("/login");
    }
}
