package ru.develgame.audiogames

import ru.develgame.audiogames.entity.AudioGame
import java.io.Serializable
import javax.annotation.Resource
import javax.faces.view.ViewScoped
import javax.inject.Named
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.UserTransaction

@Named("testdb")
@ViewScoped
open class TestDBBean: Serializable {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @Resource
    private lateinit var userTransaction: UserTransaction

    open fun testFun() {
        val find = entityManager.find(AudioGame::class.java, 1L)
        println(find.name)
    }
}