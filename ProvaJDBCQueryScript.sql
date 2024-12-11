create database gestao_clubes_esportivos_db;
use gestao_clubes_esportivos_db;

create table Liga (
	idLiga int primary key auto_increment not null,
	nome varchar(100) not null,
    anoFundacao int not null
);

create table Treinador (
	idTreinador int primary key auto_increment not null,
	nome varchar(100) not null,
    experiencia int not null
);

create table Clube (
	idClube int primary key auto_increment not null,
	nome varchar(100) not null,
    idTreinador int not null,
    dataFundacao varchar(20) not null,
    foreign key (idTreinador) references Treinador(idTreinador)
);

create table ClubeLiga (
	idClube int not null,
	foreign key (idClube) references Clube(idClube),
	idLiga int not null,
	foreign key (idLiga) references Liga(idLiga)
);

create table Jogador (
	idJogador int primary key auto_increment not null,
	nome varchar(100) not null,
	idade int not null,
    idClube int not null,
    posicao varchar(100) not null,
    foreign key (idClube) references Clube(idClube)
);

select * from Clube;
select * from ClubeLiga;
select * from Jogador;
select * from Treinador;
select * from Liga;

SET SQL_SAFE_UPDATES = 0;

delete from Clube;
delete from Jogador;
delete from Treinador;
delete from Liga;
delete from ClubeLiga;

#Auto Increment

alter table Clube auto_increment = 0;
alter table Jogador auto_increment = 0;
alter table Treinador auto_increment = 0;
alter table Liga auto_increment = 0;
alter table ClubeLiga auto_increment = 0;