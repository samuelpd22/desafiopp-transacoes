package com.picpay.controller;

import com.picpay.entity.LogistaEntity;
import com.picpay.entity.UsuarioEntity;
import com.picpay.repository.LogistaRepository;
import com.picpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transferir")
public class TransacaoController {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LogistaRepository logistaRepository;

    @PostMapping("/enviar-dinheiro")
    public ResponseEntity<String> enviarDinheiro(@RequestParam Long idUsuario, @RequestParam Long idLogista, @RequestParam BigDecimal valor) {
        // Validações e lógica de negócios
        UsuarioEntity usuario = usuarioRepository.findById(idUsuario).orElse(null);
        LogistaEntity logista = logistaRepository.findById(idLogista).orElse(null);

        if (usuario == null || logista == null) {
            return ResponseEntity.badRequest().body("Usuário ou logista não encontrado.");
        }

        if (usuario.getSaldoDisponivel().compareTo(valor) < 0) {
            return ResponseEntity.badRequest().body("Saldo insuficiente para a transação.");
        }

        // Executar a transação
        usuario.setSaldoDisponivel(usuario.getSaldoDisponivel().subtract(valor));
        logista.setSaldoDisponivel(logista.getSaldoDisponivel().add(valor));

        usuarioRepository.save(usuario);
        logistaRepository.save(logista);

        return ResponseEntity.ok("Transação concluída com sucesso.");
    }


}
