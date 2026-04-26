package br.com.fiap.meubolso;

import br.com.fiap.meubolso.Usuario;
import br.com.fiap.meubolso.Endereco;
import java.time.LocalDate;
import java.util.Scanner;

public class UsuarioView {
    public static Usuario cadastrarUsuario() {
        Usuario usuario = new Usuario();
        Scanner ed = new Scanner(System.in);
        System.out.print("Nome: ");
        usuario.setNome(ed.nextLine());
        //Usei isso para não precisar mudar o padrão da data americana para o brasileiro
        System.out.print("Dia do Nascimento: ");
        int dia = Integer.parseInt(ed.nextLine());
        System.out.print("Mês do Nascimento: ");
        int mes = Integer.parseInt(ed.nextLine());
        System.out.print("Ano do Nascimento: ");
        int ano = Integer.parseInt(ed.nextLine());
        //Construindo a data
        LocalDate data = LocalDate.of(ano, mes, dia);
        //informando a data
        usuario.setDataNascimento(data);
        System.out.print("CPF: ");
        usuario.setCpf(ed.nextLine());
        System.out.print("E-mail: ");
        usuario.setEmail(ed.nextLine());
        System.out.print("Telefone: ");
        usuario.setTelefone(ed.nextLine());
        //ENDEREÇO
        Endereco endereco = new Endereco();
        System.out.println("Cadastrando Endereço:");
        System.out.print("Rua/Av: ");
        endereco.setLogradouro(ed.nextLine());
        System.out.print("Nº: ");
        endereco.setNumero(Integer.parseInt(ed.nextLine()));
        System.out.print("Complemento: ");
        endereco.setComplemento(ed.nextLine());
        System.out.print("CEP: ");
        endereco.setCep(ed.nextLine());
        System.out.print("Cidade: ");
        endereco.setCidade(ed.nextLine());
        System.out.print("Estado: ");
        endereco.setEstado(ed.nextLine());
        //VINCULANDO INFORMAÇÕES DO ENDEREÇO
        usuario.setEndereco(endereco);
        //DEFININDO DATA DE CADASTRO
        usuario.setDataCadastro(LocalDate.now());

        return usuario;
    }
}
