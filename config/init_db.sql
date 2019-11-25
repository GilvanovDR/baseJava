CREATE TABLE resume
(
  uuid      CHAR(36) NOT NULL
    CONSTRAINT resume_pkey
    PRIMARY KEY,
  full_name TEXT
);

CREATE TABLE contact
(
  id         SERIAL   NOT NULL
    CONSTRAINT contact_pkey
    PRIMARY KEY,
  type       TEXT     NOT NULL,
  value      TEXT     NOT NULL,
  resum_uuid CHAR(36) NOT NULL
    CONSTRAINT contact_resume_uuid_fk
    REFERENCES resume
    ON DELETE CASCADE
);
CREATE UNIQUE INDEX contact_uuid_type_index
  ON public.contact (resum_uuid, type);
