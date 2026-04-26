package br.com.fiap.meubolso.model;

import java.time.LocalDate;

public class ContaCorrente extends Conta {
    private double limitedebito;
    private double limitecredito;

    public ContaCorrente() {
        super();
    }

    // Construtor Refatorado: removi o parâmetro saldo.
    // O limitedebito é passado para o super() como saldo inicial.
    public ContaCorrente(int id, String numeroConta, String agencia, LocalDate dataCriacao, double limitedebito, double limitecredito) {
        super(id, numeroConta, agencia, limitedebito, dataCriacao);
        this.limitedebito = limitedebito;
        this.limitecredito = limitecredito;
    }

    public double getLimitedebito() { return limitedebito; }
    public void setLimitedebito(double limitedebito) { this.limitedebito = limitedebito; }
    public double getLimitecredito() { return limitecredito; }
    public void setLimitecredito(double limitecredito) { this.limitecredito = limitecredito; }

    @Override
    public void sacar(double valor) {
        // Regra: Saldo disponível + Limite de débito
        double disponivel = this.saldo + this.limitedebito;

        if (valor > 0 && valor <= disponivel) {
            this.saldo -= valor; // O saldo pode ficar negativo aqui, o que é normal em CC
            System.out.println("--- Saque Realizado ---");
            System.out.println("Valor: R$ " + valor);
            System.out.println("Saldo Atual: R$ " + this.saldo);

            if (this.saldo < 0) {
                System.out.println("Atenção: Você está utilizando seu limite de débito!");
            }
        } else {
            System.out.println("[ERRO] Saldo e limite insuficientes para o valor de R$ " + valor);
        }
    }
}