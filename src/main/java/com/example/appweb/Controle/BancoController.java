package com.example.appweb.Controle;

import com.example.appweb.model.AccountType;
import com.example.appweb.model.ContaCorrentePF;
import com.example.appweb.model.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoController {

    @Autowired
    private BancoRepository bancoRepository;

    @Autowired
    private Controller controller;

    private Long number = 0L;

    public Double sacar(Double quantidade, ContaCorrentePF conta) {
        return null;
    }

    public void delete(String name){
        bancoRepository.delete(this.consultaConta(name));
    }

    public ContaCorrentePF criarConta(String name, String accountType) throws Exception {
        ContaCorrentePF contaCorrentePF = new ContaCorrentePF();
        StringBuilder message = new StringBuilder();
        if(accountType == null){
            message.append("\nNecessário informar o tipo da conta!");
        }
        switch (accountType){
            case "POUPANCA" :
                contaCorrentePF.setAccountType(AccountType.CONTA_POUPANCA);
                break;
            case "CORRENTE" :
                contaCorrentePF.setAccountType(AccountType.CONTA_CORRENTE);
            default:
                message.append("\nTipo da conta não é suportado!");
        }
        Pessoa person = controller.findPerson(name);
        if(person != null && contaCorrentePF.getError() == null){
            number++;
            contaCorrentePF.setNumeroConta(number);
            //contaCorrentePF.setPerson(person);
            bancoRepository.save(contaCorrentePF);
        }else if(contaCorrentePF.getError() == null){
            message.append("\nPessoa ");
            message.append(name).append(" informada não foi cadastrada");
        }
        if(!message.isEmpty()){
            contaCorrentePF.setError(message.toString());
        }

        return contaCorrentePF;
    }

    public ContaCorrentePF consultaConta(String name){

        List<ContaCorrentePF> contas = (List<ContaCorrentePF>) bancoRepository.findAll();

        for(ContaCorrentePF cc : contas){
            if(cc.getPerson() != null && cc.getPerson().getName().equals(name)){

                return cc;


            }
        }
        return null;
    }



    public void depositar(Double quantidade, ContaCorrentePF conta) {
        Double total = conta.getSaldo() + quantidade ;
        conta.setSaldo(total);
    }


    public String transferir(Long contaOrigem, Long contaDestino, Double valor) {
        String message = "";
        ContaCorrentePF destino = bancoRepository.findById(contaDestino).get();
        ContaCorrentePF origem = bancoRepository.findById(contaOrigem).get();

        if(origem.getSaldo() >= valor){
            destino.setSaldo(destino.getSaldo() + valor);
            origem.setSaldo(origem.getSaldo() - valor);
            bancoRepository.save(destino);
            bancoRepository.save(origem);
            message = "A conta do(a) " + destino.getPerson().getName() + " recebeu a transferência no valor de R$ " + valor;
        }else{
            message = message + " Saldo insuficiente para a operação";
        }

        return message;
    }


    public Double consultaSaldo(ContaCorrentePF conta) {
        return conta.getSaldo();
    }
}