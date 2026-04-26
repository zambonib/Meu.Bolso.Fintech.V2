package br.com.fiap.meubolso;

import java.util.Scanner;
import java.time.LocalDate;

public class ContaView {
    public static Conta prepararConta() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Abertura de Conta ---");
        System.out.println("1 - Conta Corrente | 2 - Poupança");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Agência: ");
        String agencia = sc.nextLine();
        System.out.print("Número da Conta: ");
        String numero = sc.nextLine();

        if (tipo == 1) {
            System.out.print("Informe o Limite de Débito Aprovado (Será seu saldo inicial): ");
            double debito = Double.parseDouble(sc.nextLine());
            // O saldo será igual ao limite internamente no construtor da ContaCorrente
            return new ContaCorrente(1, numero, agencia, LocalDate.now(), debito, 0.0);
        } else {
            System.out.print("Valor do Depósito Inicial: ");
            double saldoInicial = Double.parseDouble(sc.nextLine());
            return new ContaPoupanca(1, numero, agencia, saldoInicial, LocalDate.now());
        }
    }
}