package snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public abstract class SnakeAgent {

    protected Cell cell;
    protected Color color;
    protected List<Cell> snakeBody;

    public SnakeAgent(Cell cell, Color color) {
        this.cell = cell;
        if (cell != null) {
            this.cell.setAgent(this);
        }
        this.color = color;
        this.snakeBody = new ArrayList<>();
    }

    public void act(Environment environment) {
        Perception perception = buildPerception(environment);
        Action action = decide(perception);
        execute(action, environment);
    }

    protected Perception buildPerception(Environment environment) {
        // TODO
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell),
                environment.getCell(cell.getLine(),cell.getColumn()).getFood());

    }

    protected void execute(Action action, Environment environment) {
        // TODO
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

    protected abstract Action decide(Perception perception); //método que é diferente em todos os snakesAgent, fazer na própria classe

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell newCell) {
        if (this.cell != null) {
            this.cell.setAgent(null);
        }
        this.cell = newCell;
        if (newCell != null) {
            newCell.setAgent(this);
        }
    }

    public Color  getColor() {
        return color;
    }


}
