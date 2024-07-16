DROP TABLE books;
DROP TABLE users;

CREATE TABLE IF NOT EXISTS users (
	user_id serial PRIMARY KEY,
	username TEXT NOT null,
	email TEXT,
);

CREATE TABLE IF NOT EXISTS books (
	book_id serial PRIMARY KEY,
	book_title TEXT NOT null,
	book_author TEXT,
	book_rating int check(book_rating > 0 AND book_rating <= 10),
	checked_out boolean NOT null,
	checked_out_by_fk int REFERENCES users (user_id)
);