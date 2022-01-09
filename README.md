# Desafio Evolution Backend 2021 TQI

Dado o cenário abaixo, elabore uma sugestão para o back-end necessária para resolver o problema.
Use a criatividade! Você pode explorar aqui todos os seus conhecimentos, demonstrando fluxos, telas, protótipos e/ou codificação.
É importante sabermos por que você decidiu fazer a solução, portanto deixe clara a motivação das suas escolhas.

Uma empresa de empréstimo precisa criar um sistema de análise de crédito para fornecer aos seus clientes as seguintes funcionalidades:

1. Cadastro de clientes

O cliente pode cadastrar: nome, e-mail, CPF, RG, endereço completo, renda e senha.

2. Login

A autenticação será realizada por e-mail e senha.

3. Solicitação de empréstimo

Para solicitar um empréstimo, precisamos do valor do empréstimo, data da primeira parcela e quantidade de parcelas.

O máximo de parcelas será 60 e a data da primeira parcela deve ser no máximo 3 meses após o dia atual.

4. Acompanhamento das solicitações de empréstimo     

O cliente pode visualizar a lista de empréstimos solicitados por ele mesmo e também os detalhes de um de seus empréstimos.      

Na listagem, devemos retornar no mínimo o código do empréstimo, o valor e a quantidade de parcelas.        

No detalhe do empréstimo, devemos retornar: código do empréstimo, valor, quantidade de parcelas, data da primeira parcela, e-mail do cliente e renda do cliente.

Restrições
1. A implementação deve utilizar linguagem Java ou Kotlin.
2. Use todos os seus conhecimentos adquiridos no bootcamp para explorar bem a solução. Não se preocupe, porque não existe certo ou errado. Só queremos conhecer um pouco mais sobre você.
3. Utilize o GitHub para repositório de código.


A aplicação foi feita em Java 8 com um Banco de Dados feito no PostgreSQL.


Criação das tabelas:

    create table Usuarios
    (
	      nome varchar(200) not null,
	      cpf varchar not null,
	      rg varchar not null,
	      endereco text not null,
	      renda numeric not null, 
	      email varchar(200) not null,
	      senha varchar(200) not null,
	
	      constraint pk_email  primary key (email)
    );

    create table Emprestimos
    (
	      fk_email varchar,
	      id_emprestimos serial,
	      valor_emprestimo numeric not null,
	      primeira_parcela date not null,
	      quantidade_parcelas integer not null,
	      renda numeric not null,
	
	      constraint pk_id_emprestimos primary key (id_emprestimos), 
	
	      constraint fk_email foreign key (fk_email)
             references usuarios (email) match simple
             on update no action on delete no action
    );


