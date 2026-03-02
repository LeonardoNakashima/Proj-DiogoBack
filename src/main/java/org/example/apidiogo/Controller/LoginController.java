package org.example.apidiogo.Controller;

import org.example.apidiogo.Dto.LoginDto;
import org.example.apidiogo.Service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto request) {

        String token = loginService.login(
                request.getLogin(),
                request.getSenha()
        );

        return ResponseEntity.ok(
                Map.of("token", token)
        );
    }
}
