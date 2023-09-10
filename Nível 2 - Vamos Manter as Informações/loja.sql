insert into Usuario (login, senha) values ('op1', 'op1');
insert into Usuario (login, senha) values ('op2', 'op2');

select * from Usuario;


insert into Produto (nome, quantidade, precoVenda) values 
('uva', '50', '4.99'),
('pera', '10', '2.50'),
('kiwi', '10', '3.00'),
('abacaxi', '20', '2.70'),
('melancia', '1', '1.90');

select * from Produto;


DECLARE @NextValue INT
SELECT @NextValue = NEXT VALUE FOR idPessoaSequence

insert into Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) values 
(@NextValue, 'Luke', 'SQN 312, Bloco B, APT 111, Asa Norte', 'Brasilia', 'DF', '4444-2121', 'luke@skywalker.com');

insert into PessoaFisica(Pessoa_idPessoa, cpf) values 
(@NextValue, '57489632158');

--DECLARE @NextValue INT
SELECT @NextValue = NEXT VALUE FOR idPessoaSequence

insert into Pessoa (idPessoa, nome, logradouro, cidade, estado, telefone, email) values 
(@NextValue, 'Leia', 'SQS 409, Bloco S, APT 208, Asa Sul', 'Brasilia', 'DF', '6666-1414', 'leia@skywalker.com');

insert into PessoaJuridica(Pessoa_idPessoa, cnpj) values 
(@NextValue, '01198145378002');


insert into Movimento (Usuario_idUsuario, Pessoa_idPessoa, Produto_idProduto, quantidadeProduto, tipo, precoUnitario) values 
(1, 1, 1, 50, 'S', 7.78),
(3, 2, 2, 20, 'E', 4.55),
(2, 4, 3, 10, 'S', 9.21),
(8, 5, 4, 10, 'E', 3.14),
(9, 8, 5, 40, 'S', 2.99),
(4, 3, 6, 30, 'E', 1.98);

select * from Movimento

select * from Pessoa 
inner join PessoaFisica on (Pessoa.idPessoa = PessoaFisica.Pessoa_idPessoa);


select * from Pessoa 
inner join PessoaJuridica on (Pessoa.idPessoa = PessoaJuridica.Pessoa_idPessoa);


select 
	p.nome, 
	pe.nome, 
	m.quantidadeProduto, 
	m.precoUnitario, 
	m.quantidadeProduto * m.precoUnitario as ValorTotal 
from Movimento as m
inner join Produto as p on (m.Produto_idProduto = p.idProduto)
inner join Pessoa as pe on (m.Pessoa_idPessoa = pe.idPessoa)
where tipo = 'E'


select 
	p.nome, 
	pe.nome, 
	m.quantidadeProduto, 
	m.precoUnitario, 
	m.quantidadeProduto * m.precoUnitario as ValorTotal 
from Movimento as m
inner join Produto as p on (m.Produto_idProduto = p.idProduto)
inner join Pessoa as pe on (m.Pessoa_idPessoa = pe.idPessoa)
where tipo = 'S'


select p.nome, sum(quantidadeProduto * precoUnitario) as ValorTotal 
from Movimento as m
inner join Produto as p on (m.Produto_idProduto = p.idProduto)
where m.tipo = 'E'
group by p.nome


select p.nome, sum(quantidadeProduto * precoUnitario) as ValorTotal 
from Movimento as m
inner join Produto as p on (m.Produto_idProduto = p.idProduto)
where m.tipo = 'S'
group by p.nome


select u.idUsuario, u.login from 
	(select idUsuario from Usuario
	EXCEPT   
	select Usuario_idUsuario from Movimento as m
	where m.tipo = 'E') as UserSemMovimento
inner join Usuario as u on (u.idUsuario = UserSemMovimento.idUsuario)


select u.login, sum(quantidadeProduto * precoUnitario) as ValorTotal 
from Movimento as m
inner join Usuario as u on (m.Usuario_idUsuario = u.idUsuario)
where m.tipo = 'E'
group by u.login


select u.login, sum(quantidadeProduto * precoUnitario) as ValorTotal 
from Movimento as m
inner join Usuario as u on (m.Usuario_idUsuario = u.idUsuario)
where m.tipo = 'S'
group by u.login


select p.nome,  sum(quantidadeProduto * precoUnitario)/sum(quantidadeProduto) as ValorTotal 
from Movimento as m
inner join Produto as p on (m.Produto_idProduto = p.idProduto)
where m.tipo = 'S'
group by p.nome

select * from Movimento