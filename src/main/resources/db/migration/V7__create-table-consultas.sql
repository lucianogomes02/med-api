create table consultas(

    id BINARY(16) not null,
    medico_id BINARY(16) not null,
    paciente_id BINARY(16) not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_medico_id foreign key(medico_id) references medicos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

);