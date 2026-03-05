package org.example.apidiogo.Service;

import jakarta.validation.Valid;
import org.example.apidiogo.Dto.AdminRequestDto;
import org.example.apidiogo.Dto.AdminResponseDto;
import org.example.apidiogo.Dto.AlunoRequestDto;
import org.example.apidiogo.Dto.AlunoResponseDto;
import org.example.apidiogo.Exception.AdminNotFoundException;
import org.example.apidiogo.Model.Admin;
import org.example.apidiogo.Repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    public AdminService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder =  passwordEncoder;

    }

    private Admin fromRequestDTO(AdminRequestDto dto) {
        Admin admin = new Admin();
        admin.setUsuario(dto.getUsuario());
        String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
        admin.setSenha(senhaCriptografada);
        return admin;
    }

    private AdminResponseDto toResponseDto(Admin admin) {
        return new AdminResponseDto(
                admin.getId(),
                admin.getUsuario()
        );
    }

    public List<AdminResponseDto> listAll() {
        return adminRepository.findAll()
                .stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AdminResponseDto> listById(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        return admin.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    public AdminResponseDto createAdmin(AdminRequestDto dto) {
        Admin admin = fromRequestDTO(dto);
        Admin salvo = adminRepository.save(admin);
        return toResponseDto(salvo);
    }

    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin com id " + id +" não foi encontrado: 404"));
        adminRepository.delete(admin);
    }

    public AdminResponseDto updateAdmin(@Valid AdminRequestDto adminAtualizado, Long id) {
        Admin existente = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin com o id " + id + " não encontrado"));
        existente.setUsuario(adminAtualizado.getUsuario());
        existente.setSenha(adminAtualizado.getSenha());

        Admin atualizado = adminRepository.save(existente);
        return toResponseDto(atualizado);

    }

    public AdminResponseDto updatePatchAdmin(Map<String, Object> updates, Long id) {
        Admin existente = adminRepository.findById(id)
                .orElseThrow(() -> new AdminNotFoundException("Admin com o id " + id+ " não foi encontrado"));

        if(updates.containsKey("usuario")){
            existente.setUsuario(updates.get("usuario").toString());
        }
        if (updates.containsKey("senha")) {
            existente.setSenha(updates.get("senha").toString());
        }

        Admin atualizado = adminRepository.save(existente);
        return toResponseDto(atualizado);
    }


}
