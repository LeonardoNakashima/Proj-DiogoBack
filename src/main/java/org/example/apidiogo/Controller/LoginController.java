package org.example.apidiogo.Controller;
import org.example.apidiogo.Dto.*;
import org.example.apidiogo.Service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/admin")
    public ResponseEntity<LoginAdminResponse> loginAdmin(@RequestBody LoginAdminRequest loginRequest) {
        LoginAdminResponse response = loginService.autenticarAdmin(loginRequest.getUsuario(), loginRequest.getSenha());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/aluno")
    public ResponseEntity<LoginAlunoResponse> loginAluno(@RequestBody LoginAlunoRequest loginRequest) {
        LoginAlunoResponse response = loginService.autenticarAluno(loginRequest.getEmail(), loginRequest.getSenha());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/professor")
    public ResponseEntity<LoginProfessorResponse> loginProfessor(@RequestBody LoginProfessorRequest loginRequest) {
        LoginProfessorResponse response = loginService.autenticarProfessor(loginRequest.getUsuario(), loginRequest.getSenha());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {

        String link = loginService.forgotPassword(email);

        return ResponseEntity.ok(link);
    }

}
