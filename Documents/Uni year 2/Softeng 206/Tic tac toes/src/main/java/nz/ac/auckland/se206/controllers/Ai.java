package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;
import java.util.Random;

public class Ai {
    private int move = getRandomInteger();
    private ArrayList<Integer> ai;
    private ArrayList<Integer> human;

    public Ai() {

    }

    public static int getRandomInteger() {
        Random random = new Random();
        return random.nextInt(9) + 1;
	}

    public int getMove(ArrayList<Integer> ai, ArrayList<Integer> human) {
        this.ai = ai;
        this.human = human;
        if (human.size() < 2 && ai.size() < 2) {
            if (human.contains(5)) {
                move = 1;
            } else {
                move = 5;
            }
        } else if (getOffensiveMove() != 0) {
            move = getOffensiveMove();
            System.out.println("AI moveO: " + move);
        } else if (getDefensiveMove() != 0) {
            move = getDefensiveMove();
            System.out.println("AI moveD: " + move);
        } else {
            while (ai.contains(move) || human.contains(move)) {
                move = getRandomInteger();
            }
            System.out.println("AI moveR: " + move);
        }
        
        return move;
    }

    public int getDefensiveMove() {
        for (int i = 1; i <= 3; i++) {
            if (human.contains(i) && human.contains(i + 3) && !ai.contains(i + 6)) {
                return i + 6;
            }
            if (human.contains(i) && human.contains(i + 6) && !ai.contains(i + 3)) {
                return i + 3;
            }
            if (human.contains(i + 3) && human.contains(i + 6) && !ai.contains(i)) {
                return i;
            }
        }
        for (int i = 1; i <= 7; i += 3) {
            if (human.contains(i) && human.contains(i + 1) && !ai.contains(i + 2)) {
                return i + 2;
            }
            if (human.contains(i) && human.contains(i + 2) && !ai.contains(i + 1)) {
                return i + 1;
            }
            if (human.contains(i + 1) && human.contains(i + 2) && !ai.contains(i)) {
                return i;
            }
        }
        if (human.contains(1) && human.contains(5) && !ai.contains(9)) {
            return 9;
        }
        if (human.contains(1) && human.contains(9) && !ai.contains(5)) {
            return 5;
        }
        if (human.contains(5) && human.contains(9) && !ai.contains(1)) {
            return 1;
        }
        if (human.contains(3) && human.contains(5) && !ai.contains(7)) {
            return 7;
        }
        if (human.contains(3) && human.contains(7) && !ai.contains(5)) {
            return 5;
        }
        if (human.contains(5) && human.contains(7) && !ai.contains(3)) {
            return 3;
        }
        return 0;
    }

    private int getOffensiveMove() {
        for (int i = 1; i <= 3; i++) {
            if (ai.contains(i) && ai.contains(i + 3) && !human.contains(i + 6)) {
                return i + 6;
            }
            if (ai.contains(i) && ai.contains(i + 6) && !human.contains(i + 3)) {
                return i + 3;
            }
            if (ai.contains(i + 3) && ai.contains(i + 6) && !human.contains(i)) {
                return i;
            }
        }
        for (int i = 1; i <= 7; i += 3) {
            if (ai.contains(i) && ai.contains(i + 1) && !human.contains(i + 2)) {
                return i + 2;
            }
            if (ai.contains(i) && ai.contains(i + 2) && !human.contains(i + 1)) {
                return i + 1;
            }
            if (ai.contains(i + 1) && ai.contains(i + 2) && !human.contains(i)) {
                return i;
            }
        }
        if (ai.contains(1) && ai.contains(5) && !human.contains(9)) {
            return 9;
        }
        if (ai.contains(1) && ai.contains(9) && !human.contains(5)) {
            return 5;
        }
        if (ai.contains(5) && ai.contains(9) && !human.contains(1)) {
            return 1;
        }
        if (ai.contains(3) && ai.contains(5) && !human.contains(7)) {
            return 7;
        }
        if (ai.contains(3) && ai.contains(7) && !human.contains(5)) {
            return 5;
        }
        if (ai.contains(5) && ai.contains(7) && !human.contains(3)) {
            return 3;
        }
        return 0;
    }
}


