package snake.snakeRandom;

import snake.*;

import java.awt.*;
import java.util.Random;

public class SnakeRandomAgent extends SnakeAgent {
    // TODO

    public SnakeRandomAgent(Cell cell, Color color) {
        super(cell, color);
    }

    @Override //o Action que ele devolve é uma direção random
    protected Action decide(Perception perception) {
        Random random = new Random();
        Action action;

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
