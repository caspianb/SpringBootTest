package hello.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class AuthFilterPreAuth extends AbstractPreAuthenticatedProcessingFilter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilterPreAuth.class);
    private final TenantStore tenantStore;

    public AuthFilterPreAuth(TenantStore tenantStore) {
        this.tenantStore = tenantStore;
        this.setCheckForPrincipalChanges(true);
        this.setAuthenticationManager(this::authenticate);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        log.info("1. {}", tenantStore);
        tenantStore.fromRequest(request);
        log.info("2. {}", tenantStore);
        return tenantStore.getTenant();
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

    protected Authentication authenticate(Authentication auth) {
        return auth;
    }
}
