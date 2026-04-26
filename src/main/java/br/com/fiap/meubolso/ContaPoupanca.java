package br.com.fiap.meubolso;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {
    //CONSTRUTOR
    public ContaPoupanca() {
        super();
    }
    //CONSTRUTOR COM PARAMETRO
    public ContaPoupanca(int id, String numeroConta, String agencia, double saldo, LocalDate dataCriacao) {
        super(id, numeroConta, agencia, saldo, dataCriacao);
    }
    //METODOS
    public void atualizarSaldo(double taxa) {
        this.saldo += this.saldo * (taxa / 100);
    }
    @Override
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado na Poupança.");
            System.out.println("Saldo Atual: R$ " + this.saldo);
        } else {
            System.out.println("[ERRO] A Poupança não permite saldo negativo.");
        }
    }
}
