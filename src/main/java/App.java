import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

import model.Anotacao;
import persistencia.AnotacaoDAO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
        System.out.println(LocalDateTime.now());
        Anotacao anotacao = new Anotacao();
        anotacao.setId(UUID.randomUUID()); // Gere um UUID ou use um existente
        anotacao.setTitulo("Exemplo de Título");
        anotacao.setDescricao("Exemplo de Descrição");
        anotacao.setDataCriacao(LocalDateTime.now()); // Use a data atual
        anotacao.setDataEdicao(null); // Ajuste conforme necessário
        //anotacao.setFoto("caminho/para/foto.jpg"); // Caminho da foto ou null se não houver
        anotacao.setCorId((UUID.fromString("8273a3a2-45df-4142-a5e5-fb01edbe25a2"))); // ID da cor ou ajuste conforme necessário
        AnotacaoDAO dao = new AnotacaoDAO();
        //dao.inserir(anotacao);
        //dao.deletar((UUID.fromString("2a5d2770-4605-44c6-9128-0c45322a818c")));
        dao.duplicar(UUID.fromString("a4d3ef06-c5fe-4ea3-ac90-578af1f2586c"));

        System.out.println("foi...");
    }
}
