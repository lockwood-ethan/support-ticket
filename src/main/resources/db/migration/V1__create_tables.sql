CREATE TABLE ticket
(
    id            UUID   NOT NULL,
    ticket_number BIGINT NOT NULL,
    author_id     UUID,
    subject       TEXT,
    body          TEXT,
    created_date  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_ticket PRIMARY KEY (id)
);

CREATE TABLE user_entity
(
    id           UUID NOT NULL,
    auth0id      VARCHAR(255),
    created_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_userentity PRIMARY KEY (id)
);

ALTER TABLE ticket
    ADD CONSTRAINT uc_ticket_ticketnumber UNIQUE (ticket_number);

ALTER TABLE user_entity
    ADD CONSTRAINT uc_userentity_auth0id UNIQUE (auth0id);