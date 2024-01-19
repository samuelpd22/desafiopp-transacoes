package com.picpay.dto;

import com.picpay.entity.TipoUsuario;
import jakarta.validation.Valid;

import java.math.BigDecimal;

public record LogistaDTO(@Valid String primeiroNome, String segundoNome, String cpfOuCnpj,
                         String email, String senha, BigDecimal saldoDisponivel, TipoUsuario tipo) {
}
