/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.meuprojeto.aplicacaoweb.beeflyweb;


import com.meuprojeto.aplicacaoweb.beeflyweb.model.Cliente;
import com.meuprojeto.aplicacaoweb.beeflyweb.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional 
public class ClienteTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testSalvarEBuscarClienteComSucesso() {
        Cliente cliente = new Cliente();
        cliente.setNome("Carlos Alberto Teste");
        cliente.setCpf("123.400.789-01");
        cliente.setNascimento(LocalDate.of(1990, 5, 15));
        cliente.setTelefone("98967-6655");

        Cliente clienteSalvo = clienteRepository.save(cliente);

        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getId()).isNotNull();
        assertThat(clienteSalvo.getCpf()).isEqualTo("123.006.789-01");

        Cliente clienteEncontrado = clienteRepository.findById(clienteSalvo.getId()).orElse(null);
        assertThat(clienteEncontrado).isNotNull();
        assertThat(clienteEncontrado.getNome()).isEqualTo("Carlos Alberto Teste");
        assertThat(clienteEncontrado.getNascimento()).isEqualTo(LocalDate.of(1990, 5, 15));
    }
}

