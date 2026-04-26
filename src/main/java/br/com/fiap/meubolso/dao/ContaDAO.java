package br.com.fiap.meubolso.dao;

import br.com.fiap.meubolso.factory.ConnectionFactory;
import br.com.fiap.meubolso.model.Conta;
import br.com.fiap.meubolso.model.ContaCorrente;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaDAO {

    public void insert(Conta conta) {
        // SQL completo incluindo os limites que aparecem no seu print do banco
        String sql = "INSERT INTO T_CONTA (CD_CONTA, CD_USUARIO, NR_CONTA, NR_AGENCIA, VL_SALDO, DT_CRIACAO, VL_LIMITE_DEBITO, VL_LIMITE_CREDITO) " +
                "VALUES (SQ_CONTA.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conta.getIdUsuario());
            ps.setString(2, conta.getNumeroConta());
            ps.setString(3, conta.getAgencia());
            ps.setDouble(4, conta.getSaldo());

            // PROTEÇÃO CONTRA NULLPOINTEREXCEPTION:
            // Se a data estiver nula por erro na View/Construtor, ele usa a data de hoje.
            LocalDate dataParaBanco = (conta.getDataCriacao() != null) ? conta.getDataCriacao() : LocalDate.now();
            ps.setDate(5, Date.valueOf(dataParaBanco));

            // Verifica se é ContaCorrente para salvar os limites, senão salva 0
            if (conta instanceof ContaCorrente) {
                ContaCorrente cc = (ContaCorrente) conta;
                ps.setDouble(6, cc.getLimitedebito());
                ps.setDouble(7, cc.getLimitecredito());
            } else {
                ps.setDouble(6, 0.0);
                ps.setDouble(7, 0.0);
            }

            ps.execute();
            System.out.println("Conta inserida com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir conta: " + e.getMessage());
        }
    }

    public List<Conta> getAll() {
        List<Conta> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_CONTA ORDER BY CD_CONTA";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Conta c = new ContaCorrente();
                c.setId(rs.getInt("CD_CONTA"));
                c.setIdUsuario(rs.getInt("CD_USUARIO"));
                c.setNumeroConta(rs.getString("NR_CONTA"));
                c.setAgencia(rs.getString("NR_AGENCIA"));
                c.setSaldo(rs.getDouble("VL_SALDO"));

                // Trata data nula vinda do banco para não quebrar o Java
                Date dataSql = rs.getDate("DT_CRIACAO");
                if (dataSql != null) {
                    c.setDataCriacao(dataSql.toLocalDate());
                }

                lista.add(c);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contas: " + e.getMessage());
        }
        return lista;
    }

    public void update(Conta conta) {
        String sql = "UPDATE T_CONTA SET NR_CONTA = ?, NR_AGENCIA = ?, VL_SALDO = ? WHERE CD_CONTA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, conta.getNumeroConta());
            ps.setString(2, conta.getAgencia());
            ps.setDouble(3, conta.getSaldo());
            ps.setInt(4, conta.getId());

            ps.executeUpdate();
            System.out.println("Saldo atualizado no banco!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar conta: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM T_CONTA WHERE CD_CONTA = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Conta removida!");

        } catch (SQLException e) {
            System.err.println("Erro ao remover conta: " + e.getMessage());
        }
    }
}