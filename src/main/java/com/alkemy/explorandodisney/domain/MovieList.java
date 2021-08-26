package com.alkemy.explorandodisney.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class MovieList {
   private String picture;
   private String title;
   private LocalDate creationDate;

   public MovieList() {
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

   @JsonFormat(pattern = "dd-MM-yyyy")
   public LocalDate getCreationDate() {
      return creationDate;
   }

   public void setCreationDate(LocalDate creationDate) {
      this.creationDate = creationDate;
   }
}
