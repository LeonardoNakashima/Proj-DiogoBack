package org.example.apidiogo.Service;

import org.example.apidiogo.Repository.ProfessorRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProfessorDetailsService implements UserDetailsService {

    private final ProfessorRepository professorRepository;

    public ProfessorDetailsService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        return professorRepository.findProfessorByUsuario(usuario)
                .orElseThrow(() -> new UsernameNotFoundException("Professor não encontrado: " + usuario));
    }
}