-- for oracle
SELECT distinct t1.ID,t2.FV_NAME_FIRST, t2.FV_NAME_LAST, t2.FV_NAME_MIDDLE, t1.JOB
  ,t1.EMAIL, t1.PHONE, t1.NOTE, t1.DEPARTMENT, t1.USERNAME, t1.CREATE_TIME,t1.HIDE
FROM USERINFO t1 left join sch_dpm.T_PERSON t2 on t1.USERNAME=t2.FV_LOGIN;

select distinct DEPARTMENT
from USERINFO;

-- for Postgres
insert into oshs.department (name)
  select department
  from oshs.fromoracledepatment;

insert into oshs.department (name)
values ('Без подразделения');

insert into oshs.employees (id, name, surname, patronymic, position, email, phone, note, department_id, login_sydir, create_date)
  select
    t1.id :: UUID,
    t1.fv_name_first,
    t1.fv_name_last,
    t1.fv_name_middle,
    t1.job,
    t1.email,
    t1.phone,
    t1.note,
    t2.id,
    username,
    create_time
  from oshs.fromoracleusers t1 left join oshs.department t2 on t1.department = t2.name where t1.hide=false ;