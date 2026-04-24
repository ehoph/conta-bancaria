package com.senai.conta_bancaria.interface_ui.controller.exception;


import com.senai.conta_bancaria.application.dto.ContaRequestDTO;
import com.senai.conta_bancaria.application.dto.ContaResponseDTO;
import com.senai.conta_bancaria.application.dto.Deposito.DepositoRequestDTO;
import com.senai.conta_bancaria.application.dto.Saque.SaqueRequestDTO;
import com.senai.conta_bancaria.application.dto.TransferenciaRequestDTO;
import com.senai.conta_bancaria.application.service.ContaService;
import com.senai.conta_bancaria.domain.entity.Conta;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    ContaService contaService;

    @PostMapping
    public ResponseEntity<ContaResponseDTO> cadastrarConta(@Valid @RequestBody ContaRequestDTO contaRequestDTO){
        ContaResponseDTO contaCadastrada = contaService.cadastrarConta(contaRequestDTO);
        return ResponseEntity.created(URI.create("/conta/"+ contaCadastrada.id())).body(contaCadastrada);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> listarConta(){
        return ResponseEntity.ok(contaService.listarContas());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarContaPorId(@PathVariable Long id){
        return ResponseEntity.ok(contaService.buscarContaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> editarConta(@PathVariable Long id, @Valid @RequestBody ContaRequestDTO contaRequestDTO){
        return ResponseEntity.ok(contaService.editarConta(id,contaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarConta(@PathVariable Long id){
        contaService.apagarConta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposito")
    public Conta depositar(@PathVariable Long id, @RequestBody DepositoRequestDTO depositoRequestDTO) {
        return contaService.depositarConta(id, depositoRequestDTO);
    }

    @PostMapping("/{id}/saque")
    public Conta sacar(@PathVariable Long id, @RequestBody SaqueRequestDTO saqueRequestDTO) {
        return contaService.sacarConta(id, saqueRequestDTO);
    }


    @PostMapping("/transferencia")
        public ContaResponseDTO transferencia(@RequestBody TransferenciaRequestDTO transferenciaRequestDTO){
            return contaService.transferenciaConta(transferenciaRequestDTO);
        }


}
