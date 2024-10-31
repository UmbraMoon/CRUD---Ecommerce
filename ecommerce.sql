create database ecommerce;

use ecommerce;

create table Clientes (
	Cliente_ID int unique primary key,
	Nome varchar(100),
    Email varchar(100),
    Telefone varchar(15),
    Data_Cadastro date
);

create table Enderecos (
	Endereço_ID int unique primary key,
    Cliente_ID int,
    Rua varchar(50),
    Cidade varchar(50),
    Estado varchar(50),
    CEP varchar(11),
    foreign key (Cliente_ID) references Clientes(Cliente_ID)
);

create table Categorias (
	Categoria_ID int unique primary key,
    Nome varchar(100),
    Descricao varchar(200)
);

create table Fornecedores (
	Fornecedor_ID int unique primary key,
    Nome varchar(100),
    Contato varchar(50),
    Telefone varchar(11),
    Email varchar(100),
    Categoria_ID_Fornecedor int unique,
    foreign key (Categoria_ID_Fornecedor) references Categorias(Categoria_ID)
);

create table Produtos (
	Produto_ID int unique primary key,
    Nome varchar(100),
    Descricao varchar(200),
    Preco double,
    Estoque int,
    Categoria_ID int,
    Fornecedor_ID int,
	foreign key (Categoria_ID) references Categorias(Categoria_ID),
    foreign key (Fornecedor_ID) references Fornecedores(Fornecedor_ID)
);

create table Status_Pedido (
	Status_ID int unique primary key,
    Descricao varchar(200)
);

create table Vendas (
	Venda_ID int unique primary key,
    Cliente_ID_Vendas int,
    Status_ID int,
    Data_Venda date,
    foreign key (Cliente_ID_Vendas) references Clientes(Cliente_ID),
    foreign key (Status_ID) references Status_Pedido(Status_ID)
);

create table Pagamentos (
	Pagamento_ID int unique primary key,
    Venda_ID_Pagamento int,
    Data_Pagamento date,
    Valor_Pago double,
    Metodo_Pagamento varchar(50),
    foreign key (Venda_ID_Pagamento) references Vendas(Venda_ID)
);

create table Itens_Venda (
	Item_ID int unique primary key,
	Produto_ID_Itens int,
    Venda_ID int,
    Quantidade int,
    Preco_Unitario double,
    foreign key (Produto_ID_Itens) references Produtos(Produto_ID),
    foreign key (Venda_ID) references Vendas(Venda_ID)
);

DROP TABLE IF EXISTS Carrinho;

create table Carrinho (
	Carrinho_ID int unique primary key,
    Venda_ID_Carrinho int,
    Quantidade int,
    foreign key (Venda_ID_Carrinho) references Vendas(Venda_ID)
);
