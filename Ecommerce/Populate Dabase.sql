INSERT INTO usuarios
(nome, apelido, cpf, data_nasc, telefone, email, senha, criado_em, modificado_em)
VALUES
("Alan", "alan", "1234567891234", NOW(), '11987654321', 'a@a.com', '1234', NOW(), NOW()),
("Bruno", "bruno", "1234567891234", NOW(), '11987654321', 'b@a.com', '1234', NOW(), NOW()),
("Carlos", "carlos", "1234567891234", NOW(), '11987654321', 'c@a.com', '1234', NOW(), NOW()),
("Daniel", "Daniel", "1234567891234", NOW(), '11987654321', 'd@a.com', '1234', NOW(), NOW());

INSERT INTO produtos
(modelo, marca, descricao, tamanho, preco, estoque, criado_em, modificado_em)
VALUES
("Tenis Nike", "Nike", "Tenis Nike descrição", "42,40,38,37", 150.5, 50, NOW(), NOW()),
("Tenis Olimp", "Olimp", "Tenis Olimp descrição", "42,40,38,37", 150.5, 50, NOW(), NOW()),
("Tenis Hue", "Hue", "Tenis Hue descrição", "42,40,38,37", 150.5, 50, NOW(), NOW()),
("Tenis BR", "BR", "Tenis BR descrição", "42,40,38,37", 150.5, 50, NOW(), NOW()),
("Tenis brb3", "brb3", "Tenis brb3 descrição", "42,40,38,37", 150.5, 50, NOW(), NOW()),
("Camiseta PUMA", "PUMA", "Camiseta PUMA descrição", "GG,G,M", 250.5, 50, NOW(), NOW()),
("Camiseta Nike", "Nike", "Camiseta Nike descrição", "GG,G,M", 250.5, 50, NOW(), NOW()),
("Camiseta Olimpi", "Olimpi", "Camiseta Olimpi descrição", "GG,G,M", 250.5, 50, NOW(), NOW()),
("Camiseta BRBR3", "BRBR3", "Camiseta BRBR3 descrição", "GG,G,M", 250.5, 50, NOW(), NOW()),
("Relogio de Corrida", "Run", "Relogio de Corrida descrição", NULL, 1050.5, 50, NOW(), NOW()),
("Relogio de Corrida", "Apple", "Relogio de Corrida descrição", NULL, 1050.5, 50, NOW(), NOW()),
("Relogio de Corrida", "Samsung", "Relogio de Corrida descrição", NULL, 1050.5, 50, NOW(), NOW()),
("Boné Nike", "Nike", "Tenis Nike descrição", NULL, 150.5, 50, NOW(), NOW()),
("Boné Aba reta", "Aba reta", "Tenis Aba reta descrição", NULL, 150.5, 50, NOW(), NOW());


INSERT INTO enderecos
(id_usuario, numero, rua, cidade, estado, cep, criado_em, modificado_em, padrao)
VALUES
(1, 1, "Av. Paulista", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 0),
(1, 1, "Av. Paulista2", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 1),
(2, 1, "Av. Paulista", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 0),
(2, 1, "Av. Paulista2", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 1),
(3, 1, "Av. Paulista", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 0),
(3, 1, "Av. Paulista2", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 1),
(4, 1, "Av. Paulista", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 0),
(4, 1, "Av. Paulista2", "São Paulo", "São Paulo", "01311-200", NOW(), NOW(), 1);

INSERT INTO pedidos
(id_usuario, status_pedido, criado_em, modificado_em)
VALUES
(1, 0, NOW(),NOW()),
(2, 0, NOW(),NOW()),
(3, 0, NOW(),NOW()),
(4, 0, NOW(),NOW()),
(1, 1, NOW(),NOW()),
(2, 1, NOW(),NOW()),
(3, 1, NOW(),NOW()),
(4, 1, NOW(),NOW()),
(1, 2, NOW(),NOW()),
(2, 2, NOW(),NOW()),
(3, 2, NOW(),NOW()),
(4, 2, NOW(),NOW()),
(1, 3, NOW(),NOW()),
(2, 3, NOW(),NOW()),
(3, 3, NOW(),NOW()),
(4, 3, NOW(),NOW()),
(1, 4, NOW(),NOW()),
(2, 4, NOW(),NOW()),
(3, 4, NOW(),NOW()),
(4, 4, NOW(),NOW()),
(1, 5, NOW(),NOW()),
(2, 5, NOW(),NOW()),
(3, 5, NOW(),NOW()),
(4, 5, NOW(),NOW()),
(1, 6, NOW(),NOW()),
(2, 6, NOW(),NOW()),
(3, 6, NOW(),NOW()),
(4, 6, NOW(),NOW());


INSERT INTO items_pedido
(id_pedido, id_produto, preco, quantidade)
VALUES
( 1, 1, 150.5, 1),
( 2, 1, 150.5, 1),
( 3, 1, 150.5, 1),
( 4, 1, 150.5, 1),
( 5, 1, 150.5, 1),
( 6, 1, 150.5, 1),
( 7, 1, 150.5, 1),
( 8, 1, 150.5, 1),
( 9, 1, 150.5, 1),
( 10, 1, 150.5, 1),
( 11, 1, 150.5, 1),
( 12, 1, 150.5, 1),
( 13, 1, 150.5, 1),
( 14, 1, 150.5, 1),
( 15, 1, 150.5, 1),
( 16, 1, 150.5, 1),
( 17, 1, 150.5, 1),
( 18, 1, 150.5, 1),
( 19, 1, 150.5, 1),
( 20, 1, 150.5, 1),
( 21, 1, 150.5, 1),
( 22, 1, 150.5, 1),
( 23, 1, 150.5, 1),
( 24, 1, 150.5, 1),
( 25, 1, 150.5, 1),
( 26, 1, 150.5, 1),
( 27, 1, 150.5, 1),
( 28, 1, 150.5, 1);
