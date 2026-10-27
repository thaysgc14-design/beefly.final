package com.meuprojeto.aplicacaoweb.beeflyweb.repository;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String login);
}