CREATE TABLE rules
(
    id        SERIAL PRIMARY KEY   NOT NULL,
    procedure SMALLINT             NOT NULL,
    ageMin    SMALLINT             NOT NULL,
    ageMax    SMALLINT             NOT NULL,
    male      BOOLEAN              NOT NULL,
    active    BOOLEAN DEFAULT TRUE NOT NULL
);
INSERT INTO rules (procedure, ageMin, ageMax, male)
values (4567, 20, 20, true),
       (6789, 10, 10, true),
       (1234, 20, 20, true),
       (4567, 30, 30, false);
CREATE TABLE authorizations
(
    id        SERIAL PRIMARY KEY NOT NULL,
    procedure SMALLINT           NOT NULL,
    age       SMALLINT           NOT NULL,
    male      BOOLEAN            NOT NULL,
    allowed   BOOLEAN            NOT NULL,
    reason    VARCHAR(53)
);