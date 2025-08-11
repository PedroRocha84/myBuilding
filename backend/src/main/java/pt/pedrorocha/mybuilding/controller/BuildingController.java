package pt.pedrorocha.mybuilding.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BuildingController {

    @GetMapping("/api/test")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }

}
