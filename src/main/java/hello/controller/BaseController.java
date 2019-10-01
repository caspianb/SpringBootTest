package hello.controller;

import org.springframework.http.ResponseEntity;

public interface BaseController {
    ResponseEntity<String> getData();

    ResponseEntity<String> getProtectedData();
}
