package com.alkemy.explorandodisney.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Movie {

    private Long idMovie;
    private String picture;
    @NotEmpty(message = "Field 'title' cant't be empty")
    private String title;
    //@NotEmpty(message = "Field 'creation date' cant't be empty")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    private Integer score;
    @NotEmpty(message = "Field 'category' cant't be empty")
    private String category;
    private Set<String> characters;

    public Movie() {
    }

    public Long getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category=category;
    }

    public Set<String> getCharacters() {
        return characters.stream().collect(Collectors.toCollection(TreeSet::new));
    }

    public void setCharacters(Set<String> characters) {
        this.characters = characters;
    }
}
