//package com.example.androidpractice.listWithDetails.data.mock
//
//import com.example.androidpractice.listWithDetails.domain.entity.MovieFullEntity
//import com.example.androidpractice.listWithDetails.domain.entity.MoviesShortEntity
//
//object MoviesDataMock {
//    val moviesShort = listOf(
//        MoviesShortEntity(
//            id = "tt12299608",
//            type = "movie",
//            primary_title = "Mickey 17",
//            start_year = "2025",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BZGQwYmEzMzktYzBmMy00NmVmLTkyYTUtOTYyZjliZDNhZGVkXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt9603212",
//            type = "movie",
//            primary_title = "Mission: Impossible - Dead Reckoning Part One",
//            start_year = "2023",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BN2U4OTdmM2QtZTkxYy00ZmQyLTg2N2UtMDdmMGJmNDhlZDU1XkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt18335752",
//            type = "tvSeries",
//            primary_title = "1923",
//            start_year = "2022",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMTU3MGViZTItMmI5NS00MzMwLWFkNjctOGFlNWVmNDM0N2QyXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt3748528",
//            type = "movie",
//            primary_title = "Rogue One: A Star Wars Story",
//            start_year = "2016",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMjEwMzMxODIzOV5BMl5BanBnXkFtZTgwNzg3OTAzMDI@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt0388629",
//            type = "tvSeries",
//            primary_title = "One Piece",
//            start_year = "1999",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMTNjNGU4NTUtYmVjMy00YjRiLTkxMWUtNzZkMDNiYjZhNmViXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt7235466",
//            type = "tvSeries",
//            primary_title = "9-1-1",
//            start_year = "2018",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNjZiZWIxZWMtMTU4Mi00ZDNmLWJmMjAtYjBiY2IyODY5ZTI1XkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt13991232",
//            type = "tvMiniSeries",
//            primary_title = "1883",
//            start_year = "2021",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMDBjZDQyMWMtZmI0My00MzJlLWFhMTMtNWZmOTJkNzFlZTliXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt28679253",
//            type = "movie",
//            primary_title = "Eleven",
//            start_year = "2025",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNzNkMGU4YzYtNTY1MC00MmIzLWI0ZjctMzhlMzYwZjQ3MjZjXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt1160419",
//            type = "movie",
//            primary_title = "Dune: Part One",
//            start_year = "2021",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNWIyNmU5MGYtZDZmNi00ZjAwLWJlYjgtZTc0ZGIxMDE4ZGYwXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt0050083",
//            type = "movie",
//            primary_title = "12 Angry Men",
//            start_year = "1957",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BYjE4NzdmOTYtYjc5Yi00YzBiLWEzNDEtNTgxZGQ2MWVkN2NiXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt13991232",
//            type = "tvMiniSeries",
//            primary_title = "1883",
//            start_year = "2021",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMDBjZDQyMWMtZmI0My00MzJlLWFhMTMtNWZmOTJkNzFlZTliXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt28679253",
//            type = "movie",
//            primary_title = "Eleven",
//            start_year = "2025",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNzNkMGU4YzYtNTY1MC00MmIzLWI0ZjctMzhlMzYwZjQ3MjZjXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt1160419",
//            type = "movie",
//            primary_title = "Dune: Part One",
//            start_year = "2021",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNWIyNmU5MGYtZDZmNi00ZjAwLWJlYjgtZTc0ZGIxMDE4ZGYwXkEyXkFqcGc@._V1_.jpg"
//        ),
//        MoviesShortEntity(
//            id = "tt0050083",
//            type = "movie",
//            primary_title = "12 Angry Men",
//            start_year = "1957",
//            primary_image = "https://m.media-amazon.com/images/M/MV5BYjE4NzdmOTYtYjc5Yi00YzBiLWEzNDEtNTgxZGQ2MWVkN2NiXkEyXkFqcGc@._V1_.jpg"
//        )
//
//    )
//
//    val moviesFull = listOf(
//        MovieFullEntity(
//            id = "tt12299608",
//            type = "movie",
//            primary_title = "Mickey 17",
//
//            start_year = "2025",
//
//            primary_image = "https://m.media-amazon.com/images/M/MV5BZGQwYmEzMzktYzBmMy00NmVmLTkyYTUtOTYyZjliZDNhZGVkXkEyXkFqcGc@._V1_.jpg",
//            genres = listOf(
//                "Adventure",
//                "Comedy",
//                "Fantasy",
//                "Sci-Fi"),
//            rating = 6.8 ,
//            runtime_minutes = "137",
//            plot = "During a human expedition to colonize space, Mickey 17, a so-called \"expendable\" employee, is sent to explore an ice planet."
//        ),
//        MovieFullEntity(
//            id = "tt7235466",
//            type = "tvSeries",
//            primary_title = "9-1-1",
//            start_year = "2018",
//
//            primary_image = "https://m.media-amazon.com/images/M/MV5BNjZiZWIxZWMtMTU4Mi00ZDNmLWJmMjAtYjBiY2IyODY5ZTI1XkEyXkFqcGc@._V1_.jpg",
//            genres = listOf("Action",
//                "Drama",
//                "Thriller"),
//            rating = 7.9,
//            runtime_minutes = "43",
//            plot = "Explores the high-pressure experiences of first responders who are thrust into the most frightening, shocking, and heart-stopping situations, day after day."
//        ),
//        MovieFullEntity(
//            id = "tt13991232",
//            type = "tvMiniSeries",
//            primary_title = "1883",
//            start_year = "2021",
//
//            primary_image = "https://m.media-amazon.com/images/M/MV5BMDBjZDQyMWMtZmI0My00MzJlLWFhMTMtNWZmOTJkNzFlZTliXkEyXkFqcGc@._V1_.jpg",
//            genres = listOf("Drama",
//                "Western"),
//            rating = 8.7,
//            runtime_minutes = "60",
//            plot = "The post-Civil war generation of the Dutton family travels to Texas, and joins a wagon train undertaking the arduous journey west to Oregon, before settling in Montana to establish what would eventually become the Yellowstone Ranch."
//        ),
//    )
//
//}