package view;

import com.google.gson.Gson;
import model.Anotacao;

public class AnotacaoView {
    private final Gson gson;

    public AnotacaoView(Gson gson) {
        this.gson = gson;
    }

    public String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
