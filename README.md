# OOP10_MovieApp

## Overview

This is a simple Android app that shows a list of movies using RecyclerView. The data is loaded from a local JSON file.

## Features

* Displays movies in a list
* Loads data from `movies.json`
* Handles missing or invalid data
* Does not crash when errors occur

## Files

* MainActivity.java → sets up RecyclerView
* Movie.java → movie data model
* JsonUtils.java → reads JSON file
* MovieAdapter.java → displays data
* activity_main.xml → main layout
* item_movie.xml → item layout

## Error Handling

The app handles:

* null values
* missing fields
* invalid year values

Invalid data is replaced with default values like:

* "Unknown Title"
* "Unknown"
* 0 (year)

## How to Run

1. Open in Android Studio
2. Run the app
3. View the movie list

Your Name
