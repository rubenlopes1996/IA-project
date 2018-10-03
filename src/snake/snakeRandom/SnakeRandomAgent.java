package snake.snakeRandom;

import snake.*;

import java.awt.*;
import java.util.Random;

public class SnakeRandomAgent extends SnakeAgent {
    public SnakeRandomAgent(Cell cell) {
        super(cell, Color.BLUE);
    }

    @Override
    protected Action decide(Perception perception) {
        Action action = null;

        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

       action(perception);

        int movement = Environment.random.nextInt(4);

        if (movement == 0) {
            if (w != null && !w.hasTail() && !w.hasAgent()) {
                action = Action.WEST;
            }else if(n != null && !n.hasTail() && !n.hasAgent()){
                action = Action.NORTH;
            }else if(s != null && !s.hasTail() && !s.hasAgent()){
                action = Action.SOUTH;
            }else{
                action = Action.EAST;
            }
        } else if (movement == 1) {
            if (n != null && !n.hasTail() && !n.hasAgent()) {
                action = Action.NORTH;
            }else if(w != null && !w.hasTail() && !w.hasAgent()){
                action = Action.WEST;
            }else if (e != null && !e.hasTail() && !e.hasAgent()){
                action = Action.EAST;
            }else {
                action =Action.SOUTH;
            }
        } else if (movement == 2) {
            if (e != null && !e.hasTail() && !e.hasAgent()) {
                action = Action.EAST;
            }else  if (n != null && !n.hasTail() && !n.hasAgent()){
                action = Action.NORTH;
            }else if (s != null && !s.hasTail() && !s.hasAgent()){
                action = Action.SOUTH;
            }else {
                action =Action.WEST;
            }
        } else if (movement == 3) {
            if (s != null && !s.hasTail() && !s.hasAgent()) {
                action = Action.SOUTH;
            }else if (w != null && !w.hasTail() && !w.hasAgent()){
                action = Action.WEST;
            }else if (e != null && !e.hasTail() && !e.hasAgent()){
                action  =Action.EAST;
            }else {
                action = Action.NORTH;
            }
        }

        return action;
    }


}
