CREATE SEQUENCE ticket_number_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE ticket
ALTER COLUMN ticket_number
SET DEFAULT nextval('ticket_number_seq');