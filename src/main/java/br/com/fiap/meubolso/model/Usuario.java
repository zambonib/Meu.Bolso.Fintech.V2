package br.com.fiap.meubolso.model;
import java.time.LocalDate;
import java.time.Period;
//Classe inicial de identificação de usuário
public class Usuario {
    // ATRIBUTOS (DADOS DO USUÁRIO)
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String email;
    private String telefone;
    private Endereco endereco;  //buscado informações da classe Endereço
    private LocalDate dataCadastro;
    private Conta conta;

    // CONSTRUTOR PADRÃO
    public Usuario() {
    }
    // CONSTRUTOR COM PARÂMETROS

    public Usuario(int id, String nome, LocalDate dataNascimento, String cpf, String email, String telefone, Endereco endereco, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;

    }
    //GETTER AND SETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public LocalDate getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }


    public Conta getConta() { return conta; }
    public void setConta(Conta conta) { this.conta = conta; }
    // MÉTODOS
    public int getIdade() {
        return Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}