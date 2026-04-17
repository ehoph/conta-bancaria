package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.ContaRequestDTO;
import com.senai.conta_bancaria.application.dto.ContaResponseDTO;
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

}
