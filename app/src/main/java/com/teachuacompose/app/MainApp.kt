package com.teachuacompose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


object TeachUaLinksConstants {
    const val Facebook = "https://www.facebook.com/teach.in.ukrainian"
    const val Youtube = "https://www.youtube.com/channel/UCP38C0jxC8aNbW34eBoQKJw"
    const val Instagram = "https://www.instagram.com/teach.in.ukrainian/"
    const val Mail = "mailto:teach.in.ukrainian@gmail.com"
}

const val baseImageUrl = "https://speak-ukrainian.org.ua/"
const val baseUrl = "https://speak-ukrainian.org.ua/dev/api/"
const val errorConnectionMessage = "Помилка мережі"


@HiltAndroidApp
class MainApp:Application()
