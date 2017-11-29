CREATE TABLE IF NOT EXISTS `usuarios` (
    id              INT NOT NULL AUTO_INCREMENT,
    nome            varchar(255),
    apelido         varchar(255),
    cpf             varchar(255),
    data_nasc       varchar(255),
    telefone        varchar(255),
    email           varchar(255) NOT NULL,
    senha           varchar(255),
    ativo           bit DEFAULT TRUE,
    criado_em       DATETIME,
    modificado_em   DATETIME,
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
    criado_em       DATETIME,
    modificado_em   DATETIME,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `enderecos` (
    id              INT NOT NULL AUTO_INCREMENT,
    id_usuario      INT NOT NULL,
    numero          INT,
    rua             varchar(255),
    cidade          varchar(255),
    estado          varchar(255),
    cep             varchar(255),
    ativo           bit DEFAULT TRUE,
    criado_em       DATETIME,
    modificado_em   DATETIME,
    padrao          bit,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS `pedidos` (
    id              INT NOT NULL AUTO_INCREMENT,
    id_usuario      INT NOT NULL,
    status_pedido   INT NOT NULL,
    criado_em       DATETIME,
    modificado_em   DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS `items_pedido` (
    id              INT NOT NULL AUTO_INCREMENT,
    id_pedido       INT NOT NULL,
    id_produto      INT NOT NULL,
    preco           float,
    quantidade      int,
    PRIMARY KEY (id),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id),
    FOREIGN KEY (id_produto) REFERENCES produtos(id)
);

CREATE TABLE IF NOT EXISTS `session` (
  hash            varchar(255) NOT NULL UNIQUE,
  id_usuario      INT NOT NULL,
  PRIMARY KEY (hash),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);
