package com.example.demo.Security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/special")
    public String special() {
        return "special";
    }

    @GetMapping("/basic")
    public String basic() {
        return "basic";
    }
}
