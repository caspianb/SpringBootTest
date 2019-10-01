package hello.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok("You have access! <a href=\"/api\">Protected Access?</a> | <a href=\"/logout\">Logout</a>");
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(path = "api", method = RequestMethod.GET)
    public ResponseEntity<String> getProtectedData() {
        return ResponseEntity.ok("You have protected access! <a href=\"/logout\">Logout</a>");
    }

}
