package com.picpay.controller;

import com.picpay.dto.TransacaoDTO;
import com.picpay.entity.LogistaEntity;
import com.picpay.entity.TipoUsuario;
import com.picpay.entity.UsuarioEntity;
import com.picpay.repository.LogistaRepository;
import com.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferir")
public class TransacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LogistaRepository logistaRepository;

    @PostMapping("/enviar-dinheiro")
    public ResponseEntity<String> enviarDinheiro(@RequestBody TransacaoDTO transacaoDTO) {
        // Validações e lógica de negócios
        UsuarioEntity usuario = usuarioRepository.findById(transacaoDTO.idUsuario()).orElse(null);
        LogistaEntity logista = logistaRepository.findById(transacaoDTO.idLogista()).orElse(null);
        
        if (usuario == null || logista == null) {
            return ResponseEntity.badRequest().body("Usuário ou logista não encontrado.");
        }

        if (usuario.getTipo() != TipoUsuario.USUARIO) {
            return ResponseEntity.badRequest().body("Apenas usuários podem enviar dinheiro.");
        }

        if (usuario.getSaldoDisponivel().compareTo(transacaoDTO.valor()) < 0) {
            return ResponseEntity.badRequest().body("Saldo insuficiente para a transação.");
        }

        // Executar a transação
        usuario.setSaldoDisponivel(usuario.getSaldoDisponivel().subtract(transacaoDTO.valor()));
        logista.setSaldoDisponivel(logista.getSaldoDisponivel().add(transacaoDTO.valor()));

        usuarioRepository.save(usuario);
        logistaRepository.save(logista);

        return ResponseEntity.ok("Transação concluída com sucesso.");
    }


}
