package com.picpay.controller;

import com.picpay.dto.LogistaDTO;
import com.picpay.dto.UsuarioDTO;
import com.picpay.entity.LogistaEntity;
import com.picpay.entity.UsuarioEntity;
import com.picpay.repository.LogistaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/logista")
public class LogistaController {

    @Autowired
    LogistaRepository logistaRepository;

    @GetMapping
    public ResponseEntity<?> listarTodos(){
        return new ResponseEntity<>(logistaRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LogistaEntity> cadastrarUsuario(@RequestBody LogistaDTO logistaDTO){
        LogistaEntity logistaEntity  = new LogistaEntity();
        BeanUtils.copyProperties(logistaDTO, logistaEntity);
        return new ResponseEntity<>(logistaRepository.save(logistaEntity),HttpStatus.CREATED);
    }
    @GetMapping ("/{id}")
    public  ResponseEntity<?> listarPorId(@PathVariable ("id") Long id) {
        Optional<LogistaEntity> logistaEntity = logistaRepository.findById(id);
        if (logistaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        } else {
            return new ResponseEntity(logistaEntity, HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable ("id")Long id) {
        Optional<LogistaEntity> logistaEntity = logistaRepository.findById(id);
        if (logistaEntity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        logistaRepository.delete(logistaEntity.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso.");

    }
}
