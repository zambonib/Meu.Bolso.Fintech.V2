package br.com.fiap.meubolso.model;

public class Endereco {
    //Atributos
    private String logradouro;
    private int numero;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    //CONSTRUTOR PADRÃO
    public Endereco() {
    }
    //CONSTRUTOR COM PARAMETROS
    public Endereco(String logradouro, int numero, String complemento, String cep, String cidade, String estado) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }
    //GETTER AND SETTER
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    //METODOS
    public String getEnderecoCompleto() {
        return this.logradouro + ","
                + this.numero + ","
                + this.complemento + ","
                + this.cep + "," +
                this.cidade + ","
                + this.estado;
    }
}