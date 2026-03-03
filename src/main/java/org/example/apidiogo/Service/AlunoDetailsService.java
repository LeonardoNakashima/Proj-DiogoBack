package org.example.apidiogo.Service;

import org.example.apidiogo.Repository.AlunoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AlunoDetailsService implements UserDetailsService {
    private final AlunoRepository alunoRepository;

    public AlunoDetailsService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return alunoRepository.findAlunoByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Aluno não encontrado: " + email));
    }
}
