package com.senai.conta_bancaria.application.service;

import com.senai.conta_bancaria.domain.entity.Usuario;
import com.senai.conta_bancaria.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@servic avisa o spring que ele vai controlar
@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(Usuario usuario) {

        return usuarioRepository.save(usuario);
    }
}
