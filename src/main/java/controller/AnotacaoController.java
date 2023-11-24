package controller;

import model.Anotacao;
import repository.AnotacaoDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class AnotacaoController {
    private final AnotacaoDAO anotacaoDAO;

    public AnotacaoController() {
        this.anotacaoDAO = new AnotacaoDAO();
    }

    public List<Anotacao> listarAnotacoes() throws SQLException {
        return anotacaoDAO.listar();
    }

    public void adicionarAnotacao(Anotacao anotacao) throws SQLException {
        anotacaoDAO.inserir(anotacao);
    }

    public Anotacao obterAnotacao(String id) throws SQLException {
        return anotacaoDAO.obter(UUID.fromString(id));
    }

    public void editarAnotacao(Anotacao anotacao) throws SQLException {
        anotacaoDAO.editar(anotacao);
    }

    public void excluirAnotacao(String id) throws SQLException {
        anotacaoDAO.excluir(UUID.fromString(id));
    }

    public List<Anotacao> listarAnotacoesLixeira() throws SQLException {
        return anotacaoDAO.listarLixeira();
    }

    public void esvaziarLixeira() throws SQLException {
        anotacaoDAO.esvaziarLixeira();
    }

    public void moverParaLixeira(String id) throws SQLException {
        anotacaoDAO.moverParaLixeira(UUID.fromString(id));
    }

    public void restaurarDaLixeira(String id) throws SQLException {
        anotacaoDAO.restaurarDaLixeira(UUID.fromString(id));
    }

    public void duplicarAnotacao(String id) throws SQLException {
        anotacaoDAO.duplicar(UUID.fromString(id));
    }

    public List<Anotacao> ordenarAnotacoesPorDataCriacao() throws SQLException {
        return anotacaoDAO.ordenarPorDataCriacao();
    }


    public List<Anotacao> ordenarAnotacoesPorTitulo() throws SQLException {
       return anotacaoDAO.ordenarPorTitulo();
    }
}
