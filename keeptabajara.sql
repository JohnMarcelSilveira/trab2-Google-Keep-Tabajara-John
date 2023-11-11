DROP DATABASE IF EXISTS keep_tabajara;

CREATE DATABASE keep_tabajara;

\c keep_tabajara;

CREATE TABLE cor(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(20) NOT NULL
);

CREATE TABLE anotacao(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    titulo VARCHAR(50) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_edicao TIMESTAMP,
    foto BYTEA,
    lixeira BOOLEAN DEFAULT FALSE,
    data_exclusao TIMESTAMP,
    cor_id UUID REFERENCES cor(id)    
);

-- 1
INSERT INTO cor (nome) VALUES 
('Vermelho'),
('Azul'),
('Verde'),
('Amarelo'),
('Preto'),
('Branco');
