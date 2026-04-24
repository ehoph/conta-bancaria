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

    public void depositar(Long valorDepositado) {
        if (valorDepositado > 0) {
            this.saldo += valorDepositado;
        }
    }

    public void sacar(Long valorSaque) {
        if (valorSaque > 0 && valorSaque <= this.saldo) {
            this.saldo -= valorSaque;
        }
    }

    public void transferencia(Long valorTransferencia,Conta contaDestino) {
       sacar(valorTransferencia);
        contaDestino.depositar(valorTransferencia); 
       if (valorTransferencia > 0 && valorTransferencia <= this.saldo) {
           this.saldo -= valorTransferencia;
           contaDestino.depositar(valorTransferencia);
       }
    }
}
