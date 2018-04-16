package snake.snakeRandom;

import snake.*;

import java.awt.*;
import java.util.Random;

public class SnakeRandomAgent extends SnakeAgent {
    public SnakeRandomAgent(Cell cell, Color color, int size) {
        super(cell, color, size);
    }

    @Override //o Action que ele devolve é uma direção random
    protected Action decide(Perception perception) {
        Random random = new Random();
        Action action;

        // todo modify to improve the SnakeAdhocAgent decision process
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        //todo FAZER IF PARA FOOD e WALL
        if (w != null) {
            if (w.hasFood()) {
                w.setFood(null);
                return Action.WEST;
            }
        }

        if (n != null) {
            if (n.hasFood()) {
                n.setFood(null);
                return Action.NORTH;
            }
        }

        if (e != null) {
            if (e.hasFood()) {
                e.setFood(null);
                return Action.EAST;
            }
        }

        if (s != null) {
            if (s.hasFood()) {
                s.setFood(null);
                return Action.SOUTH;
            }
        }

        if (random.nextInt(4) == 0) {
            action = Action.WEST;
        } else if (random.nextInt(4) == 1) {
            action = Action.NORTH;

        } else if (random.nextInt(4) == 2) {
            action = Action.EAST;

        } else {
            action = Action.SOUTH;
        }

        return action;
    }


}
