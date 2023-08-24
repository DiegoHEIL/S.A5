package com.example.appweb.view;

import com.example.appweb.Controle.BancoController;
import com.example.appweb.model.AccountType;
import com.example.appweb.model.ContaCorrentePF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class BancoView {

    @Autowired
    private BancoController bancoController;

    @PostMapping("/criaconta")
    public ContaCorrentePF criarConta(@PathParam("nome") String name, @PathParam("type") String type) throws Exception {
        return bancoController.criarConta(name, type);
    }

    @DeleteMapping("/banco")
    public void delete(@PathParam("nome") String name){
        bancoController.delete(name);
    }

    @GetMapping("/type")
    public String listAccountType() {
        String text = AccountType.CONTA_CORRENTE + ", " + AccountType.CONTA_POUPANCA;
        return text;
    }

    @GetMapping("/consultaconta")
    public ContaCorrentePF consultaConta(@PathParam("nome") String name) {
        return bancoController.consultaConta(name);
    }

    @PutMapping("/transferir")
    public String transferir(@PathParam("contaOrigem") Long contaOrigem, @PathParam("contaDestino") Long contaDestino, @PathParam("valor") Double valor){
        return bancoController.transferir(contaOrigem, contaDestino, valor);
    }

}