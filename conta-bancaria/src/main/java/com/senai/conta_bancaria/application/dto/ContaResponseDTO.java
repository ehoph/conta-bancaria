package com.senai.conta_bancaria.application.dto;

import com.senai.conta_bancaria.domain.entity.Conta;

public record ContaResponseDTO(long id, String agencia, String numeroConta, String tipoConta, long saldo) {
    public static ContaResponseDTO fromEntity(Conta conta){
        return new ContaResponseDTO(
                conta.getId(),
                conta.getAgencia(),
                conta.getNumeroConta(),
                conta.getTipoConta(),
                conta.getSaldo()
        );
    }
}
