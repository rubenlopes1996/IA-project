package snake.snakeAdhoc;

import snake.*;

import java.awt.*;


public class SnakeAdhocAgent extends SnakeAgent {

    public SnakeAdhocAgent(Cell cell, Color color,int size) {
        super(cell, color,size);
    }

    public void act(Environment environment) {
        Perception perception = buildPerception(environment);
        Action action = decide(perception);
        execute(action, environment);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell.setAgent(null);
        this.cell = cell;
        this.cell.setAgent(this);
        this.cell.incrementNumVisited();

    }

    public Color getColor() {
        return Color.BLACK;
    }

    public Action decide(Perception perception) {
        // todo modify to improve the SnakeAdhocAgent decision process
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        Action action = null;
        int minNumVisited = Integer.MAX_VALUE;


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

        /*if (w != null && !w.hasWall() && !w.hasAgent() && lastAction != Action.EAST) {
            action = Action.WEST;
            lastAction = Action.WEST;
        }


        if (n != null && !n.hasWall() && !n.hasAgent() && lastAction != Action.SOUTH) {
            action = Action.NORTH;
            lastAction = Action.NORTH;

        }


        if (e != null && !e.hasWall() && !e.hasAgent() && lastAction != Action.WEST) {
            action = Action.EAST;
            lastAction = Action.EAST;

        }


        if (s != null && !s.hasWall() && !s.hasAgent() && lastAction != Action.NORTH) {
            action = Action.SOUTH;
            lastAction = Action.SOUTH;
        }

        return action;*/

        if(w != null && !w.hasWall() && !w.hasAgent() ){
            if(w.getNumVisited()<minNumVisited){
                minNumVisited = w.getNumVisited();
                action = Action.WEST;
            }
        }


        if(n != null && !n.hasWall() && !n.hasAgent() ){
            if(n.getNumVisited()<minNumVisited){
                minNumVisited = n.getNumVisited();
                action = Action.NORTH;
            }
        }


        if(e != null && !e.hasWall() && !e.hasAgent() ){
            if(e.getNumVisited()<minNumVisited){
                minNumVisited = e.getNumVisited();
                action = Action.EAST;
            }
        }


        if(s != null && !s.hasWall() && !s.hasAgent()){
            if(s.getNumVisited()<minNumVisited){
                minNumVisited = s.getNumVisited();
                action = Action.SOUTH;
            }
        }
        return action;
    }

}
