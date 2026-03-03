package org.example.apidiogo.Service;

import org.example.apidiogo.Dto.LoginAdminResponse;
import org.example.apidiogo.Dto.LoginAlunoResponse;
import org.example.apidiogo.Dto.LoginProfessorResponse;
import org.example.apidiogo.Repository.AdminRepository;
import org.example.apidiogo.Repository.AlunoRepository;
import org.example.apidiogo.Repository.ProfessorRepository;
import org.example.apidiogo.Security.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final AlunoRepository alunoRepository;
    private final AdminRepository adminRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginService(ProfessorRepository professorRepository,AlunoRepository alunoRepository, AdminRepository adminRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.alunoRepository = alunoRepository;
        this.adminRepository = adminRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }


    public LoginAdminResponse autenticarAdmin(String usuario, String senha) {
        var admin = adminRepository.findByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Administrador não encontrado"));

        if (!passwordEncoder.matches(senha, admin.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");        }

        String token = jwtUtil.generateToken(admin);
        return new LoginAdminResponse(token, admin.getUsuario());
    }

    public LoginAlunoResponse autenticarAluno(String email, String senha) {
        var aluno = alunoRepository.findAlunoByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aluno não encontrado"));

        if (!passwordEncoder.matches(senha, aluno.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");        }

        String token = jwtUtil.generateToken(aluno);
        return new LoginAlunoResponse(token, aluno.getNome(), aluno.getMatricula());
    }

    public LoginProfessorResponse autenticarProfessor(String usuario, String senha) {
        var professor = professorRepository.findProfessorByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado"));

        if (!passwordEncoder.matches(senha, professor.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");        }

        String token = jwtUtil.generateToken(professor);
        return new LoginProfessorResponse(token, professor.getNome());
    }


}
