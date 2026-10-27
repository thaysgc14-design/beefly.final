package com.meuprojeto.aplicacaoweb.beeflyweb.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Long id;

    @Column(nullable = false)
    private String nome;
    
    @Column(name = "CPF", nullable = false, unique = true)
    private String cpf;

    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @Column(name = "telefone")
    private String telefone;

    public Cliente() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getNascimento() { return nascimento; }
    public void setNascimento(LocalDate nascimento) { this.nascimento = nascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}