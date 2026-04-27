package br.com.fiap.meubolso.dao;

import br.com.fiap.meubolso.factory.ConnectionFactory;
import br.com.fiap.meubolso.model.Despesa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DespesaDAO {

    public void insert(Despesa despesa) {
        // SQL ajustado conforme seu print: CD_DESPESA, CD_CONTA, DS_DESPESA, VL_DESPESA, DT_DESPESA
        String sql = "INSERT INTO T_DESPESA (CD_DESPESA, CD_CONTA, DS_DESPESA, VL_DESPESA, DT_DESPESA) " +
                "VALUES (SQ_DESPESA.NEXTVAL, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, despesa.getIdConta()); // Referência à T_CONTA
            ps.setString(2, despesa.getDescricao());
            ps.setDouble(3, despesa.getValor());
            ps.setDate(4, Date.valueOf(despesa.getData()));

            ps.execute();
            System.out.println("Despesa '" + despesa.getDescricao() + "' inserida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir despesa: " + e.getMessage());
        }
    }

    public List<Despesa> getAll() {
        List<Despesa> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_DESPESA ORDER BY DT_DESPESA DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Despesa d = new Despesa();
                d.setId(rs.getInt("CD_DESPESA"));
                d.setIdConta(rs.getInt("CD_CONTA"));
                d.setDescricao(rs.getString("DS_DESPESA"));
                d.setValor(rs.getDouble("VL_DESPESA"));
                d.setData(rs.getDate("DT_DESPESA").toLocalDate());
                lista.add(d);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar despesas: " + e.getMessage());
        }
        return lista;
    }
}