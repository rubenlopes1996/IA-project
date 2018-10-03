package snake.snakeAdhoc;

import snake.*;

import java.awt.*;


public class SnakeAdhocAgent extends SnakeAgent {

    public SnakeAdhocAgent(Cell cell) {
        super(cell, Color.CYAN);
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

    public Action decide(Perception perception) {
        Cell food = perception.getFood();

        Action action;

        action = foodChase(food, perception);

        return action;
    }

    private Action foodChase(Cell food, Perception perception) {
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();
        Action action = null;

        if (this.getCell().getLine() == food.getLine()) {
            if (this.getCell().getColumn() < food.getColumn()) {
                if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else {
                    action = Action.WEST;
                }
            } else if (this.getCell().getColumn() > food.getColumn()) {
                if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else {
                    action = Action.EAST;
                }
            }
        }

        if (this.getCell().getLine() == food.getLine()) {
            if (this.getCell().getColumn() > food.getColumn()) {
                if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else {
                    action = Action.EAST;
                }
            } else if (this.getCell().getColumn() < food.getColumn()) {
                if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else {
                    action = Action.WEST;
                }
            }
        }

        if (this.getCell().getLine() < food.getLine()) {
            if (this.getCell().getColumn() == food.getColumn()) {
                if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.SOUTH;
                }
            } else if (this.getCell().getColumn() != food.getColumn()) {
                if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.NORTH;
                }
            }
        }

        if (this.getCell().getLine() > food.getLine()) {
            if (this.getCell().getColumn() == food.getColumn()) {
                if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.NORTH;
                }
            } else if (this.getCell().getColumn() != food.getColumn()) {
                if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else {
                    action = Action.SOUTH;
                }
            }
        }

        if (this.getCell().getColumn() == food.getColumn()) {
            if (this.getCell().getLine() > food.getLine()) {
                if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.SOUTH;
                }
            } else if (this.getCell().getLine() < food.getLine()) {
                if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.NORTH;
                }
            }
        }

        if (this.getCell().getColumn() == food.getColumn()) {
            if (this.getCell().getLine() < food.getLine()) {
                if (s != null && !s.hasTail() && !s.hasAgent()) {
                    action = Action.SOUTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.NORTH;
                }
            } else if (this.getCell().getLine() > food.getLine()) {
                if (n != null && !n.hasTail() && !n.hasAgent()) {
                    action = Action.NORTH;
                } else if (e != null && !e.hasTail() && !e.hasAgent()) {
                    action = Action.EAST;
                } else if (w != null && !w.hasTail() && !w.hasAgent()) {
                    action = Action.WEST;
                } else {
                    action = Action.SOUTH;
                }
            }
        }
        return action;
    }
}

