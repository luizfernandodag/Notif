INSERT INTO users (name, email, phone) VALUES
('Alice Souza', 'alice@example.com', '5511999999991'),
('Bruno Lima', 'bruno@example.com', '5511999999992'),
('Carla Mendes', 'carla@example.com', '5511999999993');



INSERT INTO user_subscriptions (user_id, subscriptions) VALUES
((SELECT id FROM users WHERE email='alice@example.com'), 'SPORTS'),
((SELECT id FROM users WHERE email='alice@example.com'), 'MOVIES'),
((SELECT id FROM users WHERE email='bruno@example.com'), 'FINANCE'),
((SELECT id FROM users WHERE email='bruno@example.com'), 'MOVIES'),
((SELECT id FROM users WHERE email='carla@example.com'), 'SPORTS'),
((SELECT id FROM users WHERE email='carla@example.com'), 'FINANCE');

INSERT INTO user_channels (user_id, channels) VALUES
((SELECT id FROM users WHERE email='alice@example.com'), 'EMAIL'),
((SELECT id FROM users WHERE email='alice@example.com'), 'PUSH'),
((SELECT id FROM users WHERE email='bruno@example.com'), 'SMS'),
((SELECT id FROM users WHERE email='bruno@example.com'), 'EMAIL'),
((SELECT id FROM users WHERE email='carla@example.com'), 'SMS'),
((SELECT id FROM users WHERE email='carla@example.com'), 'PUSH');