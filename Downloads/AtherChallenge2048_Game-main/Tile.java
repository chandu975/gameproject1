package com.company;

import java.awt.Color;


public class Tile {
    int value;
    Color tileColor;

    public Tile() {
        this.value = 0;
    }

    public Tile(int number) {
        this.value = number;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return "" + this.value;
    }

    public void setColor() {
        if (this.getValue() == 2) {
            this.tileColor = new Color(238, 228, 218);
        } else if (this.getValue() == 4) {
            this.tileColor = new Color(237, 224, 200);
        } else if (this.getValue() == 8) {
            this.tileColor = new Color(242, 177, 121);
        } else if (this.getValue() == 16) {
            this.tileColor = new Color(245, 149, 99);
        } else if (this.getValue() == 32) {
            this.tileColor = new Color(246, 124, 95);
        } else if (this.getValue() == 64) {
            this.tileColor = new Color(246, 94, 59);
        } else if (this.getValue() == 128) {
            this.tileColor = new Color(237, 207, 114);
        } else if (this.getValue() == 256) {
            this.tileColor = new Color(237, 204, 97);
        } else if (this.getValue() == 512) {
            this.tileColor = new Color(237, 200, 80);
        } else if (this.getValue() == 1024) {
            this.tileColor = new Color(237, 197, 63);
        } else {
            this.tileColor = new Color(237, 194, 46);
        }

    }

    public Color getColor() {
        this.setColor();
        return this.tileColor;
    }
}
