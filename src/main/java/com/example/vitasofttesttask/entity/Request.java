package com.example.vitasofttesttask.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "author_id")
    private AppUser author;

//    @OneToOne
//    @JoinColumn(name = "operator_id")
//    private AppUser operator;

    private Date created;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String message;
}
