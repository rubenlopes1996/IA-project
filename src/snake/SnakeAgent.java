package snake;

import snake.snakeAI.nn.SnakeAIAgent;
import snake.snakeAI.nn.SnakeAIAgent2;

import java.awt.Color;
import java.util.LinkedList;

public abstract class SnakeAgent {

    protected Cell cell;
    protected Color color;
    protected LinkedList<Tail> snakeBody;
    protected boolean alive;
    protected int foodEat;
    protected int foodEatAI;
    protected int foodEatAI2;


    public SnakeAgent(Cell cell, Color color) {
        this.cell = cell;
        if (cell != null) {
            this.cell.setAgent(this);
        }
        this.color = color;
        this.snakeBody = new LinkedList<>();
        this.alive = true;
        this.foodEat = 0;
    }

    protected void moverCauda(Cell pos) {
        if (!snakeBody.isEmpty()) {
            Cell lastTail = snakeBody.removeLast().getCell();
            lastTail.setTail(null);
            snakeBody.addFirst(new Tail(pos));
        }
    }

    public void act(Environment environment) {
        Perception perception = buildPerception(environment);
        Action action = decide(perception);
        execute(action, environment);
    }

    public Perception buildPerception(Environment environment) {
        return new Perception(
                environment.getNorthCell(cell),
                environment.getSouthCell(cell),
                environment.getEastCell(cell),
                environment.getWestCell(cell),
                environment.getFoodPosition(cell));

    }

    public boolean isAlive() {
        return alive;
    }

    protected void execute(Action action, Environment environment) {
        Cell nextCell = null;

        if (action == Action.NORTH && cell.getLine() != 0) {
            nextCell = environment.getNorthCell(cell);
        }
        if (action == Action.SOUTH && cell.getLine() != environment.getNumLines() - 1) {
            nextCell = environment.getSouthCell(cell);
        }
        if (action == Action.WEST && cell.getColumn() != 0) {
            nextCell = environment.getWestCell(cell);
        }
        if (action == Action.EAST && cell.getColumn() != environment.getNumColumns() - 1) {
            nextCell = environment.getEastCell(cell);
        }
        if (nextCell == null || nextCell.hasAgent() || nextCell.hasTail()) {
            alive = false;
        } else {
            if(nextCell.hasFood()){
                snakeBody.addFirst(new Tail(cell));
            }
            else{
                moverCauda(cell);
            }
            setCell(nextCell);
            if(nextCell.hasFood()){
                nextCell.setFood(null);
                foodEat++;
                environment.placeFood();
            }

        }

    }

    protected Action action(Perception perception) {

        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();
        Action action = null;


        if (w != null && !w.hasTail() && !w.hasAgent()) {
            if (w.hasFood()) {
                action = Action.WEST;
            }
        }

        if (n != null && !n.hasTail() && !n.hasAgent()) {
            if (n.hasFood()) {
                action = Action.NORTH;
            }
        }

        if (e != null && !e.hasTail() && !e.hasAgent()) {
            if (e.hasFood()) {
                action = Action.EAST;
            }
        }

        if (s != null && !s.hasTail() && !s.hasAgent()) {
            if (s.hasFood()) {
                action = Action.SOUTH;
            }
        }

        return action;
    }

    public LinkedList<Tail> getSnakeBody() {
        return snakeBody;
    }

    public int getFoodEat() {
        return foodEat;
    }

    protected abstract Action decide(Perception perception);

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


    public Color getColor() {
        return color;
    }


    public void clearTails() {
        for (Tail t :snakeBody){
            t.getCell().setTail(null);
        }
        snakeBody.clear();
    }
}
