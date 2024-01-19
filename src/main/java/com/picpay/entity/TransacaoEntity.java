package com.picpay.entity;

import jakarta.persistence.*;
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
@Table ( name = "tbtransacao")
public class TransacaoEntity {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;


    private Long idUsuario;


    private Long idLogista;


    private BigDecimal valor;
}
