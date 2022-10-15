package ru.develgame.audiogames

import ru.develgame.audiogames.dao.AudioGamesDao
import ru.develgame.audiogames.entity.AudioGame
import java.io.Serializable
import javax.annotation.Resource
import javax.faces.view.ViewScoped
import javax.inject.Inject
import javax.inject.Named
import javax.transaction.UserTransaction

@Named("testdb")
@ViewScoped
open class TestDBBean: Serializable {
    @Inject
    private lateinit var audioGamesDao: AudioGamesDao

    open fun testFun() {
        audioGamesDao.allAudioGames.forEach { println(it.name) }
    }
}