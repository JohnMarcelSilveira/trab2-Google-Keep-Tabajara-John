DROP DATABASE IF EXISTS keep_tabajara;

CREATE DATABASE keep_tabajara;

\c keep_tabajara;

CREATE TABLE anotacao(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(50) NOT NULL,
    descricao TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_edicao TIMESTAMP,
    cor VARCHAR(50) DEFAULT 'FFFF00',
    foto BYTEA,
    lixeira BOOLEAN DEFAULT FALSE,
    data_exclusao TIMESTAMP    
);