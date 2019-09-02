create table resume
(
  uuid char(36) not null
    constraint resume_pkey
    primary key,
  full_name text
)
;

create table contact
(
  id serial not null
    constraint contact_pkey
    primary key,
  type text not null,
  value text not null,
  resum_uuid char(36) not null
    constraint contact_resume_uuid_fk
    references resume
    on delete cascade
)
;
CREATE UNIQUE INDEX contact_uuid_type_index ON public.contact (resum_uuid, type);
