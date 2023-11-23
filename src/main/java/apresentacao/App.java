package apresentacao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Anotacao;
import persistencia.AnotacaoDAO;
import spark.Spark;
import java.sql.SQLException;
import java.util.List;

public class App {
    public static void main(String[] args) throws SQLException {
        Spark.port(8080);

        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });

        Gson gson = new GsonBuilder().create();

        Spark.get("/", (request, response) -> {
            try {
                List<Anotacao> anotacoes = new AnotacaoDAO().listar();
                return gson.toJson(anotacoes);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.post("/adicionar", (request, response) -> {
            try {
                Anotacao anotacao = gson.fromJson(request.body(), Anotacao.class);
                new AnotacaoDAO().inserir(anotacao);
                return gson.toJson("Anotação adicionada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.get("/obter/:id", (request, response) -> {
            try {
                Anotacao anotacao = new AnotacaoDAO().obter(request.params(":id"));
                return gson.toJson(anotacao);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.put("/editar/:id", (request, response) -> {
            try {
                Anotacao anotacao = gson.fromJson(request.body(), Anotacao.class);
                new AnotacaoDAO().editar(anotacao);
                return gson.toJson("Anotação editada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.delete("/excluir/:id", (request, response) -> {
            try {
                String msgRetorno = "";
                Anotacao anotacao = new AnotacaoDAO().obter(request.params(":id"));
                if(anotacao.isLixeira()){
                    new AnotacaoDAO().excluir(request.params(":id"));
                    msgRetorno = "Anotação excluída definitamente com sucesso!";
                }
                else{
                    new AnotacaoDAO().excluirParaLixeira(request.params(":id"));
                    msgRetorno = "Anotação enviada para lixeira com sucesso!";
                };
                return gson.toJson(msgRetorno);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.put("/duplicar/:id", (request, response) -> {
            try {
                new AnotacaoDAO().duplicar(request.params(":id"));
                return gson.toJson("Anotação duplicada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });        

        Spark.after((request, response) -> {
            response.type("application/json");
        });
    }
}
