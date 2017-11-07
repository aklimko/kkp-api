package pl.adamklimko.kkp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.adamklimko.kkp.model.user.AppUser;
import pl.adamklimko.kkp.model.user.Token;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static pl.adamklimko.kkp.security.SecurityUtils.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AppUser creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AppUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        Date expirationDate = getExpirationDate(EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        Token tokenResponse = new Token();
        tokenResponse.setToken(TOKEN_PREFIX + token);
        tokenResponse.setExpirationDate(LocalDateTime.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault()).toString());

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(tokenResponse);
        res.setContentType("application/json");
        res.getWriter().write(responseBody);
    }

    private Date getExpirationDate(long time) {
        return new Date(System.currentTimeMillis() + (time * 1000));
    }
}
