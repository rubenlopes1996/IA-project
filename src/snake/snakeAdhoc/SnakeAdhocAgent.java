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

    public Color getColor() {
        return Color.BLACK;
    }

    public Perception buildPerception(Environment environment) {
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell));
    }

    public Action decide(Perception perception) {
        // todo modify to improve the SnakeAdhocAgent decision process
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();

        //todo FAZER IF PARA FOOD PARA WALL

        Action action = null;
        Action lastAction = null;

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

        if (w != null && !w.hasWall() && !w.hasAgent() && lastAction != Action.EAST) {
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

        return action;
    }

    public void execute(Action action, Environment environment) {

        Cell nextCell = null;

        if (action == Action.NORTH && cell.getLine() != 0) {
            nextCell = environment.getNorthCell(cell);
        } else if (action == Action.SOUTH && cell.getLine() != environment.getSize() - 1) {
            nextCell = environment.getSouthCell(cell);
        } else if (action == Action.WEST && cell.getColumn() != 0) {
            nextCell = environment.getWestCell(cell);
        } else if (action == Action.EAST && cell.getColumn() != environment.getSize() - 1) {
            nextCell = environment.getEastCell(cell);
        }

        if (nextCell != null && !nextCell.hasWall() && !nextCell.hasAgent()) {
            setCell(nextCell);
        }
    }

}
