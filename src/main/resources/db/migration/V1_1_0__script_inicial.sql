
    create table if not exists usuarios (
       id SERIAL not null,
       foto varchar(200),
       nome varchar(100) not null,
       senha varchar(100) not null,
       usuario varchar(40) not null,
       primary key (id)
    );

    create table if not exists clientes(
   	    id SERIAL not null,
   		contato varchar(12),
        nome varchar(100),
        usuario_id SERIAL,
        primary key (id),
        constraint fk_usuario foreign key (usuario_id) references usuarios(id)
        );

    create table if not exists veiculos (
       id SERIAL not null,
        mensalidade float4,
        nome varchar(100),
        placa varchar(9),
        tipos_veiculos varchar(15),
        uber boolean,
        vencimento_boleto varchar(2),
        cliente_id SERIAL,
        primary key (id),
        constraint fk_cliente foreign key (cliente_id) references clientes(id)
    );

    create index idx_cliente on clientes (id);

    create index idx_veiculo on veiculos (id);

    create index idx_nome_cliente on clientes (nome);