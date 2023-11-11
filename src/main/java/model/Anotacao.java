package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Anotacao {
    private UUID id; // chave primaria 
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;
    private byte[] foto;
    private boolean lixeira;
    private LocalDateTime dataExclusao;
    private UUID corId; // chave estrangeira
    
    // getters e setters
    public UUID getId() {
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
    
    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public LocalDateTime getDataEdicao() {
        return dataEdicao;
    }
    
    public void setDataEdicao(LocalDateTime dataEdicao) {
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

    public LocalDateTime getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDateTime dataExclusao) {
        this.dataExclusao = dataExclusao;
    }
    
    public UUID getCorId() {
        return corId;
    }
    
    public void setCorId(UUID corId) {
        this.corId = corId;
    }
}
    

