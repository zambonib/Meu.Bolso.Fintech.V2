package br.com.fiap.meubolso.model;

import java.time.LocalDate;

public abstract class Conta {
    //Atributos
    private int id;
    private String numeroConta;
    private String agencia;
    protected double saldo;
    private LocalDate dataCriacao;
    //CONSTRUTOR
    public Conta() {
    }
    //CONSTRUTOR COM PARAMETROS
    public Conta(int id, String numeroConta, String agencia, double saldo, LocalDate dataCriacao) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
    }
    //METODOS
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado.");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) this.saldo += valor;
    }

    public double getSaldo() { return saldo;
    }
}
