package com.senai.conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String agencia;
    private String numeroConta;
    private String tipoConta;
    private long saldo;
    private boolean ativo;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
