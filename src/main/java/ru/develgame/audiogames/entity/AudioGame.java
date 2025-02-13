package ru.develgame.audiogames.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    public AudioGame(String name, String folder) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioGame audioGame = (AudioGame) o;
        return id == audioGame.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
