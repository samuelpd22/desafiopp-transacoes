package com.picpay.dto;

import java.math.BigDecimal;

public record TransacaoDTO(Long idUsuario, Long idLogista, BigDecimal valor) {
}
