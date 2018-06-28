CREATE SCHEMA if not exists oshs;

create table if not exists oshs.department
(
  id uuid default uuid_generate_v4() not null
    constraint department_pkey
    primary key,
  parent_id uuid
    constraint department_department_id_fk
    references oshs.department
    on update cascade on delete cascade,
  name varchar(100)
)
;
comment on table oshs.department is 'Подразделение'
;
create unique index if not exists department_id_uindex
  on oshs.department (id)
;

create table if not exists oshs.employees
(
  id uuid default uuid_generate_v4() not null
    constraint employees_pkey
    primary key,
  depatmnet_id uuid
    constraint employees_department_id_fk
    references oshs.department,
  surname varchar(100),
  patronymic varchar(100),
  position varchar(100),
  email varchar(100),
  phone varchar(100),
  room varchar(100),
  photo varchar(100),
  note varchar(100),
  name varchar(100),
  "group" varchar(100),
  role varchar(100),
  login_sydir varchar(100),
  is_removed varchar(100),
  is_external varchar(100)
)
;

comment on table oshs.employees is 'Сотрудники'
;

create unique index if not exists employees_id_uindex
  on oshs.employees (id)
;

create table if not exists oshs.delegation
(
  id uuid default uuid_generate_v4() not null
    constraint delegation_pkey
    primary key,
  employee_id uuid not null
    constraint delegation_employees_id_fk
    references employees
    on update cascade on delete cascade,
  delegate_id uuid not null
    constraint delegation_employees_delegate__fk
    references employees
    on update cascade on delete cascade,
  start_date timestamp,
  end_date timestamp,
  doc_number varchar(100),
  doc_name varchar(100),
  file_id varchar(100)
)
;

create unique index if not exists delegation_id_uindex
  on oshs.delegation (id)
;
