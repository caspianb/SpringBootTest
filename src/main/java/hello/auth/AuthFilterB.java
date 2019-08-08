package hello.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class AuthFilterB extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilterB.class);
    private final TenantStore tenantStore;

    public AuthFilterB(TenantStore tenantStore) {
        this.tenantStore = tenantStore;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("1. {}", tenantStore);
        tenantStore.fromRequest(request);
        log.info("2. {}", tenantStore);
        filterChain.doFilter(request, response);
    }
}
