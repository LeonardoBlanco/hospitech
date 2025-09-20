CREATE TABLE usuarios
(
    id VARCHAR(36) PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL UNIQUE,
    login VARCHAR(100) NOT NULL UNIQUE,
    ativo BOOLEAN NOT NULL,
    senha VARCHAR(255),
    data_alteracao DATE,
    endereco VARCHAR(500),
    role VARCHAR(50) NOT NULL
);
