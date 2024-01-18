package com.picpay.entity.transacao;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name = "tb_transacao")
public class TransacaoEntity {

    @OneToOne
    @JoinColumn(name = "tb_usuarios_id")
    private Long idUsuario;

    @OneToOne
    @JoinColumn(name = "tb_logista_id")
    private Long idLogista;


    private BigDecimal valor;
}
