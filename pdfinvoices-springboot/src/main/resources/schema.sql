CREATE TABLE IF NOT EXISTS invoices (
    id uuid default random_uuid() primary key,
    pdf_url varchar(255),
    user_id varchar(255),
    amount int
);
