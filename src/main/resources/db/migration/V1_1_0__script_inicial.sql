
    CREATE TABLE IF NOT EXISTS usuarios (
       id SERIAL NOT NULL,
       foto VARCHAR(200),
       nome VARCHAR(100) NOT NULL,
       senha VARCHAR(100) NOT NULL,
       usuario VARCHAR(40) NOT NULL,
       PRIMARY KEY (id)
    );

    CREATE TABLE IF NOT EXISTS users (
        id SERIAL PRIMARY KEY,
        login VARCHAR(100) NOT NULL UNIQUE,
        password VARCHAR(20) NOT NULL,
        role VARCHAR(20) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS clientes(
   	    id SERIAL NOT NULL,
   		contato VARCHAR(12),
        nome VARCHAR(100),
        usuario_id SERIAL,
        PRIMARY KEY (id),
        CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
        );

    CREATE TABLE IF NOT EXISTS imagens (
        id SERIAL PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        dados BYTEA NOT NULL
    );

    CREATE TABLE IF NOT EXISTS veiculos (
       id SERIAL NOT NULL,
        mensalidade float4,
        nome VARCHAR(100),
        placa VARCHAR(9),
        tipos_veiculos VARCHAR(15),
        uber BOOLEAN,
        vencimento_boleto VARCHAR(2),
        cliente_id SERIAL,
        PRIMARY KEY (id),
        CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES clientes(id)
    );

    CREATE INDEX IF NOT EXISTS idx_cliente ON clientes (id);

    CREATE INDEX IF NOT EXISTS idx_veiculo ON veiculos (id);

    CREATE INDEX IF NOT EXISTS idx_nome_cliente ON clientes (nome);