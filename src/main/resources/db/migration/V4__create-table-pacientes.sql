alter table pacientes add active tinyint;
update pacientes set pacientes.active = 1;
