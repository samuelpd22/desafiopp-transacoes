package com.picpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "tb_logista")
public class LogistaEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;
    private String segundoNome;
    @NotEmpty
    @Column( unique = true)
    private String cpfOuCnpj;
    @Email
    @NotEmpty
    @Column ( unique = true)
    private String email;

    private String senha;

    private BigDecimal saldoDisponivel;

    @Range(min = 1, max = 2, message = "(1) Para Usuario - (2) Para Logista.")
    @NotNull
    private int permissao; // 1 = Nivel usuario , 2 = Nivel Logista
}
