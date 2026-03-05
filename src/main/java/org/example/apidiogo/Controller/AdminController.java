package org.example.apidiogo.Controller;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.AdminRequestDto;
import org.example.apidiogo.Dto.AdminResponseDto;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Openapi.AdminOpenApi;
import org.example.apidiogo.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Admin")
public class AdminController implements AdminOpenApi {

    private final AdminService service;
    private final PasswordEncoder passwordEncoder;


    public AdminController(AdminService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/list")
    public ResponseEntity<List<AdminResponseDto>> listAdmins() {
        List<AdminResponseDto> admins = service.listAll();
        return ResponseEntity.ok(admins);
    }


    @GetMapping("/findAdmin/{id}")
    public ResponseEntity<List<AdminResponseDto>> findAdmin(@PathVariable Long id) {
        return ResponseEntity.ok(service.listById(id));
    }


    @PostMapping("/create")
    public ResponseEntity<AdminResponseDto> createAdmin(@RequestBody @Valid AdminRequestDto dto) {
        AdminResponseDto response = service.createAdmin(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable Long id) {
        service.deleteAdmin(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@PathVariable Long id, @RequestBody @Valid AdminRequestDto dto) {
        AdminResponseDto response = service.updateAdmin(dto, id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<AdminResponseDto> patchAdmin(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        AdminResponseDto response = service.updatePatchAdmin(updates, id);
        return ResponseEntity.ok(response);
    }


}
