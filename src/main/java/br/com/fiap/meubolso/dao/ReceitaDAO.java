package br.com.fiap.meubolso.dao;

import br.com.fiap.meubolso.factory.ConnectionFactory;
import br.com.fiap.meubolso.model.Receita;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO {

    public void insert(Receita receita) {
        // SQL ajustado conforme seu print: CD_RECEITA, CD_CONTA, DS_RECEITA, VL_RECEITA, DT_RECEITA
        String sql = "INSERT INTO T_RECEITA (CD_RECEITA, CD_CONTA, DS_RECEITA, VL_RECEITA, DT_RECEITA) " +
                "VALUES (SQ_RECEITA.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, receita.getIdConta()); // Referência à T_CONTA
            ps.setString(2, receita.getDescricao());
            ps.setDouble(3, receita.getValor());
            ps.setDate(4, Date.valueOf(receita.getData()));

            ps.execute();
            System.out.println("Receita '" + receita.getDescricao() + "' inserida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir receita: " + e.getMessage());
        }
    }

    public List<Receita> getAll() {
        List<Receita> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_RECEITA ORDER BY DT_RECEITA DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Receita r = new Receita();
                r.setId(rs.getInt("CD_RECEITA"));
                r.setIdConta(rs.getInt("CD_CONTA"));
                r.setDescricao(rs.getString("DS_RECEITA"));
                r.setValor(rs.getDouble("VL_RECEITA"));
                r.setData(rs.getDate("DT_RECEITA").toLocalDate());
                lista.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar receitas: " + e.getMessage());
        }
        return lista;
    }
}