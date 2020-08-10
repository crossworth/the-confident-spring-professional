CREATE TABLE IF NOT EXISTS transactions (
    id uuid default random_uuid() primary key,
    amount int,
    timestamp timestamp,
    reference varchar(255),
    slogan varchar(255)
);
