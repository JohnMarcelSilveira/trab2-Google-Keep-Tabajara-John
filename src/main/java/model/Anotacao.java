package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Anotacao {
    private UUID id; // chave primaria 
    private String titulo;
    private String descricao;
    private String dataCriacao;
    private String dataEdicao;
    private byte[] foto;
    private boolean lixeira;
    private String dataExclusao;
    private String cor; 
    
    // getters e setters
    public UUID getId() {
        if(id == null)
            setId(UUID.randomUUID());
        return id;
    }
    
    public void setId(UUID id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public String getDataEdicao() {
        return dataEdicao;
    }
    
    public void setDataEdicao(String dataEdicao) {
        this.dataEdicao = dataEdicao;
    }
    
    public byte[] getFoto() {
        return foto;
    }
    
    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

     public boolean isLixeira() {
        return lixeira;
    }

    public void setLixeira(boolean lixeira) {
        this.lixeira = lixeira;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao) {
        this.dataExclusao = dataExclusao;
    }
    
    public String getCor() {
        return cor;
    }
    
    public void setCor(String cor) {
        this.cor = cor;
    }
}
    

