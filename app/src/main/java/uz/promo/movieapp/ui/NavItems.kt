package uz.promo.movieapp.ui

sealed class NavItems(
    val title: String,
    var screenRoute:String,
){

    object Home: NavItems(
        title = "Home",
        "home",
    )
    object Details: NavItems(
        title = "Details",
        "details",
    )

}
