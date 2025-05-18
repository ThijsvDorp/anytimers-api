

-- User table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(255),
    first_name VARCHAR(255),
    prefix VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP
);

-- Group table
CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    group_name VARCHAR(255),
    description VARCHAR(255),
    owner_id INTEGER,
    created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP
);

-- User / Group junction table
CREATE TABLE user_groups (
    user_id INTEGER,
    group_id INTEGER,
    PRIMARY KEY(user_id, group_id)
);

-- Anytimers table
CREATE TABLE anytimers (
    id SERIAL PRIMARY KEY,
    creditor_id INTEGER,
    debitor_id INTEGER,
    description VARCHAR(255),
    redemption_message VARCHAR(255),
    status VARCHAR(255),
    redeemed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Refresh Token table
CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    token VARCHAR(255),
    user_id INTEGER,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    expires_at TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Password Resets table
CREATE TABLE password_resets
(
    id          SERIAL PRIMARY KEY,
    uuid        VARCHAR(36)              NOT NULL,
    user_id     BIGINT                   NOT NULL,
    reset_token VARCHAR(255)             NOT NULL UNIQUE,
    expires_on  TIMESTAMP WITH TIME ZONE NOT NULL
);

-- Constraints / Foreign keys
ALTER TABLE user_groups
    ADD CONSTRAINT fk_users_groups_user
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE user_groups
    ADD CONSTRAINT fk_user_groups_group
        FOREIGN KEY (group_id) REFERENCES groups(id);

ALTER TABLE anytimers
    ADD CONSTRAINT fk_anytimers_users_creditor
        FOREIGN KEY (creditor_id) REFERENCES users (id);

ALTER TABLE anytimers
    ADD CONSTRAINT fk_anytimers_users_debitor
        FOREIGN KEY (debitor_id) REFERENCES users (id);

ALTER TABLE refresh_tokens
    ADD CONSTRAINT fk_refresh_tokens_users
        FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE password_resets
    ADD CONSTRAINT fk_password_resets_user
        FOREIGN KEY (user_id) REFERENCES users (id);