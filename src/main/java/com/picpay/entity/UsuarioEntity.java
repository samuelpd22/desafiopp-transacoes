package com.picpay.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Entity
@Table ( name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;
    private String segundoNome;
    @NotEmpty
    @Column ( unique = true)
    private String cpfOuCnpj;
    @Email
    @NotEmpty
    @Column ( unique = true)
    private String email;
    private String senha;

    private BigDecimal saldoDisponivel;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;


}
