package com.nexuslink;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table RUN.
 */
public class Run {

    private Long id;
    private Integer uMileage;
    private String date;

    public Run() {
    }

    public Run(Long id) {
        this.id = id;
    }

    public Run(Long id, Integer uMileage, String date) {
        this.id = id;
        this.uMileage = uMileage;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUMileage() {
        return uMileage;
    }

    public void setUMileage(Integer uMileage) {
        this.uMileage = uMileage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
