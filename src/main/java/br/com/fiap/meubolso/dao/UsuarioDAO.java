package br.com.fiap.meubolso.dao;

import br.com.fiap.meubolso.factory.ConnectionFactory;
import br.com.fiap.meubolso.model.Usuario; // Verifique se o pacote está correto
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    /**
     * CREATE - Insere um novo usuário
     */
    public void insert(Usuario usuario) {
        String sql = "INSERT INTO T_USUARIO (CD_USUARIO, NM_USUARIO, DT_NASCIMENTO, NR_CPF, DS_EMAIL, NR_TELEFONE, DT_CADASTRO) " +
                "VALUES (SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setDate(2, Date.valueOf(usuario.getDataNascimento())); // Converte LocalDate para java.sql.Date
            ps.setString(3, usuario.getCpf());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefone());
            ps.setDate(6, Date.valueOf(usuario.getDataCadastro()));

            ps.execute();
            System.out.println("Usuário inserido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    /**
     * READ - Lista todos os usuários (Exigência do Capítulo 2)
     */
    public List<Usuario> getAll() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM T_USUARIO ORDER BY CD_USUARIO";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("CD_USUARIO"));
                u.setNome(rs.getString("NM_USUARIO"));
                u.setDataNascimento(rs.getDate("DT_NASCIMENTO").toLocalDate());
                u.setCpf(rs.getString("NR_CPF"));
                u.setEmail(rs.getString("DS_EMAIL"));
                u.setTelefone(rs.getString("NR_TELEFONE"));
                u.setDataCadastro(rs.getDate("DT_CADASTRO").toLocalDate());

                lista.add(u);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
        }
        return lista;
    }

    /**
     * UPDATE - Atualiza dados do usuário
     */
    public void update(Usuario usuario) {
        String sql = "UPDATE T_USUARIO SET NM_USUARIO = ?, DS_EMAIL = ?, NR_TELEFONE = ? WHERE CD_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getTelefone());
            ps.setInt(4, usuario.getId());

            ps.executeUpdate();
            System.out.println("Usuário ID " + usuario.getId() + " atualizado!");

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    /**
     * DELETE - Remove um usuário
     */
    public void delete(int id) {
        String sql = "DELETE FROM T_USUARIO WHERE CD_USUARIO = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Usuário removido com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao excluir usuário: " + e.getMessage());
        }
    }
}