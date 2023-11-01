package com.example.librarymanagementsystem.Models;

import com.example.librarymanagementsystem.Enums.Genre;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String title;
    private Boolean isAvailable;
    @Enumerated(value = EnumType.STRING)
    private Genre genre;
    private Date publicationDate;
    private Integer price;
    @ManyToOne
    @JoinColumn
    private Author author; //Upto here unidirectional mapping
}
