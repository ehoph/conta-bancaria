package com.senai.conta_bancaria.interface_ui.controller;


import com.senai.conta_bancaria.application.dto.UsuarioResponseDTO;
import com.senai.conta_bancaria.application.dto.UsuarioRequestDTO;
import com.senai.conta_bancaria.application.service.UsuarioService;
import com.senai.conta_bancaria.domain.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        UsuarioResponseDTO usuarioCadastrado = usuarioService.cadastrarUsuario(usuarioRequestDTO);
        return ResponseEntity.created
                (URI.create("/usuario/" + usuarioCadastrado.id())).body(usuarioCadastrado);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuario(){
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorId(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> editarUsuario( @PathVariable Long id,@RequestBody UsuarioRequestDTO usuarioRequestDTO){
        return ResponseEntity.ok(usuarioService.editarUsuario(id,usuarioRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarUsuario(@PathVariable Long id){
        usuarioService.apagarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
