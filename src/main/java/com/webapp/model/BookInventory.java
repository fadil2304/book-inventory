package com.webapp.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "book_inventory")
public class BookInventory{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String publisher;
    private String author;
    private Long pages;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
