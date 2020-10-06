package com.example.hystrixdashboard;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = "/error")
    public String myerror() {
        return "<center><h1>Something went wrong</h1></center>";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
