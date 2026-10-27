package com.meuprojeto.aplicacaoweb.beeflyweb;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Usuario;
import com.meuprojeto.aplicacaoweb.beeflyweb.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional // Reverte as alterações na base de dados após cada teste para manter a BD limpa
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testSalvarEBuscarUsuario() {
        // 1. Criar um novo utilizador com os campos obrigatórios da entidade
        Usuario usuario = new Usuario();
        usuario.setNome("Utilizador Teste Unitário");
        usuario.setDataNas(LocalDate.of(1996, 11, 1));
        usuario.setFuncao("Gerente");
        usuario.setLogin("teste_unitario");
        usuario.setSenha("123456");

        // 2. Guardar o utilizador na base de dados
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        // 3. Validar se o ID foi gerado e se os dados foram guardados com sucesso
        assertThat(usuarioSalvo).isNotNull();
        assertThat(usuarioSalvo.getId()).isNotNull();
        assertThat(usuarioSalvo.getLogin()).isEqualTo("teste_unitario");

        // 4. Testar a busca do utilizador guardado pelo ID
        Usuario usuarioEncontrado = usuarioRepository.findById(usuarioSalvo.getId()).orElse(null);
        
        assertThat(usuarioEncontrado).isNotNull();
        assertThat(usuarioEncontrado.getNome()).isEqualTo("Utilizador Teste Unitário");
        assertThat(usuarioEncontrado.getDataNas()).isEqualTo(LocalDate.of(1996, 11, 1));
    }
}