package com.ggingmin.springbootapi.model;

import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id", unique = true)
    private String emailId;
}
