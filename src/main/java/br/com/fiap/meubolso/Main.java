package br.com.fiap.meubolso;

import br.com.fiap.meubolso.dao.UsuarioDAO;
import br.com.fiap.meubolso.dao.ContaDAO;
import br.com.fiap.meubolso.dao.ReceitaDAO;
import br.com.fiap.meubolso.dao.DespesaDAO;
import br.com.fiap.meubolso.model.Conta;
import br.com.fiap.meubolso.model.ContaCorrente;
import br.com.fiap.meubolso.model.Usuario;
import br.com.fiap.meubolso.model.UsuarioView;
import br.com.fiap.meubolso.model.Receita;
import br.com.fiap.meubolso.model.Despesa;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

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
            System.out.printf("%-10s %s %n", "", "FINTECH - MEU BOLSO (DB)");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuario no Banco");
            System.out.println("2 - Listar usuários do Banco");
            System.out.println("3 - Criar conta vinculada");
            System.out.println("4 - Realizar Saque");
            System.out.println("5 - Testar Receitas (Cadastrar 5 e Listar)");
            System.out.println("6 - Testar Despesas (Cadastrar 5 e Listar)");
            System.out.println("0 - Sair");
            System.out.print("Escolha sua opção: ");

            escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    Usuario novo = UsuarioView.cadastrarUsuario();
                    usuarioDao.insert(novo);
                    break;

                case 2:
                    List<Usuario> listaDoBanco = usuarioDao.getAll();
                    if (listaDoBanco.isEmpty()) {
                        System.out.println("\n[ALERTA] Banco de dados vazio.");
                    } else {
                        System.out.println("\n--- RELATÓRIO DO BANCO DE DADOS ---");
                        for (Usuario u : listaDoBanco) {
                            System.out.println("ID: " + u.getId() + " | Nome: " + u.getNome() + " | CPF: " + u.getCpf());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- VINCULAR NOVA CONTA ---");
                    System.out.print("Digite o ID do usuário dono da conta: ");
                    int idUser = entrada.nextInt();

                    ContaCorrente novaConta = new ContaCorrente();
                    novaConta.setIdUsuario(idUser);

                    System.out.print("Digite o número da conta: ");
                    novaConta.setNumeroConta(entrada.next());
                    System.out.print("Digite a agência: ");
                    novaConta.setAgencia(entrada.next());
                    System.out.print("Saldo inicial: ");
                    novaConta.setSaldo(entrada.nextDouble());

                    contaDao.insert(novaConta);
                    break;

                case 4:
                    System.out.println("\n--- OPERAÇÃO DE SAQUE ---");
                    List<Conta> contas = contaDao.getAll();

                    if (contas.isEmpty()) {
                        System.out.println("[ALERTA] Nenhuma conta cadastrada.");
                    } else {
                        for (Conta c : contas) {
                            System.out.println("ID Conta: " + c.getId() + " | Saldo Atual: R$ " + c.getSaldo());
                        }
                        System.out.print("Digite o ID da conta para saque: ");
                        int idConta = entrada.nextInt();
                        System.out.print("Valor do saque: ");
                        double valorSaque = entrada.nextDouble();

                        for (Conta c : contas) {
                            if (c.getId() == idConta) {
                                c.sacar(valorSaque);
                                contaDao.update(c);
                                break;
                            }
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- TESTE DE RECEITAS (CADASTRO AUTOMÁTICO DE 5) ---");
                    System.out.print("Informe o ID da Conta para vincular as receitas: ");
                    int idContaReceita = entrada.nextInt();

                    for (int i = 1; i <= 5; i++) {
                        Receita r = new Receita();
                        r.setIdConta(idContaReceita);
                        r.setDescricao("Receita Automática " + i);
                        r.setValor(500.0 * i);
                        r.setData(LocalDate.now());
                        receitaDao.insert(r);
                    }

                    System.out.println("\n--- LISTANDO TODAS AS RECEITAS ---");
                    receitaDao.getAll().forEach(r ->
                            System.out.println("ID: " + r.getId() + " | Desc: " + r.getDescricao() + " | Valor: R$ " + r.getValor()));
                    break;

                case 6:
                    System.out.println("\n--- TESTE DE DESPESAS (CADASTRO AUTOMÁTICO DE 5) ---");
                    System.out.print("Informe o ID da Conta para vincular as despesas: ");
                    int idContaDespesa = entrada.nextInt();

                    for (int i = 1; i <= 5; i++) {
                        Despesa d = new Despesa();
                        d.setIdConta(idContaDespesa);
                        d.setDescricao("Despesa Automática " + i);
                        d.setValor(100.0 * i);
                        d.setData(LocalDate.now());
                        despesaDao.insert(d);
                    }

                    System.out.println("\n--- LISTANDO TODAS AS DESPESAS ---");
                    despesaDao.getAll().forEach(d ->
                            System.out.println("ID: " + d.getId() + " | Desc: " + d.getDescricao() + " | Valor: R$ " + d.getValor()));
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
        entrada.close();
    }
}