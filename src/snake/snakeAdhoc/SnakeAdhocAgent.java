package snake.snakeAdhoc;

import snake.*;

import java.awt.*;


public class SnakeAdhocAgent extends SnakeAgent {

    public SnakeAdhocAgent(Cell cell, Color color) {
        super(cell, color);
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
    }

    public Perception buildPerception(Environment environment) {
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell));
    }

    public Action decide(Perception perception) {
        // todo modify to improve the ReactiveAgent's decision process
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        //todo FAZER IF PARA FOOD PARA WALL

        Action action = null;
        int minNumVisited = Integer.MAX_VALUE;

        if(w!=null){
            if (w.hastFood()) {
                w.setFood(null);
                return Action.WEST;
            }
        }

        if(n!=null) {
            if (n.hastFood()) {
                n.setFood(null);
                return Action.NORTH;
            }
        }

        if(e!=null) {
            if (e.hastFood()) {
                e.setFood(null);
                return Action.EAST;
            }
        }

        if(s!=null) {
            if (s.hastFood()) {
                s.setFood(null);
                return Action.SOUTH;
            }
        }

        if(w != null && !w.hasWall() && !w.hasAgent() ){
            action = Action.WEST;
        }


        if(n != null && !n.hasWall() && !n.hasAgent() ){
            action = Action.NORTH;
        }


        if(e != null && !e.hasWall() && !e.hasAgent() ){
            action = Action.EAST;
        }


        if(s != null && !s.hasWall() && !s.hasAgent()){
            action = Action.SOUTH;
        }
        return action;
    }

    public void execute(Action action, Environment environment) {

        Cell nextCell = null;

        if (action == Action.NORTH && cell.getLine() != 0) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH && cell.getLine() != environment.getNumLines() - 1) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST && cell.getColumn() != 0) {
            nextCell = environment.getWestCell(cell);
        } else if (action == Action.EAST && cell.getColumn() != environment.getNumColumns() - 1) {
            nextCell = environment.getEastCell(cell);
        }

        if (nextCell != null && !nextCell.hasWall() && !nextCell.hasAgent()) {
            setCell(nextCell);
        }
    }

}
