package com.example.androidpractice.listWithDetails.data.mock

import com.example.androidpractice.listWithDetails.data.entity.MovieFullEntity
import com.example.androidpractice.listWithDetails.data.entity.MoviesShortEntity

object MoviesDataMock {
    val moviesShort = listOf(
        MoviesShortEntity(
            id = "tt12299608",
            type = "movie",
            primary_title = "Mickey 17",
            original_title = "Mickey 17",
            aggregate_rating = 6.8,
            start_year = "2025"
        ),
        MoviesShortEntity(
            id = "tt9603212",
            type = "movie",
            primary_title = "Mission: Impossible - Dead Reckoning Part One",
            original_title = "Mission: Impossible - Dead Reckoning Part One",
            aggregate_rating = 7.6,
            start_year = "2023"
        ),
        MoviesShortEntity(
            id = "tt18335752",
            type = "tvSeries",
            primary_title = "1923",
            original_title = "1923",
            aggregate_rating = 8.3,
            start_year = "2022"
        ),
        MoviesShortEntity(
            id = "tt3748528",
            type = "movie",
            primary_title = "Rogue One: A Star Wars Story",
            original_title = "Rogue One",
            aggregate_rating = 7.8,
            start_year = "2016"
        ),
        MoviesShortEntity(
            id = "tt0388629",
            type = "tvSeries",
            primary_title = "One Piece",
            original_title = "One Piece: Wan pîsu",
            aggregate_rating = 9.0,
            start_year = "1999"
        ),
        MoviesShortEntity(
            id = "tt7235466",
            type = "tvSeries",
            primary_title = "9-1-1",
            original_title = "9-1-1",
            aggregate_rating = 7.9,
            start_year = "2018"
        ),
        MoviesShortEntity(
            id = "tt13991232",
            type = "tvMiniSeries",
            primary_title = "1883",
            original_title = "1883",
            aggregate_rating = 8.7,
            start_year = "2021"
        ),
        MoviesShortEntity(
            id = "tt28679253",
            type = "movie",
            primary_title = "Eleven",
            original_title = "Eleven",
            aggregate_rating = 7.4,
            start_year = "2025"
        ),
        MoviesShortEntity(
            id = "tt1160419",
            type = "movie",
            primary_title = "Dune: Part One",
            original_title = "Dune: Part One",
            aggregate_rating = 8.0,
            start_year = "2021"
        ),
        MoviesShortEntity(
            id = "tt0050083",
            type = "movie",
            primary_title = "12 Angry Men",
            original_title = "12 Angry Men",
            aggregate_rating = 9.0,
            start_year = "1957"
        )
    )

    val moviesFull = listOf(
        MovieFullEntity(
            id = "tt12299608",
            type = "movie",
            primary_title = "Mickey 17",

            start_year = "2025",

            image_url = "https://m.media-amazon.com/images/M/MV5BZGQwYmEzMzktYzBmMy00NmVmLTkyYTUtOTYyZjliZDNhZGVkXkEyXkFqcGc@._V1_.jpg",
            genres = listOf(
                "Adventure",
                "Comedy",
                "Fantasy",
                "Sci-Fi"),
            rating = 6.8 ,
            runtime_minutes = "137",
            plot = "During a human expedition to colonize space, Mickey 17, a so-called \"expendable\" employee, is sent to explore an ice planet."
        ),
        MovieFullEntity(
            id = "tt7235466",
            type = "tvSeries",
            primary_title = "9-1-1",
            start_year = "2018",

            image_url = "https://m.media-amazon.com/images/M/MV5BNjZiZWIxZWMtMTU4Mi00ZDNmLWJmMjAtYjBiY2IyODY5ZTI1XkEyXkFqcGc@._V1_.jpg",
            genres = listOf("Action",
                "Drama",
                "Thriller"),
            rating = 7.9,
            runtime_minutes = "43",
            plot = "Explores the high-pressure experiences of first responders who are thrust into the most frightening, shocking, and heart-stopping situations, day after day."
        ),
        MovieFullEntity(
            id = "tt13991232",
            type = "tvMiniSeries",
            primary_title = "1883",
            start_year = "2021",

            image_url = "https://m.media-amazon.com/images/M/MV5BMDBjZDQyMWMtZmI0My00MzJlLWFhMTMtNWZmOTJkNzFlZTliXkEyXkFqcGc@._V1_.jpg",
            genres = listOf("Drama",
                "Western"),
            rating = 8.7,
            runtime_minutes = "60",
            plot = "The post-Civil war generation of the Dutton family travels to Texas, and joins a wagon train undertaking the arduous journey west to Oregon, before settling in Montana to establish what would eventually become the Yellowstone Ranch."
        ),
    )

}