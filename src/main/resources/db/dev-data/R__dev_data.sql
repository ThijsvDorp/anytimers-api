INSERT INTO users (
    email,
    username,
    first_name,
    prefix,
    last_name,
    password,
    role,
    created_on,
    updated_on
) VALUES (
    'admin@example.com',
    'admin',
    'Admin',
    NULL,
    'User',
    '$2y$10$mzTRpTD36JJVYUi2b4fq0eIZNVnashhvSb7dSkzKvZMHjjx6l/ksW', -- bcrypt for 'pass'
    'ADMIN',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);