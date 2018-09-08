package com.okan.hazelcastdemo.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Access(AccessType.FIELD)
public class Fruit implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}