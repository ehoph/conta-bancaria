package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.entity.Usuario;
import jakarta.validation.constraints.NotNull;

public record ContaRequestDTO(
        @NotNull
        String agencia,
        @NotNull
        String numeroConta,
        @NotNull
        String tipoConta,
        @NotNull
        long saldo

) {
    public Conta toEntity(){
        return Conta.builder()
                .agencia(this.agencia)
                .numeroConta(this.numeroConta)
                .tipoConta(this.tipoConta)
                .saldo(this.saldo)
                .ativo(true)
                .usuario(null)
                .build();
    }
}
