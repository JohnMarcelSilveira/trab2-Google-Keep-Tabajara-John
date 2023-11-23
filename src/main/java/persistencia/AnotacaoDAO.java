package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.UUID;
import model.Anotacao;

public class AnotacaoDAO {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Use o mesmo formato do banco de dados



    public void inserir(Anotacao anotacao) throws SQLException{
        String sql = "insert into anotacao (id, titulo, descricao, data_criacao, data_edicao, foto, cor) values (?, ?, ?, ?, ?, ?, ?);";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, anotacao.getId());
        instrucaoSQL.setString(2, anotacao.getTitulo());
        instrucaoSQL.setString(3, anotacao.getDescricao());        
        instrucaoSQL.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now().format(formatter)));
        instrucaoSQL.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now().format(formatter)));
        instrucaoSQL.setBytes(6, anotacao.getFoto());
        instrucaoSQL.setString(7, anotacao.getCor());
        instrucaoSQL.executeUpdate();

        instrucaoSQL.close();
        connection.close();
    }

    public Anotacao obter(String id) throws SQLException {
        Anotacao anotacao = new Anotacao();
        UUID uuid = UUID.fromString(id);
        String sql = "select * from anotacao where id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, uuid);
        ResultSet rs = instrucaoSQL.executeQuery();
        if (rs.next()) {
            anotacao.setId((UUID)rs.getObject("id"));
            anotacao.setTitulo(rs.getString("titulo"));
            anotacao.setDescricao(rs.getString("descricao"));
            anotacao.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime().format(formatter));
            anotacao.setDataEdicao(rs.getTimestamp("data_edicao").toLocalDateTime().format(formatter));
            anotacao.setFoto(rs.getBytes("foto"));
            anotacao.setLixeira(rs.getBoolean("lixeira"));
            
            Timestamp timestampDataExclusao = rs.getTimestamp("data_exclusao");
            anotacao.setDataExclusao(timestampDataExclusao != null ? timestampDataExclusao.toLocalDateTime().format(formatter) : null);

            
            anotacao.setCor(rs.getString("cor"));  
        }

        instrucaoSQL.close();
        connection.close();

        return anotacao;
    }

    public boolean excluir(String id) throws SQLException {
        UUID uuid = UUID.fromString(id);

        String sql = "DELETE from anotacao WHERE id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setObject(1, uuid);
        int resultado = instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
        return resultado == 1;
    }

    public boolean editar(Anotacao anotacao) throws SQLException {
        String sql = "UPDATE anotacao SET titulo = ?, descricao = ?, data_criacao = ?, data_edicao = ?, foto = ?, cor = ? WHERE id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setString(1, anotacao.getTitulo());
        instrucaoSQL.setString(2, anotacao.getDescricao());
        instrucaoSQL.setTimestamp(3, Timestamp.valueOf(LocalDateTime.parse(anotacao.getDataCriacao()).format(formatter)));
        instrucaoSQL.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setBytes(5, anotacao.getFoto());
        instrucaoSQL.setString(6, anotacao.getCor());
        instrucaoSQL.setObject(7, anotacao.getId());
        int resultado = instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
        return resultado == 1;
    }

    public void duplicar(String id) throws SQLException {
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

    public void excluirParaLixeira(String id) throws SQLException {
        UUID uuid = UUID.fromString(id);
        String sql = "update anotacao set lixeira = true, data_exclusao = ? where id = ?;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        instrucaoSQL.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        instrucaoSQL.setObject(2, uuid);
        instrucaoSQL.executeUpdate();
        instrucaoSQL.close();
        connection.close();
    }

    public ArrayList<Anotacao> listar() throws SQLException{
        ArrayList<Anotacao> anotacoes = new ArrayList<Anotacao>();
        String sql = "select * from anotacao;";
        Connection connection = new ConexaoPostgreSQL().getConexao();

        PreparedStatement instrucaoSQL = connection.prepareStatement(sql);
        ResultSet rs = instrucaoSQL.executeQuery();
        
        while (rs.next()) {
            Anotacao anotacao = new Anotacao();
            anotacao.setId((UUID)rs.getObject("id"));
            anotacao.setTitulo(rs.getString("titulo"));
            anotacao.setDescricao(rs.getString("descricao"));
            anotacao.setDataCriacao(rs.getTimestamp("data_criacao").toLocalDateTime().format(formatter));
            anotacao.setDataEdicao(rs.getTimestamp("data_edicao").toLocalDateTime().format(formatter));
            anotacao.setFoto(rs.getBytes("foto"));
            anotacao.setLixeira(rs.getBoolean("lixeira"));
            
            Timestamp timestampDataExclusao = rs.getTimestamp("data_exclusao");
            anotacao.setDataExclusao(timestampDataExclusao != null ? timestampDataExclusao.toLocalDateTime().format(formatter) : null);

            
            anotacao.setCor(rs.getString("cor"));  
            anotacoes.add(anotacao);
        }

        instrucaoSQL.close();
        connection.close();

        return anotacoes;
    }

}