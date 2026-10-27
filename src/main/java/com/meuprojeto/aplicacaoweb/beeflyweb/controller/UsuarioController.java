package com.meuprojeto.aplicacaoweb.beeflyweb.controller;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Usuario;
import com.meuprojeto.aplicacaoweb.beeflyweb.repository.UsuarioRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
        @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        binder.registerCustomEditor(LocalDate.class, new java.beans.PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    setValue(LocalDate.parse(text, formatter));
                }
            }
        });
    }


    // Abre o formulário de cadastro de utilizador
    @GetMapping("/novoUsuario")
    public String novoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastroUsuario";
    }

    // Guarda o novo utilizador na base de dados e redireciona para o login
   @PostMapping("/salvarUsuario")
public String salvarUsuario(@ModelAttribute Usuario usuario, Model model) {
    try {
        usuarioRepository.save(usuario);
        return "redirect:/login";
    } catch (Exception e) {
        // Mensagem adaptada para o cadastro de utilizadores (ex: login duplicado)
        model.addAttribute("erro", "Erro ao salvar: Verifique se o login já está em uso ou se os dados estão corretos.");
        model.addAttribute("usuario", usuario);
        return "cadastroUsuario"; // Mantém na mesma página de cadastro de utilizador
    }
}
}