package net.purevirtual.advent2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Advent22_09 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input9.txt");
        Scanner scanner = new Scanner(file);
        Set<Cord> visited = new HashSet<>();
        List<Cord> rope =new ArrayList<>();
        for (int i=0;i<10;i++) {// or 2 for firt version
            rope.add(new Cord(0, 0));
        }
        visited.add(rope.get(rope.size()-1));
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split(" ");
            Direction dir = Direction.valueOf(parts[0]);
            int count = Integer.parseInt(parts[1]);
            for (int i = 0; i < count; i++) {
                rope.set(0,   rope.get(0).apply(dir.getCord()));
                for (int j=1;j<rope.size();j++) {
                    rope.set(j, adjust(rope.get(j-1), rope.get(j)));
                }
                visited.add(rope.get(rope.size()-1));
            }

        }
        System.err.println(visited);
        System.err.println(visited.size());
    }
    
    private static Cord adjust(Cord head, Cord tail) {
        int dx = Math.abs(head.x - tail.x);
        int dy = Math.abs(head.y - tail.y);
        boolean needsMove = dx + dy > 2 || dx >= 2 || dy >= 2;
        if (!needsMove) {
            System.err.println("no tail move: head=" + head + " and tail = " + tail);
            return tail;
        }
        if (head.x - 2 == tail.x && head.y == tail.y) {
            tail = tail.apply(Direction.R.cord);
        } else if (head.x + 2 == tail.x && head.y == tail.y) {
            tail = tail.apply(Direction.L.cord);
        } else if (head.y + 2 == tail.y && head.x == tail.x) {
            tail = tail.apply(Direction.U.cord);
        } else if (head.y - 2 == tail.y && head.x == tail.x) {
            tail = tail.apply(Direction.D.cord);
        } else if (head.x > tail.x && head.y > tail.y) {
            tail = tail.apply(new Cord(+1, +1));
        } else if (head.x > tail.x && head.y < tail.y) {
            tail = tail.apply(new Cord(+1, -1));
        } else if (head.x < tail.x && head.y > tail.y) {
            tail = tail.apply(new Cord(-1, +1));
        } else if (head.x < tail.x && head.y < tail.y) {
            tail = tail.apply(new Cord(-1, -1));
        } else {
            System.err.println("no tail move head=" + head + " and tail = " + tail);
            throw new IllegalStateException("no valid move");
        }
        System.err.println("after applying move head=" + head + " and tail = " + tail);
        return tail;
    }

    static record Cord(int x, int y) {

        Cord apply(Cord other) {
            return new Cord(x + other.x, y + other.y);
        }
    }

    enum Direction {
        U(new Cord(0, -1)),
        D(new Cord(0, 1)),
        L(new Cord(-1, 0)),
        R(new Cord(1, 0));
        private Cord cord;

        private Direction(Cord cord) {
            this.cord = cord;
        }

        public Cord getCord() {
            return cord;
        }

    };

}
