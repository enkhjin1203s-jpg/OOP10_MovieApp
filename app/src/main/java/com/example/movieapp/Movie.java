package com.example.movieapp;
public class Movie {
    private String title;
    private Integer year;
    private String genre;
    private String posterResource;

    public Movie(String title, Integer year, String genre, String posterResource) {
        this.title = (title != null && !title.isEmpty()) ? title : "Unknown Title";
        if (year != null && year > 1800 && year < 2100) {
            this.year = year;
        } else {
            this.year = null;
        }
        this.genre = (genre != null && !genre.isEmpty()) ? genre : "Unknown Genre";
        this.posterResource = (posterResource != null && !posterResource.isEmpty())
                ? posterResource : "default_poster";
    }

    public String getTitle()         { return title; }

    public Integer getYear()         { return year; }

    public String getGenre()         { return genre; }

    public String getPoster()         { return posterResource; }
}