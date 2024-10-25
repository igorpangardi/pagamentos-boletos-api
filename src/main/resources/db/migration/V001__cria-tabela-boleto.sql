create table boleto (
    id bigint not null auto_increment,
    codigo_barras varchar(80) not null,
    data_emissao datetime not null,
    data_vencimento datetime not null,
    data_pagamento datetime null,
    valor decimal(10,2) not null,
    valor_pago decimal(10,2) null,
    valor_restante decimal(10,2) null,
    status varchar(20) not null,
    nome_recebedor varchar(80) not null,
    cpf_recebedor varchar(11) not null,
    nome_pagador varchar(80) not null,
    cpf_pagador varchar(11) not null,
    juros decimal(10,2) null,
    multa decimal(10,2) null,
    desconto decimal(10,2) null,

    primary key (id)
);

alter table boleto add constraint uk_boleto unique (codigo_barras);