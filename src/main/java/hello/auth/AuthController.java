package hello.auth;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final TenantStore tenantStore;

    public AuthController(TenantStore tenantStore) {
        this.tenantStore = tenantStore;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getUserInfo(HttpServletRequest request) {
        log.info("{}", tenantStore);

        String response = "{\"name\": \"" + tenantStore.getTenant() + "\"}";
        return ResponseEntity.ok(response);
    }

}
