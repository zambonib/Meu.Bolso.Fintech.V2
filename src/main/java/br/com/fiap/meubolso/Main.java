package br.com.fiap.meubolso;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        List<Usuario> listaUsuarios = new ArrayList<>();
        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n========================================");
            System.out.printf("%-10s %s %n", "", "FINTECH - MEU BOLSO");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuario");
            System.out.println("2 - Acessar usuários");
            System.out.println("3 - Criar conta");
            System.out.println("4 - Saque");
            System.out.println("0 - Sair");
            System.out.print("Escolha sua opção: ");

            escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    Usuario novo = UsuarioView.cadastrarUsuario();
                    novo.setId(listaUsuarios.size() + 1);
                    listaUsuarios.add(novo);
                    System.out.println("\n>>> Usuário cadastrado com sucesso!");
                    break;

                case 2:
                    if (listaUsuarios.isEmpty()) {
                        System.out.println("\n[ALERTA] Nenhum usuário no sistema. Use a opção 1.");
                    } else {
                        System.out.println("\n--- RELATÓRIO DE CLIENTES ---");
                        for (Usuario u : listaUsuarios) {
                            String status = (u.getConta() != null) ? "Saldo: R$ " + u.getConta().getSaldo() : "Sem Conta";
                            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | " + status);
                        }
                    }
                    break;

                case 3:
                    if (listaUsuarios.isEmpty()) {
                        System.out.println("\n[ERRO] Não há usuários para vincular uma conta!");
                    } else {
                        // Vincula ao último cadastrado para facilitar o teste
                        Usuario selecionado = listaUsuarios.get(listaUsuarios.size() - 1);

                        if (selecionado.getConta() != null) {
                            System.out.println("\n[AVISO] " + selecionado.getNome() + " já possui conta.");
                        } else {
                            Conta nova = ContaView.prepararConta();
                            selecionado.setConta(nova);
                            System.out.println("\n>>> Conta vinculada a " + selecionado.getNome());
                        }
                    }
                    break;

                case 4: // SACAR
                    if (listaUsuarios.isEmpty()) {
                        System.out.println("\n[ALERTA] Nenhum usuário cadastrado.");
                    } else {
                        // 1. Escolher o usuário
                        System.out.println("\n--- Realizar Saque ---");
                        for (int i = 0; i < listaUsuarios.size(); i++) {
                            System.out.println(i + " - " + listaUsuarios.get(i).getNome());
                        }
                        System.out.print("Selecione o usuário: ");
                        int index = entrada.nextInt();
                        Usuario selecionado = listaUsuarios.get(index);

                        // 2. Verificar se tem conta
                        if (selecionado.getConta() == null) {
                            System.out.println("[ERRO] Este usuário não possui uma conta cadastrada.");
                        } else {
                            // 3. Pedir o valor e sacar (O Polimorfismo acontece aqui!)
                            System.out.print("Valor do saque: R$ ");
                            double valorSaque = entrada.nextDouble();

                            // O Java decide sozinho se usa o sacar() da CC ou da CP!
                            selecionado.getConta().sacar(valorSaque);
                        }
                    }
                    break;
            }
        }
        entrada.close();
    }
}