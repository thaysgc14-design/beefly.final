function validarFormularioCliente(event) {
    var nome = document.getElementById("nome").value.trim();
    var cpf = document.getElementById("cpf").value.trim();
    var telefone = document.getElementById("telefone").value.trim();

    if (nome === "") {
        alert("O campo Nome é obrigatório!");
        event.preventDefault(); // Impede o envio do formulário
        return false;
    }

    // Remove caracteres não numéricos do CPF para contar os dígitos
    var cpfLimpo = cpf.replace(/\D/g, '');
    if (cpfLimpo.length !== 11) {
        alert("O CPF precisa ter exatos 11 dígitos.");
        event.preventDefault();
        return false;
    }

    if (telefone.length < 10) {
        alert("Por favor, informe um telefone válido.");
        event.preventDefault();
        return false;
    }

    return true;
}