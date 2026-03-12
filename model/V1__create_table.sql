CREATE TABLE user_credentials
(
    credential_id UUID NOT NULL,
    password_hash         VARCHAR(255) NOT NULL,
    account_verified      BOOLEAN      NOT NULL,
    account_locked        BOOLEAN      NOT NULL,
    failed_login_attempts INTEGER      NOT NULL,
    last_password_change  TIMESTAMP WITHOUT TIME ZONE,
    email                 VARCHAR(255) NOT NULL,
    CONSTRAINT pk_user_credentials PRIMARY KEY (credential_id)
);

CREATE TABLE users
(
    credentials_id UUID,
    created_at                 TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    active                     BOOLEAN      NOT NULL,
    value UUID NOT NULL,
    email                      VARCHAR(255) NOT NULL,
    first_name                 VARCHAR(255) NOT NULL,
    last_name                  VARCHAR(255) NOT NULL,
    birth_date                 date         NOT NULL,
    phone                      VARCHAR(255) NOT NULL,
    timezone                   VARCHAR(255) NOT NULL,
    language                   VARCHAR(255) NOT NULL,
    settings_id UUID NOT NULL,
    default_currency           VARCHAR(255),
    monthly_budget             DECIMAL,
    alert_limit                DECIMAL,
    auto_detect_subscriptions  BOOLEAN,
    alert_threshold_percentage INTEGER,
    financial_alerts_enabled   BOOLEAN      NOT NULL,
    billing_cycle_day          INTEGER,
    CONSTRAINT pk_users PRIMARY KEY (value)
);

ALTER TABLE user_credentials
    ADD CONSTRAINT uc_user_credentials_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_credentials UNIQUE (credentials_id);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_CREDENTIALS FOREIGN KEY (credentials_id) REFERENCES user_credentials (credential_id);