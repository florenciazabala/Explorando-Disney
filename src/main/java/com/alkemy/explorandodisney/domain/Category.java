package com.alkemy.explorandodisney.domain;


import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Category {
    private Long idCategory;
    private String picture;
    private String name;
    private Set<String> movies;

    public Category() {
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMovies() {
        return movies.stream().collect(Collectors.toCollection(TreeSet::new));
    }

    public void setMovies(Set<String> movies) {
        this.movies = movies;
    }
}
