package com.chromicle.collapp.utils;

/**
 * @author by Chromicle (ajayprabhakar369@gmail.com)
 * @since 1/26/2020
 */
public class UserUploaded {
    private String bookName, bookLat, bookLong, bookDes, bookAmount,user;

    public UserUploaded(){

    }

    public UserUploaded(String bookName, String bookLat, String bookLong, String bookDes, String bookAmount, String user) {
        this.bookName = bookName;
        this.bookLat = bookLat;
        this.bookLong = bookLong;
        this.bookDes = bookDes;
        this.bookAmount = bookAmount;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookLat() {
        return bookLat;
    }

    public void setBookLat(String bookLat) {
        this.bookLat = bookLat;
    }

    public String getBookLong() {
        return bookLong;
    }

    public void setBookLong(String bookLong) {
        this.bookLong = bookLong;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookDes() {
        return bookDes;
    }

    public void setBookDes(String bookDes) {
        this.bookDes = bookDes;
    }

    public String getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(String bookAmount) {
        this.bookAmount = bookAmount;
    }
}
