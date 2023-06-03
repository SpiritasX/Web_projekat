package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping("/")
    private String home() {
        return "Hello, World!"; //"<form action=\"/api/login\" method=\"POST\"><button name=\"korisnickoIme\" value=\"Login\"/></form>";
    }

    @PostMapping("/api/pretrazi")
    public String pretrazi(@RequestParam String pretraga) {
        // return knjigaService.pretrazi(pretraga).toString();
        return "neki";
        // TODO bolji algoritam pretrazivanja
    }
}

