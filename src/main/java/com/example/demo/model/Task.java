package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String avatar;

    public Task() {
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(Long id, String name, String description, String avatar) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.avatar = avatar;
    }

}