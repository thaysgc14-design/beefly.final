package com.meuprojeto.aplicacaoweb.beeflyweb.controller;

import com.meuprojeto.aplicacaoweb.beeflyweb.model.Cliente;
import com.meuprojeto.aplicacaoweb.beeflyweb.repository.ClienteRepository;
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
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

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

    // Tela 1: Apenas para mostrar a lista de clientes (Tabela)
    @GetMapping("/clientes")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes"; // Abre o arquivo clientes.html
    }

    // Tela 2: Apenas para abrir o formulário de cadastro em branco
    @GetMapping("/novoCliente")
    public String telaCadastroCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cadastroClientes"; // Abre o arquivo cadastroClientes.html
    }

   @PostMapping("/salvarCliente")
    public String salvarCliente(@ModelAttribute Cliente cliente, Model model) {
        try {
            // 1. Limpa o CPF para contar apenas os números
            String cpfLimpo = cliente.getCpf() != null ? cliente.getCpf().replaceAll("[^0-9]", "") : "";
            
            // 2. Valida se tem exatamente 11 dígitos
            if (cpfLimpo.length() != 11) {
                model.addAttribute("erro", "O CPF precisa ter exatos 11 dígitos.");
                model.addAttribute("cliente", cliente);
                return "cadastroClientes"; // Retorna para o formulário com o pop-up de erro
            }

            // 3. Aplica a formatação padrão (987.654.321-00)
            String cpfFormatado = cpfLimpo.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            cliente.setCpf(cpfFormatado);

            // 4. Salva no banco se passar na validação
            clienteRepository.save(cliente);
            return "redirect:/clientes";
            
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao salvar: Verifique se o CPF já está cadastrado ou se os dados são inválidos.");
            model.addAttribute("cliente", cliente);
            return "cadastroClientes";
        }
    }
}
