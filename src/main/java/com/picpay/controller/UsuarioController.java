package com.picpay.controller;

import com.picpay.dto.UsuarioDTO;
import com.picpay.entity.UsuarioEntity;
import com.picpay.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping ("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return new ResponseEntity<>(usuarioRepository.findAll(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        BeanUtils.copyProperties(usuarioDTO, usuarioEntity);
        return new ResponseEntity<>(usuarioRepository.save(usuarioEntity),HttpStatus.CREATED);
    }
    @GetMapping ("/{id}")
    public  ResponseEntity<?> listarPorId(@PathVariable ("id") Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (usuarioEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        } else {
            return new ResponseEntity(usuarioEntity, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable ("id")Long id) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
        if (usuarioEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        usuarioRepository.delete(usuarioEntity.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");

    }
}
