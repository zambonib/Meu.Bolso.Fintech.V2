package br.com.fiap.meubolso;

import br.com.fiap.meubolso.dao.UsuarioDAO; // Import obrigatório
import br.com.fiap.meubolso.model.Conta;
import br.com.fiap.meubolso.model.ContaView;
import br.com.fiap.meubolso.model.Usuario;
import br.com.fiap.meubolso.model.UsuarioView;

import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        // MUDANÇA: Em vez de ArrayList, usamos o DAO
        UsuarioDAO dao = new UsuarioDAO();

        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n========================================");
            System.out.printf("%-10s %s %n", "", "FINTECH - MEU BOLSO (DB)");
            System.out.println("========================================");
            System.out.println("1 - Cadastrar Usuario no Banco");
            System.out.println("2 - Listar usuários do Banco");
            System.out.println("3 - Criar conta");
            System.out.println("4 - Saque");
            System.out.println("0 - Sair");
            System.out.print("Escolha sua opção: ");

            escolha = entrada.nextInt();

            switch (escolha) {
                case 1:
                    Usuario novo = UsuarioView.cadastrarUsuario();
                    // MUDANÇA: O ID agora quem gera é a SEQUENCE do Oracle (SQ_USUARIO)
                    dao.insert(novo); // Salva direto no banco!
                    break;

                case 2:
                    // MUDANÇA: Busca os dados atualizados do banco
                    List<Usuario> listaDoBanco = dao.getAll();

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
                    // Para o vínculo de conta no banco, você precisaria de um ContaDAO.
                    // Se o prazo está curto, foque em mostrar o CRUD de Usuário funcionando no banco.
                    System.out.println("Funcionalidade em migração para o Banco de Dados...");
                    break;

                case 4:
                    System.out.println("Funcionalidade em migração para o Banco de Dados...");
                    break;
            }
        }
        entrada.close();
    }
}