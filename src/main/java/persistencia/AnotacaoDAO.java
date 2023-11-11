package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import model.Anotacao;

public class AnotacaoDAO {


    public void inserir(Anotacao anotacao) throws SQLException{
        String sql = "insert into anotacao (id, titulo, descricao, data_criacao, data_edicao, foto, cor_id) values (?, ?, ?, ?, ?, ?, ?);";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, anotacao.getId());
        instrucaoSQL.setString(2, anotacao.getTitulo());
        instrucaoSQL.setString(3, anotacao.getDescricao());        
        instrucaoSQL.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setBytes(6, anotacao.getFoto());
        instrucaoSQL.setObject(7, anotacao.getCorId());
        instrucaoSQL.executeUpdate();

        instrucaoSQL.close();
        connection.close();
    }

    public Anotacao obter(UUID id) throws SQLException {
        Anotacao anotacao = new Anotacao();
        String sql = "select * from anotacao where id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, id);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            anotacao.setId((UUID)rs.getObject("id"));
            anotacao.setTitulo(rs.getString("titulo"));
            anotacao.setDescricao(rs.getString("descricao"));
            anotacao.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime());
            anotacao.setDataEdicao(rs.getTimestamp("data_edicao").toLocalDateTime());
            anotacao.setFoto(rs.getBytes("foto"));
            anotacao.setLixeira(rs.getBoolean("lixeira"));
            
            Timestamp timestampDataExclusao = rs.getTimestamp("data_exclusao");
            anotacao.setDataExclusao(timestampDataExclusao != null ? timestampDataExclusao.toLocalDateTime() : null);

            
            anotacao.setCorId((UUID)rs.getObject("cor_id"));  
        }

        instrucaoSQL.close();
        connection.close();

        return anotacao;
    }

    public boolean deletar(UUID id) throws SQLException {

        String sql = "DELETE from anotacao WHERE id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, id);
        int resultado = instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
        return resultado == 1;
    }

    public boolean atualizar(Anotacao anotacao) throws SQLException {
        String sql = "UPDATE anotacao SET titulo = ?, descricao = ?, data_criacao = ?, data_edicao = ?, foto = ?, cor_id = ? WHERE id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setString(1, anotacao.getTitulo());
        instrucaoSQL.setString(2, anotacao.getDescricao());
        instrucaoSQL.setTimestamp(3, Timestamp.valueOf(anotacao.getDataCriacao()));
        instrucaoSQL.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setBytes(5, anotacao.getFoto());
        instrucaoSQL.setObject(6, anotacao.getCorId());
        int resultado = instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
        return resultado == 1;
    }

    public void duplicar(UUID id) throws SQLException {
        Anotacao anotacao = obter(id);
        anotacao.setId(UUID.randomUUID());
        inserir(anotacao);
    }

    public void ordenarPorDataCriacao() throws SQLException {
        String sql = "select * from anotacao order by data_criacao;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString("titulo"));
        }

        instrucaoSQL.close();
        connection.close();
    }

    public void excluirParaLixeira(UUID id) throws SQLException {
        String sql = "update anotacao set lixeira = true, data_exclusao = ? where id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setObject(2, id);
        instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
    }

}