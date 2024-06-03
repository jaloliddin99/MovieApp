package uz.promo.movieapp.data.remote.models

data class Result(
    val adult: Boolean,
    val gender: Int,
    val id: Int,
    val known_for: List<KnownFor>,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String,
    var isLiked: Boolean = false
)

fun createTestResult(): Result {
    val knownFor1 = KnownFor(
        adult = false,
        backdrop_path = "/test_backdrop_path1.jpg",
        first_air_date = "2021-01-01",
        genre_ids = listOf(16, 35, 9648),
        id = 101,
        media_type = "tv",
        name = "Test Series",
        origin_country = listOf("US"),
        original_language = "en",
        original_name = "Test Original Series",
        original_title = "",
        overview = "A test TV series overview.",
        popularity = 7.8,
        poster_path = "/test_poster1.jpg",
        release_date = "",
        title = "Test Series Title",
        video = false,
        vote_average = 8.5,
        vote_count = 200
    )

    val knownFor2 = KnownFor(
        adult = false,
        backdrop_path = "/test_backdrop_path2.jpg",
        first_air_date = "",
        genre_ids = listOf(28, 12, 878),
        id = 102,
        media_type = "movie",
        name = "",
        origin_country = listOf("US"),
        original_language = "en",
        original_name = "",
        original_title = "Test Movie",
        overview = "A test movie overview.",
        popularity = 9.2,
        poster_path = "/test_poster2.jpg",
        release_date = "2022-02-02",
        title = "Test Movie Title",
        video = false,
        vote_average = 7.3,
        vote_count = 150
    )
    return Result(
        adult = false,
        gender = 1,
        id = 12345,
        known_for = listOf(knownFor1, knownFor2),
        known_for_department = "Acting",
        name = "Test Person",
        original_name = "Test Original Person",
        popularity = 15.6,
        profile_path = "/test_profile.jpg"
    )
}
