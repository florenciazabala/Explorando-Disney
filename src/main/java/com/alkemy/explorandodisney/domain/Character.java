package com.alkemy.explorandodisney.domain;



import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

public class Character {

    private Long idCharacter;
    private String picture;
    @NotEmpty(message = "Name can't be empty")
    private String name;
    //@NotEmpty(message = "Age can't be empty")
    private Integer age;
    //@NotEmpty(message = "Weight can't be empty")
    private Double weight;
    @NotEmpty(message = "Story can't be empty")
    private String story;
    private Set<String> titles;

    public Character() {
    }

    public Long getIdCharacter() {
        return idCharacter;
    }

    public void setIdCharacter(Long idCharacter) {
        this.idCharacter = idCharacter;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public Set<String> getTitles() {
        return titles.stream().collect(Collectors.toCollection(TreeSet::new));
    }

    public void setTitles(Set<String> titles) {
        this.titles = titles;
    }
}
