package br.com.fiap.meubolso;

import br.com.fiap.meubolso.dao.UsuarioDAO;
import br.com.fiap.meubolso.dao.ContaDAO; // Import novo
import br.com.fiap.meubolso.model.Conta;
import br.com.fiap.meubolso.model.ContaCorrente; // Para instanciar a conta
import br.com.fiap.meubolso.model.Usuario;
import br.com.fiap.meubolso.model.UsuarioView;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        UsuarioDAO usuarioDao = new UsuarioDAO();
        ContaDAO contaDao = new ContaDAO(); // Instanciando o DAO de conta

        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n========================================");
            System.out.printf("%-10s %s %n", "", "FINTECH - MEU BOLSO (DB)");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuario no Banco");
            System.out.println("2 - Listar usuários do Banco");
            System.out.println("3 - Criar conta vinculada");
            System.out.println("4 - Realizar Saque");
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

                    // Criando o objeto conta (Usando ContaCorrente como exemplo)
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

                        // Busca a conta específica na lista (simplificado para o teste)
                        for (Conta c : contas) {
                            if (c.getId() == idConta) {
                                c.sacar(valorSaque);
                                contaDao.update(c); // ATUALIZA o saldo no banco!
                                break;
                            }
                        }
                    }
                    break;
            }
        }
        entrada.close();
    }
}