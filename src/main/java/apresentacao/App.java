package apresentacao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.AnotacaoController;
import routes.AnotacaoRoutes;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.port(8080);

        Gson gson = new GsonBuilder().create();
        AnotacaoController anotacaoController = new AnotacaoController();
        AnotacaoRoutes anotacaoRoutes = new AnotacaoRoutes(anotacaoController, gson);

        Spark.exception(Exception.class, (exception, request, response) -> {
            exception.printStackTrace();
        });

        anotacaoRoutes.setupRoutes();

        System.out.println("Servidor rodando em http://localhost:8080");

    }
}
