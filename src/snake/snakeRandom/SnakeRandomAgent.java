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

        
        return null;
    }
}
