package ru.develgame.audiogames.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "AUDIOGAME", schema = "APP")
public class AudioGame implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FOLDER")
    private String folder;

    public AudioGame() {
    }

    public AudioGame(int id, String name, String folder) {
        this.id = id;
        this.name = name;
        this.folder = folder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }
}
