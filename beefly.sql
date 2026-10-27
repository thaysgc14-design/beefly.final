CREATE DATABASE IF NOT EXISTS beeflydb;
USE beeflydb;
-- drop database beeflydb;
CREATE TABLE IF NOT EXISTS Cliente (
 idCliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 nome VARCHAR(300) NOT NULL,
 CPF VARCHAR(20) NOT NULL UNIQUE,
 nascimento DATE NOT NULL ,
 telefone varchar(12) );

CREATE TABLE IF NOT EXISTS materias (
idmaterial INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome VARCHAR(200) NOT NULL,
quantidade INT NOT NULL,
dataVal VARCHAR(12) NOT NULL,
categoria VARCHAR(100) NULL,
obs LONGTEXT NULL
);

CREATE TABLE IF NOT EXISTS usuario (
id_usuario INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome VARCHAR(300) NOT NULL,
DataNas DATE NOT NULL,
funcao VARCHAR(15) NOT NULL,
login VARCHAR(45) NOT NULL UNIQUE,
senha CHAR(8) NOT NULL);

CREATE TABLE IF NOT EXISTS Servico (
idServico INT PRIMARY KEY  NOT NULL AUTO_INCREMENT,
nome VARCHAR(100) NOT NULL,
valor DECIMAL(5,2) NOT NULL,
tempoaten INT NOT NULL,
material INT NOT NULL,
Prestador INT NOT NULL,
CONSTRAINT FOREIGN KEY (material) REFERENCES beeflydb.materias (idmaterial),
CONSTRAINT FOREIGN KEY (Prestador) REFERENCES beeflydb.usuario (id_usuario));

CREATE TABLE IF NOT EXISTS pagamento(
idpagamento INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
valor DECIMAL(5,2) NOT NULL,
formaP VARCHAR(20) NOT NULL,
statuspg VARCHAR(45) NOT NULL);

CREATE TABLE IF NOT EXISTS caixa (
idcaixa INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
valor DECIMAL(5,2) NOT NULL,
tipo VARCHAR(10) NOT NULL,
dataHora DATETIME NOT NULL,
descricao LONGTEXT NULL);

CREATE TABLE IF NOT EXISTS atendimento (
Servico_idServico INT PRIMARY KEY NOT NULL,
Cliente_idCliente INT NOT NULL,
pretador INT NOT NULL,
caixa INT NOT NULL,
pagamento INT NOT NULL,
CONSTRAINT fk_Servico_has_Cliente_Servico1
FOREIGN KEY (Servico_idServico) REFERENCES beeflydb.Servico (idServico),
CONSTRAINT fk_Servico_has_Cliente_Cliente1 FOREIGN KEY (Cliente_idCliente)
REFERENCES beeflydb.Cliente (idCliente),
CONSTRAINT FK_prestadoAtendimento FOREIGN KEY (pretador) REFERENCES beeflydb.usuario (id_usuario),
CONSTRAINT FK_caixaatendimento FOREIGN KEY (caixa) REFERENCES beeflydb.caixa (idcaixa),
CONSTRAINT FK_pagamentoatendimento FOREIGN KEY (pagamento) REFERENCES beeflydb.pagamento (idpagamento),
CONSTRAINT FK_prestado_caixa FOREIGN KEY (pretador) REFERENCES beeflydb.caixa (idcaixa));

CREATE TABLE IF NOT EXISTS agenda (
  idagenda INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  statusate VARCHAR(45) NOT NULL,
  prestador_usua INT NOT NULL,
  Cliente INT NOT NULL,
  Servico INT NOT NULL,
  CONSTRAINT FK_Presta_agenda_atendimento FOREIGN KEY (prestador_usua) REFERENCES atendimento (pretador),
  CONSTRAINT FK_Cliente_agenda_atendimento FOREIGN KEY (Cliente) REFERENCES beeflydb.atendimento (Cliente_idCliente),
  CONSTRAINT FK_Cliente_agenda FOREIGN KEY (Cliente) REFERENCES beeflydb.Cliente (idCliente),
  CONSTRAINT FK_Servico_agenda FOREIGN KEY (Servico) REFERENCES beeflydb.Servico (idServico),
  CONSTRAINT FK_Servico_atendimento FOREIGN KEY (Servico) REFERENCES beeflydb.atendimento (Servico_idServico));
  
SELECT * FROM beeflydb.cliente;