package snake;

import snake.snakeAdhoc.SnakeAdhocAgent;
import snake.snakeRandom.SnakeRandomAgent;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Environment {

    public Random random;
    public final Cell[][] grid;
    private final List<SnakeAgent> agents;
    private Food food;
    private final int maxIterations;

    public Environment(int size, int maxIterations) {

        this.maxIterations = maxIterations;

        this.grid = new Cell[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = new Cell(i, j);
            }
        }

        this.agents = new ArrayList<>();
        this.random = new Random();
        initialize(random.nextInt(10));
    }

    public void initialize(int seed) {
        random.setSeed(seed);

        placeAgents();
        placeFood();
    }

    private void placeAgents() {
        SnakeRandomAgent snakeRandomAgent = new SnakeRandomAgent(new Cell(2, 2), Color.BLUE, 1);
        SnakeAdhocAgent snakeAdhocAgent = new SnakeAdhocAgent(new Cell(2, 2), Color.BLUE, 1);
        agents.add(snakeRandomAgent);
        agents.add(snakeAdhocAgent);
    }

    private void placeFood() {
        this.food = new Food(getCell(random.nextInt(10), random.nextInt(10))); //falta verificar se essa posição não é ocupada pela cobra e tornar aleatoria a posição da comida
    }

    public void simulate() {
       if(agents.size() == 1){
           simulateSnkae(maxIterations);
       }else if(agents.size() == 2){
           simulateSnkae(300);
       }

    }

    public void simulateSnkae(int maxIterations) {
        for (int i = 0; i < maxIterations; i++) {
            for (SnakeAgent snakeAgent : agents) {
                snakeAgent.act(this);
                fireUpdatedEnvironment();
                if (getFood()) {
                    placeFood();
                }
            }
        }
    }

    public int getSize() {
        return grid.length;
    }

    public Cell getNorthCell(Cell cell) {
        if (cell.getLine() > 0) {
            return grid[cell.getLine() - 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getSouthCell(Cell cell) {
        if (cell.getLine() < grid.length - 1) {
            return grid[cell.getLine() + 1][cell.getColumn()];
        }
        return null;
    }

    public Cell getEastCell(Cell cell) {
        if (cell.getColumn() < grid[0].length - 1) {
            return grid[cell.getLine()][cell.getColumn() + 1];
        }
        return null;
    }

    public Cell getWestCell(Cell cell) {
        if (cell.getColumn() > 0) {
            return grid[cell.getLine()][cell.getColumn() - 1];
        }
        return null;
    }

    public final Cell getCell(int linha, int coluna) {
        return grid[linha][coluna];
    }

    public Color getCellColor(int linha, int coluna) {
        return grid[linha][coluna].getColor();
    }

    public boolean getFood() {
        for (SnakeAgent snakeAgent : agents) {
            if (snakeAgent.getCell() == food.getCell()) {
                this.food = null;
                return true;
            }
        }
        return false;
    }


    //listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }
}
