package br.com.fiap.meubolso.model;

import java.time.LocalDate;

public class ContaPoupanca extends Conta {

    // CONSTRUTOR PADRÃO
    public ContaPoupanca() {
        super();
    }

    // CONSTRUTOR COM PARAMETROS (Corrigido para incluir o Usuario)
    public ContaPoupanca(int id, String numeroConta, String agencia, double saldo, LocalDate dataCriacao, Usuario usuario) {
        // Agora o super recebe o usuario para bater com a classe Conta
        super(id, numeroConta, agencia, saldo, dataCriacao, usuario);
    }

    // METODOS
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