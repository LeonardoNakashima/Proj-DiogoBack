package org.example.apidiogo.Repository;

import org.example.apidiogo.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findAdminById(Long id);

    Optional<Admin> findByUsuario(String usuario);
}
