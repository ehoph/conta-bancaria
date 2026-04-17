package com.senai.conta_bancaria.domain.exception;

public class ContaNaoEncontradoException extends RuntimeException {
    public ContaNaoEncontradoException(long idConta) {
        super("Conta com o ID:"+idConta+" Não Encontrado");
    }
}
