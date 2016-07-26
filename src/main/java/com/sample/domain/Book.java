package com.sample.domain;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Book extends BaseDomain {

    @NotNull
    private String name;

    private String author;

    private String isbn;

    private long noOfPages;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Lob
    private byte[] image;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(long noOfPages) {
        this.noOfPages = noOfPages;
    }


}
