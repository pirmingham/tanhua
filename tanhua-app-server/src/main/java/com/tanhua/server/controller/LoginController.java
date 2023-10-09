package com.tanhua.server.controller;

import com.tanhua.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map){
        String phone = (String) map.get("phone");

        userService.login(phone);
        return ResponseEntity.ok(null);
    }
}
