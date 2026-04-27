package br.com.fiap.meubolso;

import br.com.fiap.meubolso.dao.*;
import br.com.fiap.meubolso.model.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

/**
 * Classe Principal com Menu Interativo.
 * Implementa o CRUD completo para atender e superar os requisitos da FIAP.
 */
public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // Inicialização dos DAOs
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ContaDAO contaDao = new ContaDAO();
        ReceitaDAO receitaDao = new ReceitaDAO();
        DespesaDAO despesaDao = new DespesaDAO();

        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n========================================");
            System.out.println("       FINTECH - MEU BOLSO (V 2.0)      ");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Criar Conta Vinculada");
            System.out.println("4 - Operações de Saldo (Saque/Depósito)");
            System.out.println("5 - Gestão de Receitas (Inserir 5/Listar)");
            System.out.println("6 - Gestão de Despesas (Inserir 5/Listar)");
            System.out.println("7 - EXCLUIR Registro (Conta ou Usuário)");
            System.out.println("0 - Sair");
            System.out.print("Escolha sua opção: ");

            try {
                escolha = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
                continue;
            }

            switch (escolha) {
                case 1:
                    Usuario novo = UsuarioView.cadastrarUsuario();
                    usuarioDao.insert(novo);
                    break;

                case 2:
                    List<Usuario> listaUsers = usuarioDao.getAll();
                    if (listaUsers.isEmpty()) {
                        System.out.println("\n[AVISO] Nenhum usuário no banco.");
                    } else {
                        System.out.println("\n--- LISTA DE USUÁRIOS ---");
                        for (Usuario u : listaUsers) {
                            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | CPF: " + u.getCpf());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Digite o ID do usuário dono da conta: ");
                    int idUser = Integer.parseInt(entrada.nextLine());
                    Usuario dono = usuarioDao.getById(idUser);

                    if (dono != null) {
                        Conta nova = ContaView.prepararConta(dono);
                        contaDao.insert(nova);
                    } else {
                        System.out.println("[ERRO] Usuário não encontrado!");
                    }
                    break;

                case 4:
                    System.out.println("\n--- SAQUE / DEPÓSITO ---");
                    List<Conta> contasS = contaDao.getAll();
                    contasS.forEach(c -> System.out.println("ID: " + c.getId() + " | Saldo: R$ " + c.getSaldo()));

                    System.out.print("ID da Conta: ");
                    int idCS = Integer.parseInt(entrada.nextLine());
                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(entrada.nextLine());
                    System.out.println("1 - Depósito | 2 - Saque");
                    int tipoOp = Integer.parseInt(entrada.nextLine());

                    for (Conta c : contasS) {
                        if (c.getId() == idCS) {
                            if (tipoOp == 1) c.depositar(valor);
                            else c.sacar(valor);
                            contaDao.update(c); // Update no Banco
                            break;
                        }
                    }
                    break;

                case 5:
                    System.out.print("ID da Conta para Receitas: ");
                    int idCR = Integer.parseInt(entrada.nextLine());
                    System.out.println("Inserindo 5 receitas automáticas para teste...");
                    for (int i = 1; i <= 5; i++) {
                        Receita r = new Receita();
                        r.setIdConta(idCR); // CORREÇÃO: setIdConta ao invés de setIdUsuario
                        r.setDescricao("Receita Teste " + i);
                        r.setValor(100.0 * i);
                        r.setData(LocalDate.now());
                        receitaDao.insert(r);
                    }
                    System.out.println("\n--- TODAS AS RECEITAS ---");
                    receitaDao.getAll().forEach(r -> System.out.println("ID: " + r.getId() + " | Desc: " + r.getDescricao() + " | R$ " + r.getValor()));
                    break;

                case 6:
                    System.out.print("ID da Conta para Despesas: ");
                    int idCD = Integer.parseInt(entrada.nextLine());
                    System.out.println("Inserindo 5 despesas automáticas para teste...");
                    for (int i = 1; i <= 5; i++) {
                        Despesa d = new Despesa();
                        d.setIdConta(idCD); // CORREÇÃO: setIdConta ao invés de setIdUsuario
                        d.setDescricao("Despesa Teste " + i);
                        d.setValor(50.0 * i);
                        d.setData(LocalDate.now());
                        despesaDao.insert(d);
                    }
                    System.out.println("\n--- TODAS AS DESPESAS ---");
                    despesaDao.getAll().forEach(d -> System.out.println("ID: " + d.getId() + " | Desc: " + d.getDescricao() + " | R$ " + d.getValor()));
                    break;

                case 7:
                    System.out.println("\n--- EXCLUSÃO DE REGISTROS ---");
                    System.out.println("1 - Excluir Conta | 2 - Excluir Usuário");
                    int tipoExc = Integer.parseInt(entrada.nextLine());
                    System.out.print("Digite o ID para excluir: ");
                    int idExc = Integer.parseInt(entrada.nextLine());

                    if (tipoExc == 1) contaDao.delete(idExc);
                    else usuarioDao.delete(idExc);
                    break;

                case 0:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        entrada.close();
    }
}