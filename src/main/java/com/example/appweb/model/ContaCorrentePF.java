package com.example.appweb.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "conta")
public class ContaCorrentePF{

    @Id
    @Column(name = "numero_conta")
    private Long numeroConta;
    private Double saldo;

    @OneToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa person;
    @Column(name = "type")
    private AccountType accountType;


    //@Column
    @Transient
    private Date dataAtualizacao;
    @Transient
    private String error;

    public Pessoa getPerson() {
        return person;
    }

    public void setPerson(Pessoa person) {
        this.person = person;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

   /* public Pessoa getPerson() {
        return person;
    }

    public void setPerson(Pessoa person) {
        this.person = person;
    }
*/
    public Long getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(Long numeroConta) {
        this.numeroConta = numeroConta;
    }

    /*public Person getPessoa() {
        return pessoa;
    }

    public void setPessoa(Person pessoa) {
        this.pessoa = pessoa;
    }*/

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
}