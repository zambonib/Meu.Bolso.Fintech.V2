package br.com.fiap.meubolso.model;

import java.time.LocalDate;

public abstract class Conta {
    // ATRIBUTOS
    private int id;
    private int idUsuario;
    private Usuario usuario;
    private String numeroConta;
    private String agencia;
    protected double saldo;
    private LocalDate dataCriacao;

    // CONSTRUTOR PADRÃO
    public Conta() {
    }

    // CONSTRUTOR COM PARAMETROS
    public Conta(int id, String numeroConta, String agencia, double saldo, LocalDate dataCriacao, Usuario usuario) {
        this.id = id;
        this.numeroConta = numeroConta;
        this.agencia = agencia;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.usuario = usuario;
        if (usuario != null) {
            this.idUsuario = usuario.getId();
        }
    }

    // METODOS
    public void sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado.");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    public void depositar(double valor) {
        if (valor > 0) this.saldo += valor;
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if (usuario != null) {
            this.idUsuario = usuario.getId();
        }
    }

    public String getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(String numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}