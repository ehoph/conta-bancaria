package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaRequestDTO;
import com.senai.conta_bancaria.application.dto.ContaResponseDTO;
import com.senai.conta_bancaria.application.dto.Deposito.DepositoRequestDTO;
import com.senai.conta_bancaria.application.dto.Saque.SaqueRequestDTO;
import com.senai.conta_bancaria.application.dto.TransferenciaRequestDTO;
import com.senai.conta_bancaria.application.dto.TransferenciaResponseDTO;
import com.senai.conta_bancaria.domain.entity.Conta;
import com.senai.conta_bancaria.domain.exception.ContaNaoEncontradoException;
import com.senai.conta_bancaria.domain.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    ContaRepository contaRepository;

    public ContaResponseDTO cadastrarConta(ContaRequestDTO contaRequestDTO){
        return  ContaResponseDTO.fromEntity(contaRepository.save(contaRequestDTO.toEntity()));
    }



    public List<ContaResponseDTO> listarContas(){
        return contaRepository.findAll().stream().map(ContaResponseDTO::fromEntity).toList();
    }


    public ContaResponseDTO buscarContaPorId(Long id){
        return ContaResponseDTO.fromEntity(contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id)));
    }

    public ContaResponseDTO editarConta(Long id, ContaRequestDTO contaRequestDTO){
        Conta contaEditada = contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id));
        contaEditada.setAgencia(contaRequestDTO.agencia());
        contaEditada.setNumeroConta(contaRequestDTO.numeroConta());
        contaEditada.setTipoConta(contaRequestDTO.tipoConta());
        contaEditada.setSaldo(contaRequestDTO.saldo());
        return ContaResponseDTO.fromEntity(contaRepository.save(contaEditada));
    }

    public void apagarConta(Long id){
        if(!contaRepository.existsById(id)){
            throw new ContaNaoEncontradoException(id);
        }
        contaRepository.deleteById(id);
    }

    public Conta depositarConta(Long id, DepositoRequestDTO depositoRequestDTO){
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id));

        conta.depositar(depositoRequestDTO.valorDepositado());
        return contaRepository.save(conta);
    }

    public Conta sacarConta(Long id, SaqueRequestDTO saqueRequestDTO){
        Conta conta = contaRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradoException(id));

        conta.sacar(saqueRequestDTO.valorSaque());
        return contaRepository.save(conta);
    }

   public ContaResponseDTO transferenciaConta(TransferenciaRequestDTO transferenciaRequestDTO){
        Conta contaSaida = contaRepository.findByNumeroConta(transferenciaRequestDTO.ContaDestino()).get();
        Conta contaDestino = contaRepository.findByNumeroConta(transferenciaRequestDTO.ContaSaida()).get();
        
        contaSaida.transferencia(transferenciaRequestDTO.valorTransferencia(), contaDestino);


        return ContaResponseDTO.fromEntity(contaRepository.save(contaDestino));

   }

}


