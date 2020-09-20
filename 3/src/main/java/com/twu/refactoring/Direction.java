package com.twu.refactoring;

import java.util.HashMap;
import java.util.Map;

public class Direction {
    private final char direction;

    public Direction(char direction) {
        this.direction = direction;
    }

    private final static Map right = new HashMap<Character, Character>();
    private final static Map left = new HashMap<Character, Character>();
    static {
        right.put('N','E');
        right.put('S','W');
        right.put('E','N');
        right.put('W','S');
        left.put('N','W');
        left.put('S','E');
        left.put('E','N');
        left.put('W','S');
    }

    public Direction turnRight() {
        if (!right.containsKey(this.direction)){
            throw new IllegalArgumentException();
        }
        return new Direction((Character) right.get(this.direction));
    }

    public Direction turnLeft() {
        if (!left.containsKey(this.direction)){
            throw new IllegalArgumentException();
        }
        return new Direction((Character) left.get(this.direction));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction1 = (Direction) o;

        if (direction != direction1.direction) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) direction;
    }

    @Override
    public String toString() {
        return "Direction{direction=" + direction + '}';
    }
}
