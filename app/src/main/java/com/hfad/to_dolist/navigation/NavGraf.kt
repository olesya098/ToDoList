package com.hfad.to_dolist.navigation

import kotlinx.serialization.Serializable

//sealed class NavGraf( val route: String) {
//    data object TaskUI: NavGraf("TaskUI")
//    data object CreateTask: NavGraf("CreateTask")
//}
@Serializable
object TaskUIRoute

@Serializable
object CreateTaskRoute

