package snake.snakeAI;

import snake.*;
import snake.snakeAI.ga.RealVectorIndividual;
import snake.snakeAI.nn.SnakeAIAgent;
import snake.snakeAI.nn.SnakeAIAgent2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SnakeIndividual extends RealVectorIndividual<SnakeProblem, SnakeIndividual> {
    public static final int PESO_COMIDA = 500;
    private int food, movements, snake1Food, snake2Food;
    public int bestFood;
    public int bestMovements;
    public double bestFitness;

    public SnakeIndividual(SnakeProblem problem, int size) {
        super(problem, size);


    }

    public SnakeIndividual(SnakeIndividual original) {
        super(original);
        this.movements = original.movements;
        this.food = original.food;
        this.bestMovements = original.bestMovements;
        this.bestFood = original.bestFood;
        this.bestFitness = original.bestFitness;
        this.snake1Food = this.snake2Food = 0;
    }

    @Override
    public double computeFitness() {
        bestFood = bestMovements = 0;
        Environment environment = problem.getEnvironment();
        List<SnakeAgent> agents = new ArrayList<>();
        int[] vector;
        food = 0;
        movements = 0;
        snake1Food = 0;
        snake2Food = 0;

        double penalty = 0.0;

        for (int i = 0; i < problem.getNumEvironmentSimulations(); i++) {

            environment.initialize(i);

            agents = environment.getAgents();


            if (environment.getOption() == 6) {
                double[] firstHalf = Arrays.copyOfRange(genome, 0, genome.length / 2);
                double[] secondtHalf = Arrays.copyOfRange(genome, genome.length / 2, genome.length);
                ((SnakeAIAgent) agents.get(0)).setWeights(firstHalf);
                ((SnakeAIAgent2) agents.get(1)).setWeights(secondtHalf);

                snake1Food += agents.get(0).getSnakeBody().size();
                snake2Food += agents.get(1).getSnakeBody().size();


            } else {
                environment.setWeights(genome);
            }

            vector = environment.simulateSnakeAi();

            if (snake2Food != snake1Food) {
                penalty = Math.abs(snake1Food - snake2Food) * 500;
            }

            movements += vector[0];
            food += snake1Food + snake2Food;
            bestMovements = vector[2];
            bestFood = vector[3];

        }


        food = food / problem.getNumEvironmentSimulations();
        movements = movements / problem.getNumEvironmentSimulations();

        fitness = ((food * PESO_COMIDA) + (movements * 0.2)) - penalty;

        if (fitness > bestFitness) {
            bestFitness = fitness;
        }

        return fitness;

    }

    public double[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAverage:");
        sb.append("\n\tFitness: " + fitness);
        sb.append("\n\tMovements: " + (double) movements);
        sb.append("\n\tFood Eaten: " + (double) food);
        sb.append("\nBest snake:");
        sb.append("\n\tFitness: " + bestFitness);
        sb.append("\n\tMovements: " + (double) bestMovements);
        sb.append("\n\tFood Eaten: " + (double) bestFood);


        return sb.toString();
    }


    @Override
    public int compareTo(SnakeIndividual i) {
        if (this.fitness > i.fitness)
            return 1;

        if (this.fitness == i.fitness)
            return 0;

        return -1;
    }

    @Override
    public SnakeIndividual clone() {
        return new SnakeIndividual(this);
    }
}
