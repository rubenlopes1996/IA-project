package snake.snakeAI.nn;

import snake.*;


import java.awt.Color;

//Tarefa 3
public class SnakeAIAgent extends SnakeAgent {

    final private int inputLayerSize;
    final private int hiddenLayerSize;
    final private int outputLayerSize;

    /**
     * Network inputs array.
     */
    final private int[] inputs;
    /**
     * Hiddden layer weights.
     */
    final private double[][] w1;
    /**
     * Output layer weights.
     */
    final private double[][] w2;
    /**
     * Hidden layer activation values.
     */
    final private double[] hiddenLayerOutput;
    /**
     * Output layer activation values.
     */
    final private double[] output;

    final private int[] inputLayer;

    public SnakeAIAgent(Cell cell, int inputLayerSize, int hiddenLayerSize, int outputLayerSize) {
        super(cell, Color.yellow);
        this.inputLayerSize = inputLayerSize;
        this.hiddenLayerSize = hiddenLayerSize;
        this.outputLayerSize = outputLayerSize;
        inputs = new int[inputLayerSize];
        inputs[inputs.length - 1] = -1; //bias entry
        w1 = new double[inputLayerSize][hiddenLayerSize]; // the bias entry for the hidden layer neurons is already counted in inputLayerSize variable
        w2 = new double[hiddenLayerSize + 1][outputLayerSize]; // + 1 due to the bias entry for the output neurons
        hiddenLayerOutput = new double[hiddenLayerSize + 1];
        hiddenLayerOutput[hiddenLayerSize] = -1; // the bias entry for the output neurons
        output = new double[outputLayerSize];
        inputLayer = new int[inputLayerSize];
    }


    /**
     * Initializes the network's weights
     *
     * @param weights vector of weights comming from the individual.
     */
    public void setWeights(double[] weights) {
        int k = 0;
        for (int i = 0; i < inputLayerSize; i++) {
            for (int j = 0; j < hiddenLayerSize; j++) {
                w1[i][j] = weights[k++];
            }
        }
        for (int i = 0; i < hiddenLayerSize + 1; i++) {
            for (int j = 0; j < outputLayerSize; j++) {
                w2[i][j] = weights[k++];
            }
        }
    }

    /**
     * Computes the output of the network for the inputs saved in the class
     * vector "inputs".
     */
    public void forwardPropagation() {
        float somaPesada = 0;
        for (int i = 0; i < hiddenLayerSize; i++) {
            for (int j = 0; j < inputLayerSize; j++) {
                somaPesada += inputs[j] * w1[j][i];
            }

            hiddenLayerOutput[i] = sigmoide(somaPesada);
        }

        for (int i = 0; i < outputLayerSize; i++) {
            somaPesada = 0;
            for (int j = 0; j < hiddenLayerSize; j++) {
                somaPesada += hiddenLayerOutput[j] * w2[j][i];
            }
            output[i] = somaPesada;
        }

    }

    private double sigmoide(float somaPesada) {
        return 1 / (1 + Math.pow(Math.E, -somaPesada));
    }

    @Override
    public Action decide(Perception perception) {
        Cell food = perception.getFood();
        Cell w = perception.getW();
        Cell n = perception.getN();
        Cell e = perception.getE();
        Cell s = perception.getS();
        Action action = null;


        inputs[0] = n != null && n.hasAgent() && n.hasTail() ? 1 : 0;
        inputs[1] = e != null && e.hasAgent() && e.hasTail() ? 1 : 0;
        inputs[2] = s != null && s.hasAgent() && s.hasTail() ? 1 : 0;
        inputs[3] = w != null && w.hasAgent() && w.hasTail() ? 1 : 0;

        inputs[4] = n != null && n.hasFood() ? 1 : 0;
        inputs[5] = e != null && e.hasFood() ? 1 : 0;
        inputs[6] = s != null && s.hasFood() ? 1 : 0;
        inputs[7] = w != null && w.hasFood() ? 1 : 0;

        inputs[8] = food.getLine() > cell.getLine() ? 1 : food.getLine() < cell.getLine() ? -1 : 0;
        inputs[9] = food.getColumn() > cell.getColumn() ? 1 : food.getColumn() < cell.getColumn() ? -1 : 0;

        forwardPropagation();

        double max = output[0];
        for (int i = 1; i < output.length; i++) {
            if (output[i] > max) {
                max = output[i];
            }
        }

        if (max == output[0]) {
            action = Action.NORTH;
        }
        if (max == output[1]) {
            action = Action.EAST;
        }
        if (max == output[2]) {
            action = Action.SOUTH;
        }
        if (max == output[3]) {
            action = Action.WEST;
        }
        return action;
    }
}
