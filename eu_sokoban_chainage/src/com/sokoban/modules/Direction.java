package com.sokoban.modules;

public enum Direction {

    gauche(-1, 0), droite(1, 0), haut(0, -1), bas(0, 1);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getPas(int m) {
        return dy * m + dx;
    }

    public Direction opposite() {
        if (this == droite) return gauche;
        else if (this == gauche) return droite;
        else if (this == haut) return bas;
        else return haut;
    }

}
