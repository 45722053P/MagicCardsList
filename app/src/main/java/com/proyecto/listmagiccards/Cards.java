package com.proyecto.listmagiccards;

import java.io.Serializable;

/**
 * Created by alex on 20/10/2016.
 */

public class Cards implements Serializable{
    String name;
    String color;
    String type;
    String rarity;
    String imageUrl;
    String descripcion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Cards{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", type='" + type + '\'' +
                ", rarity='" + rarity + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
