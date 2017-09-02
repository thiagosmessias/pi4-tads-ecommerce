/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  thiagomessias
 * Created: Aug 27, 2017
 */

CREATE TABLE IF NOT EXISTS `users` (
    id              INT NOT NULL AUTO_INCREMENT,
    nome            varchar(255),
    sobrenome       varchar(255),
    email           varchar(255) NOT NULL,
    telefone        varchar(255),
    perfil          int DEFAULT 0,
    senha           varchar(255),
    cpf             varchar(255),
    ativo           bit,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `products` (
    id              INT NOT NULL AUTO_INCREMENT,
    modelo          varchar(255),
    marca           varchar(255),
    descricao       text,
    preco           float,
    estoque         int,
    ativo           bit,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `address` (
    user_id         INT NOT NULL,
    numero          INT,
    rua             varchar(255),
    cidade          varchar(255),
    estado          varchar(255),
    cep             varchar(255),
    ativo           bit,
    `default`       bit,
    FOREIGN KEY (user_id) REFERENCES users(id)
);