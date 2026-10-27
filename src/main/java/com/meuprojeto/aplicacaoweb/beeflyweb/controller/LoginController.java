package com.meuprojeto.aplicacaoweb.beeflyweb.controller;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Usuario;
import com.meuprojeto.aplicacaoweb.beeflyweb.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String index() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/fazerLogin")
    public String fazerLogin(@RequestParam String login, @RequestParam String senha, Model model, HttpSession session) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByLogin(login);

        if (usuarioOpt.isPresent() && usuarioOpt.get().getSenha().equals(senha)) {
            session.setAttribute("nomeUsuario", usuarioOpt.get().getNome());
            return "redirect:/menu";
        } else {
            model.addAttribute("erro", "Login ou palavra-passe incorretos!");
            return "login";
        }
    }

    @GetMapping("/menu")
    public String menuPage() {
        return "menu";
    }
}
