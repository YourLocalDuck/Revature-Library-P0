package com.revature.models;

public class Book {
    private int book_id;
    private String book_title;
    private String book_author;
    private int book_rating;
    private boolean checked_out;
    private int checked_out_by_fk;
    private User user;

    public Book() {
    }

    public Book(int book_id, String book_title, String book_author, int book_rating, boolean checked_out,
            User user) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_rating = book_rating;
        this.checked_out = checked_out;
        this.checked_out_by_fk = user.getUser_id();
        this.user = user;
    }

    // Constructor for inserts, without ID and with user as FK int
    public Book(String book_title, String book_author, int book_rating, boolean checked_out,
            int checked_out_by_fk) {
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_rating = book_rating;
        this.checked_out = checked_out;
        this.checked_out_by_fk = checked_out_by_fk;
    }
    
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public int getBook_rating() {
        return book_rating;
    }

    public void setBook_rating(int book_rating) {
        this.book_rating = book_rating;
    }

    public boolean isChecked_out() {
        return checked_out;
    }

    public void setChecked_out(boolean checked_out) {
        this.checked_out = checked_out;
    }

    public int getChecked_out_by_fk() {
        return checked_out_by_fk;
    }

    public void setChecked_out_by_fk(int checked_out_by_fk) {
        this.checked_out_by_fk = checked_out_by_fk;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Book [book_id=" + book_id + ", book_title=" + book_title + ", book_author=" + book_author
                + ", book_rating=" + book_rating + ", checked_out=" + checked_out + ", checked_out_by_fk="
                + checked_out_by_fk + ", user=" + user + "]";
    }
    
    

}
