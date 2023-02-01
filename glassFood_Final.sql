CREATE DATABASE Glass_Food;
use glass_food;

CREATE TABLE Cliente(
	cod_cli int primary key auto_increment,
	nome varchar(100) not null,
    telefone varchar(12) not null,
    rua varchar(30) not null,
    bairro varchar(30) not null,
    numero varchar(6) not null
);

INSERT INTO Cliente (cod_cli, nome, telefone, rua, bairro, numero) VALUES (1, 'Amanda Castro Alves', '77998546536', 'Jacinto Lobo', 'Centro', 98),
																	        (2, 'Allan Rodrigues Batista', '77999262321', 'Pará', 'Sossego', 26),
																			(3, 'Cecília Alcantra Alves', '77981295723', 'Deoclina Andrade', 'São francisco', 74),
																			(4, 'Daniela Martins de Jesus', '77999179188', 'Gertulio Vargas', 'Centro', 369),
                                                                            (5, 'Gabriel Pereira Nogueira', '77999105209', 'C', 'Monte azul', 27);
CREATE TABLE Entregador(
	cod_ent int primary key auto_increment,
	nome varchar(100) not null,
    telefone varchar(12) not null,
    veiculo varchar(30) not null,
    ocupado varchar(10) not null
);

INSERT INTO Entregador (cod_ent, nome, telefone, veiculo, ocupado) VALUES (1, 'Alex Sander da Silva Rocha', '77998178788', 'Moto', 'Disponivel'),
																			(2, 'Abgail Chaves Alves', '77981477789', 'Moto', 'Disponivel'),
                                                                            (3, 'Daniela Martins de Jesus', '77999179188', 'Moto', 'Disponivel'),
                                                                            (4, 'Marcelo Lopes Gomes', '77999221436', 'Moto', 'Disponivel'),
																	        (5, 'Wesley Brito Macedo', '77999577828', 'Moto', 'Disponivel');
																	
create table Produto(
	cod_prod int primary key auto_increment,
	nome varchar(30) not null,
    valor double(10, 2) not null,
    descricao varchar(80) not null,
    tipo varchar(10) not null
);

INSERT INTO Produto (cod_prod, nome, valor, descricao, tipo) VALUES (6, 'American bacon', 20.00, 'Pão, presunto, ovo, bacon, queijo, tomate, alface e maionese', 'Lanche'),
																		(7, 'Especial', 22.00, 'Pão, hamburguer de carne caseiro, ovo, queijo, presunto, alface e tomate ', 'Lanche'),
																		(8, 'Misto quente', 9.00, 'Pão, duas fatia de presunto, queijo, milho verde, batata palha e salada', 'Lanche'),
																		(9, 'X-calabresa', 18.00, 'Pão, calabresa, queijo, milho verde e maionese', 'Lanche'),
																		(10, 'X-bacon', 20.00, 'Pão, hamburguer de carne, bacon, queijo e maionese', 'Lanche');
                                                                        
INSERT INTO Produto (cod_prod, nome, valor, descricao, tipo) VALUES (1, 'Paulista', 60.00, 'Molho de tomate, mussarela, palmito, catupiry e tomate','Pizza'),
																	(2, 'Vegetariana', 60.00, 'Molho de tomate, mussarela, palmito, milho, tomate, ovos e azeitona', 'Pizza'),
																	(3, 'Toscana', 60.00, 'Molho de tomate, mussarella, presunto, creme de leite, milho e azeitona', 'Pizza'),
																	(4, 'Romana', 60.00, 'Molho de tomate, mussarela, presunto, bacon, ervilha, palmito e cremede leite', 'Pizza'),
																	(5, 'Quatro queijo', 60.00, 'Molho de tomate, mussarela, catupiry, gorgonzola e parmesão', 'Pizza');

INSERT INTO Produto (cod_prod, nome, valor, descricao, tipo) VALUES (11, 'Coca 2 L', 12.0, 'Coca cola 2 litro', 'Bebida'),
																		(12, 'Coca 1 L', 8.0, 'Coca cola 1 litro', 'Bebida'),
																		(13, 'Coca lata', 5.0, 'Coca lata gelada - 350 Ml', 'Bebida'),
																		(14, 'Agua Mineral', 2.50, '500 Ml', 'Bebida'),
																		(15, 'Suco', 8.0, '500 ml', 'Bebida');
																		



create table Pedido(
	cod_ped int primary key auto_increment,
    cod_cli int,
    cod_ent int,
    valor double,
    estado varchar(15),
    observacao varchar(100)
);

create table Pedido_Bebida(
	cod_ped_beb int primary key auto_increment,
	cod_ped int not null,
	cod_prod int not null,
	nome varchar(50) not null,
    valor double(10, 2) not null,
    descricao varchar(80) not null,
    tipo varchar(10) not null,
    foreign key (cod_prod) references Produto (cod_prod),
    foreign key (cod_ped) references Pedido (cod_ped)
);

create table Pedido_Lanche(
	cod_ped_lan int primary key auto_increment,
	cod_ped int not null,
	cod_prod int not null,
	nome varchar(50) not null,
    valor double(10, 2) not null,
    descricao varchar(80) not null,
    tipo varchar(10) not null,
    tipo_pao varchar(20) not null,
    ponto_carne varchar(20) not null,
    foreign key (cod_prod) references Produto (cod_prod),
    foreign key (cod_ped) references Pedido (cod_ped)
);

create table Pedido_Pizza(
	cod_ped_piz int primary key auto_increment,
	cod_ped int not null,
	cod_prod int not null,
	nome varchar(50) not null,
    valor double(10, 2) not null,
    descricao varchar(80) not null,
    tipo varchar(20) not null,
    borda varchar(20) not null,
    foreign key (cod_prod) references Produto (cod_prod),
    foreign key (cod_ped) references Pedido (cod_ped)
);

