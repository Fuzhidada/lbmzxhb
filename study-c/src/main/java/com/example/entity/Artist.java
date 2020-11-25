package com.example.entity;

import javax.persistence.*;

public class Artist {
    @Id
    @Column(name = "ArtistId")
    private Integer artistid;

    @Column(name = "Name")
    private String name;

    /**
     * @return ArtistId
     */
    public Integer getArtistid() {
        return artistid;
    }

    /**
     * @param artistid
     */
    public void setArtistid(Integer artistid) {
        this.artistid = artistid;
    }

    /**
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}