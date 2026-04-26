package br.com.fiap.meubolso.model;

import java.util.Scanner;
import java.time.LocalDate;

public class ContaView {
    public static Conta prepararConta(Usuario dono) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Abertura de Conta ---");
        System.out.println("Dono da conta: " + dono.getNome());
        System.out.println("1 - Conta Corrente | 2 - Poupança");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Agência: ");
        String agencia = sc.nextLine();
        System.out.print("Número da Conta: ");
        String numero = sc.nextLine();

        if (tipo == 1) {
            System.out.print("Informe o Limite de Débito: ");
            double debito = Double.parseDouble(sc.nextLine());

            // ATENÇÃO NA ORDEM: id, numero, agencia, saldo, data, usuario, limiteDebito, limiteCredito
            // O saldo inicial será o valor do debito conforme você pediu
            return new ContaCorrente(0, numero, agencia, debito, LocalDate.now(), dono, debito, 0.0);
        } else {
            System.out.print("Valor do Depósito Inicial: ");
            double saldoInicial = Double.parseDouble(sc.nextLine());

            // ATENÇÃO NA ORDEM: id, numero, agencia, saldo, data, usuario
            return new ContaPoupanca(0, numero, agencia, saldoInicial, LocalDate.now(), dono);
        }
    }
}