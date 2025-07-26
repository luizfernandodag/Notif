- Inserção dos usuários
INSERT INTO users (id, name, email, phone) VALUES
(1, 'Alice Souza', 'alice@example.com', '5511999999991'),
(2, 'Bruno Lima', 'bruno@example.com', '5511999999992'),
(3, 'Carla Mendes', 'carla@example.com', '5511999999993');

-- Subscrições por categoria
INSERT INTO user_subscriptions (user_id, subscriptions) VALUES
(1, 'SPORTS'),
(1, 'MOVIES'),
(2, 'FINANCE'),
(2, 'MOVIES'),
(3, 'SPORTS'),
(3, 'FINANCE');

-- Canais de notificação
INSERT INTO user_channels (user_id, channels) VALUES
(1, 'EMAIL'),
(1, 'PUSH'),
(2, 'SMS'),
(2, 'EMAIL'),
(3, 'SMS'),
(3, 'PUSH');