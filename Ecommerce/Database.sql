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
    id            INT NOT NULL AUTO_INCREMENT,
    nome          varchar(255),
    sobrenome     varchar(255),
    telefone      varchar(255),
    cep           varchar(255),
    perfil        int DEFAULT 0,
    senha         varchar(255),
    cpf           varchar(255),
    PRIMARY KEY (id)
);