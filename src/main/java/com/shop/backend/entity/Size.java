package com.shop.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sizes")
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SizeType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "adult_size")
    private AdultSize adultSize;

    @Enumerated(EnumType.STRING)
    @Column(name = "child_size")
    private ChildSize childSize;

    public Size() {
    }

    public Size(SizeType type, AdultSize adultSize, ChildSize childSize) {
        this.type = type;
        this.adultSize = adultSize;
        this.childSize = childSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SizeType getType() {
        return type;
    }

    public void setType(SizeType type) {
        this.type = type;
    }

    public AdultSize getAdultSize() {
        return adultSize;
    }

    public void setAdultSize(AdultSize adultSize) {
        this.adultSize = adultSize;
    }

    public ChildSize getChildSize() {
        return childSize;
    }

    public void setChildSize(ChildSize childSize) {
        this.childSize = childSize;
    }
}
