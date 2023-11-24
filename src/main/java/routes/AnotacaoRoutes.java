package routes;

import com.google.gson.Gson;
import controller.AnotacaoController;
import model.Anotacao;
import spark.Spark;
import java.util.List;

public class AnotacaoRoutes {
    private final AnotacaoController anotacaoController;
    private final Gson gson;

    public AnotacaoRoutes(AnotacaoController anotacaoController, Gson gson) {
        this.anotacaoController = anotacaoController;
        this.gson = gson;
    }

    public void setupRoutes() {
        Spark.port(8080);

        Spark.get("/", (request, response) -> {
            try {
                List<Anotacao> anotacoes = anotacaoController.listarAnotacoes();
                return gson.toJson(anotacoes);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.get("/obter/:id", (request, response) -> {
            try {
                Anotacao anotacao = anotacaoController.obterAnotacao(request.params(":id"));
                return gson.toJson(anotacao);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.get("/ordenarPorDataCriacao", (request, response) -> {
            try {
                List<Anotacao> anotacoes = anotacaoController.ordenarAnotacoesPorDataCriacao();
                return gson.toJson(anotacoes);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.get("/lixeira", (request, response) -> {
            try {
                List<Anotacao> anotacoes = anotacaoController.listarAnotacoesLixeira();
                return gson.toJson(anotacoes);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.post("/adicionar", (request, response) -> {
            try {
                Anotacao anotacao = gson.fromJson(request.body(), Anotacao.class);
                anotacaoController.adicionarAnotacao(anotacao);
                return gson.toJson("Anotação adicionada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.put("/editar/:id", (request, response) -> {
            try {
                Anotacao anotacao = gson.fromJson(request.body(), Anotacao.class);
                anotacaoController.editarAnotacao(anotacao);
                return gson.toJson("Anotação editada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.put("/duplicar/:id", (request, response) -> {
            try {
                anotacaoController.duplicarAnotacao(request.params(":id"));
                return gson.toJson("Anotação duplicada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.put("/restaurar/:id", (request, response) -> {
            try {
                anotacaoController.restaurarDaLixeira(request.params(":id"));
                return gson.toJson("Anotação restaurada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.delete("/excluir/:id", (request, response) -> {
            try {
                String msgRetorno = "";
                Anotacao anotacao = anotacaoController.obterAnotacao(request.params(":id"));
                if (anotacao.isLixeira()) {
                    anotacaoController.excluirAnotacao(request.params(":id"));
                    msgRetorno = "Anotação excluída definitamente com sucesso!";
                } else {
                    anotacaoController.moverParaLixeira(request.params(":id"));
                    msgRetorno = "Anotação enviada para lixeira com sucesso!";
                }
                ;
                return gson.toJson(msgRetorno);
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });

        Spark.delete("/esvaziarLixeira", (request, response) -> {
            try {
                anotacaoController.esvaziarLixeira();
                return gson.toJson("Lixeira esvaziada com sucesso!");
            } catch (Exception e) {
                return gson.toJson(e.getMessage());
            }
        });
    }
}
