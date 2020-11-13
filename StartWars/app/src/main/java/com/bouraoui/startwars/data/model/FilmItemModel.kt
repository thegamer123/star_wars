package com.bouraoui.startwars.data.model

data class FilmItemModel (
    var title:String?,
    var episode_id:Int?,
    var opening_crawl:String?,
    var director:String?,
    var producer:String?,
    var release_date:String?,
    var characters:MutableList<String?>?,
    var planets:MutableList<String?>?,
    var starships:MutableList<String?>?,
    var vehicles:MutableList<String?>?,
    var species:MutableList<String?>?,
    var created:String?,
    var edited:String?,
    var url:String?
    ){
}