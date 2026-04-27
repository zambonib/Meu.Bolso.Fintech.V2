package br.com.fiap.meubolso.model;

import java.time.LocalDate;

public class Despesa {
    private int id;
    private int idConta;
    private String descricao;
    private double valor;
    private LocalDate data;

    // Construtor padrão
    public Despesa() {
    }

    // Construtor completo
    public Despesa(int id, int idConta, String descricao, double valor, LocalDate data) {
        this.id = id;
        this.idConta = idConta;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getIdConta() { return idConta; }
    public void setIdConta(int idConta) { this.idConta = idConta; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}