package com.meuprojeto.aplicacaoweb.beeflyweb.repository;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}