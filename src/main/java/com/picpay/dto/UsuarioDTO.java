package com.picpay.dto;

import jakarta.validation.Valid;

public record UsuarioDTO(@Valid int permissao, String primeiroNome, String segundoNome, String cpfOuCnpj,
                         String email, String senha) {


}
