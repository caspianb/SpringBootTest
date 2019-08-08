package hello.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @RequestMapping(path = "/data", method = RequestMethod.GET)
    public String getData(HttpServletRequest request) {
        System.out.println("Received: " + request.getParameterMap().entrySet().stream().collect(Collectors.toList()));
        return "";
    }

    @RequestMapping(path = "/data", method = RequestMethod.POST)
    public void postData(HttpServletRequest request) {
        System.out.println("Received: " + request.getParameterMap().entrySet().stream().collect(Collectors.toList()));
    }
}
