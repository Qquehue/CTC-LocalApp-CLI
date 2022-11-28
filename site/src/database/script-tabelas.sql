CREATE DATABASE CTC; 

USE CTC;  

CREATE TABLE Linha (
idLinha INT PRIMARY KEY AUTO_INCREMENT,  
nomeLinha VARCHAR(45),  
situacaoLinha CHAR(10)
);  

CREATE TABLE Estacao (  
idEstacao INT PRIMARY KEY AUTO_INCREMENT,  
nomeEstacao VARCHAR(45),  
situacaoEstacao CHAR(10),  
fkLinha INT,  
FOREIGN KEY (fkLinha) REFERENCES Linha(idLinha)  
);  

CREATE TABLE Maquina (  
idMaquina INT PRIMARY KEY AUTO_INCREMENT,  
modeloCPU VARCHAR(45),  
totalMemoria DOUBLE,  
totalDisco DOUBLE,  
dataCadastro DATETIME, 
fkEstacao INT,  
FOREIGN KEY (fkEstacao) REFERENCES Estacao(idEstacao)  
);  

CREATE TABLE UsoMaquina (  
idUso INT PRIMARY KEY AUTO_INCREMENT, 
temperaturaCPU DOUBLE, 
usoCPU DOUBLE, 
usoMemoria DOUBLE, 
upTime DATETIME, 
fkMaquina INT,  
FOREIGN KEY (fkMaquina) REFERENCES Maquina(idMaquina)  
);  

CREATE TABLE Cargo (  
idCargo INT PRIMARY KEY AUTO_INCREMENT,  
nomeCargo VARCHAR(45)  
);  

CREATE TABLE Funcionario (  
idFuncionario INT PRIMARY KEY AUTO_INCREMENT,  
nomeFuncionario VARCHAR(45),  
CPF CHAR(11),  
telefone CHAR(15),  
email VARCHAR(45),  
senha VARCHAR(45),  
fkLinha INT,  
FOREIGN KEY (fkLinha) REFERENCES Linha(idLinha),  
fkCargo INT,  
FOREIGN KEY (fkCargo) REFERENCES Cargo(idCargo)  
); 

INSERT INTO Cargo (nomeCargo) VALUES 
("Analista"), 
("Tecnico"); 

INSERT INTO Linha (nomeLinha) VALUES 
("Azul"), 
("Verde"), 
("Vermelha"); 

SELECT * FROM Cargo; 

SELECT * FROM Linha; 