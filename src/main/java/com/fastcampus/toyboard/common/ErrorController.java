package com.fastcampus.toyboard.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/badRequest")
    public String badRequest() {
        return "error/error400";
    }

    @GetMapping("/serverError")
    public String serverError() {
        return "error/error500";
    }

    @GetMapping ("/error403")
    public String forbidden() {
        return "error/error403";
    }

    @GetMapping ("/error")
    public String defaultError() {
        return "error/error404";
    }
}
