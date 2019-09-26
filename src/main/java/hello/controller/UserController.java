package hello.controller;

import hello.auth.TenantStore;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final TenantStore tenantStore;

    public UserController(TenantStore tenantStore) {
        this.tenantStore = tenantStore;
    }

    @RequestMapping(path = "me", method = RequestMethod.GET)
    public ResponseEntity<String> getUserInfo(HttpServletRequest request) {

        log.info("{}", tenantStore);

        String response = null;
        response = new ObjectMapper().createObjectNode()
                .put("name", tenantStore.getTenant())
                .toString();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            response = new ObjectMapper().createObjectNode()
                    .put("name", auth.getName())
                    .put("auth", request.getAuthType())
                    .toString();
        }

        return ResponseEntity.ok(response);
    }

}
