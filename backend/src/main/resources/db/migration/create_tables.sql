-- Tabela principal de usuários
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50)
);

DROP TYPE IF EXISTS category CASCADE;
CREATE TYPE category AS ENUM ('SPORTS', 'MOVIES', 'FINANCE', 'PROMOTION', 'SECURITY', 'ALERT');

DROP TYPE IF EXISTS channel_type CASCADE;
CREATE TYPE channel_type AS ENUM ('EMAIL', 'SMS', 'PUSH');

CREATE TABLE user_subscriptions (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    category category
);

CREATE TABLE user_channels (
    user_id BIGINT REFERENCES users(id) ON DELETE CASCADE,
    channel channel_type
);

CREATE TABLE notification_log (
    id BIGSERIAL PRIMARY KEY,
    message TEXT,
    user_name VARCHAR(255),
    user_email VARCHAR(255),
    user_phone VARCHAR(50),
    category category,
    channel channel_type,
    timestamp TIMESTAMP,
    success BOOLEAN
);
