package com.example.meet13.ui.navigation

interface DestinasiNavigasi {
    val route: String
    val title: String
}

object DestinasiHome : DestinasiNavigasi{
    override val route: String = "home"
    override val title: String ="Home"
}

object DestinasiInsert : DestinasiNavigasi{
    override val route: String = "insert"
    override val title: String = "Insert"
}

object DestinasiView : DestinasiNavigasi{
    override val route: String = "view"
    override val title: String = "View"
}