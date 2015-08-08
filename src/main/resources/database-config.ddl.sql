CREATE SEQUENCE contact_seq
START WITH 1 
INCREMENT BY 1 
NOMAXVALUE;

create table contact (
   contact_id  number(20)   not null
  , name  nvarchar2(50) not null
  , surname nvarchar2(50) not null
  , login nvarchar2(50) not null
  , email nvarchar2(50) not null
  , phone_number nvarchar2(20) not null
  , constraint pk_contact primary key (contact_id)
);

