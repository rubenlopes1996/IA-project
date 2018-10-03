package snake.snakeAI.ga.geneticOperators;

import snake.Environment;
import snake.snakeAI.ga.GeneticAlgorithm;
import snake.snakeAI.ga.RealVectorIndividual;

public class MutationChange<I extends RealVectorIndividual> extends Mutation<I> {


    public MutationChange(double probability /*TODO?*/) {
        super(probability);
        // TODO
    }

    @Override
    public void run(I ind) {
        for (int i = 0; i < ind.getNumGenes(); i++) {
            if (GeneticAlgorithm.random.nextDouble() < probability) {
/*
                double x = Environment.random.nextGaussian();
                double y = (x * 0.5) + 0.5;
                double newVal = Math.rint(y * 100000.0) * 0.00001;
                ind.setGene(i, newVal);*/
                ind.setGene(i, GeneticAlgorithm.random.nextDouble()*2-1);

            }
        }
    }

    @Override
    public String toString() {
        return "Uniform distribution mutation (" + probability /* + TODO?*/;
    }
}