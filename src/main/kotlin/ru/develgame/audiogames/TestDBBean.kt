package ru.develgame.audiogames

import java.io.Serializable
import javax.annotation.Resource
import javax.faces.view.ViewScoped
import javax.inject.Named
import javax.persistence.EntityManager

@Named("testdb")
@ViewScoped
open class TestDBBean: Serializable {
    @Resource
    private lateinit var entityManager: EntityManager


}