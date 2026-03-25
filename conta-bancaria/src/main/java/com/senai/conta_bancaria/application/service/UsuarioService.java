package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.application.dto.UsuarioRequestDTO;
import com.senai.conta_bancaria.application.dto.UsuarioResponseDTO;
import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//@servic avisa o spring que ele vai controlar
@Service
public class UsuarioService {


    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {


        return UsuarioResponseDTO.fromEntity(usuarioRepository.save(usuarioRequestDTO.toEntity()));
    }

    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream().map(
                        UsuarioResponseDTO::fromEntity
                ).toList();
    }
       public UsuarioResponseDTO buscarUsuarioPorId(Long id){
        return UsuarioResponseDTO.fromEntity(usuarioRepository.findById(id).get());
       }




    public UsuarioResponseDTO editarUsuario(Long id, UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuarioEditado = usuarioRepository.findById(id).get();
        usuarioEditado.setNome(usuarioRequestDTO.nome());
        usuarioEditado.setEmail(usuarioRequestDTO.email());
        usuarioEditado.setSenha(usuarioRequestDTO.senha());
        return UsuarioResponseDTO.fromEntity(usuarioRepository.save(usuarioEditado));
    }

    public void apagarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }

}
