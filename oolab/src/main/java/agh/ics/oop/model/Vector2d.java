package agh.ics.oop.model;

import java.math.*;

import static java.lang.Math.*;

public class Vector2d {
    private final int x;
    private final int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.x - other.y);
    }

    public Vector2d opposite(){
        return new Vector2d(-this.x, -this.y);
    }

    public Vector2d upperRight(Vector2d other){
        return new Vector2d(max(other.x, this.x), max(other.y, this.y));
    }

    public Vector2d lowerLeft(Vector2d other){
        return new Vector2d(min(other.x, this.x), min(other.y, this.y));
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return "(%d, %d)".formatted(this.x, this.y);
    }

    @Override
    public boolean equals(Object other){
        if (other instanceof Vector2d vec)
            return vec.x == this.x && vec.y == this.y;
        return false;
    }

    @Override
    public int hashCode(){
        int hash = 17;

        hash *= 23 * this.x;
        hash *= 23 * this.y;

        return hash;
    }
}
