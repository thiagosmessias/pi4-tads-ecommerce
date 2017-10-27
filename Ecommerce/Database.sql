CREATE TABLE IF NOT EXISTS `usuarios` (
    id              INT NOT NULL AUTO_INCREMENT,
    nome            varchar(255),
    apelido         varchar(255),
    cpf             varchar(255),
    data_nasc       varchar(255),
    telefone        varchar(255),
    email           varchar(255) NOT NULL,
    senha           varchar(255),
    perfil          int DEFAULT 0,
    ativo           bit DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `produtos` (
    id              INT NOT NULL AUTO_INCREMENT,
    modelo          varchar(255),
    marca           varchar(255),
    descricao       text,
    tamanho         text,
    preco           float,
    estoque         int,
    ativo           bit DEFAULT TRUE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `enderecos` (
    id              INT NOT NULL AUTO_INCREMENT,
    user_id         INT NOT NULL,
    numero          INT,
    rua             varchar(255),
    cidade          varchar(255),
    estado          varchar(255),
    cep             varchar(255),
    ativo           bit DEFAULT TRUE,
    padrao          bit,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES usuarios(id)
);