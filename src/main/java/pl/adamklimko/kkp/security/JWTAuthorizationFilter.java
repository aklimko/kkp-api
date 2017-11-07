package pl.adamklimko.kkp.security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static pl.adamklimko.kkp.security.SecurityUtils.HEADER_STRING;
import static pl.adamklimko.kkp.security.SecurityUtils.SECRET;
import static pl.adamklimko.kkp.security.SecurityUtils.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException, ExpiredJwtException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication;
        try {
            authentication = getAuthentication(req);
        } catch (ExpiredJwtException e) {
            res.setContentType("application/json");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
            return;
        } catch (SignatureException e) {
            res.setContentType("application/json");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Wrong token signature");
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) {
            return null;
        }

        String user = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
        if (user == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }
}
