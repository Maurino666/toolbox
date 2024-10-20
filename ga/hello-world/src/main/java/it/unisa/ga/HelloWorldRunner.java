package it.unisa.ga;

import it.unisa.ga.fitness.HelloWorldMSEFunction;
import it.unisa.ga.fitness.HelloWorldMatchingFunction;
import it.unisa.ga.fitness.HelloWorldRMSDFunction;
import it.unisa.ga.individual.MyIndividual;
import it.unisa.ga.metaheuristic.GenerationalGA;
import it.unisa.ga.metaheuristic.SGA;
import it.unisa.ga.operator.crossover.HelloWorldSinglePointCrossover;
import it.unisa.ga.operator.mutation.HelloWorldSinglePointMutation;
import it.unisa.ga.operator.selection.RouletteWheelSelection;
import it.unisa.ga.population.initializer.FixedSizeHelloWorldRandomInitializer;
import it.unisa.ga.results.GAResults;
import it.unisa.ga.stopping.MaxIterationsStoppingCondition;
import it.unisa.ga.stopping.MaxNoImprovementsStoppingConditions;
import it.unisa.ga.stopping.MultipleStoppingCondition;
import it.unisa.ga.stopping.StoppingCondition;

import java.util.Random;

public class HelloWorldRunner {

    public static void main(String[] args) throws CloneNotSupportedException {
        // Hyper parameters
        final int numberOfIndividuals = 100;
        final int maxLen = 12;
        final double mutationProbability = 1;
        final double crossoverProbability = 1;
        final int maxIterations = 10000;
        final int maxIterationsNoImprovements = 100;

        Random random = new Random();
//        HelloWorldMSEFunction fitnessFunction = new HelloWorldMSEFunction(); // Fitness Function
        HelloWorldMatchingFunction fitnessFunction = new HelloWorldMatchingFunction();
//        HelloWorldRMSDFunction fitnessFunction = new HelloWorldRMSDFunction();
        FixedSizeHelloWorldRandomInitializer populationInitializer = new FixedSizeHelloWorldRandomInitializer(numberOfIndividuals, maxLen); // Population Initializer
        RouletteWheelSelection<MyIndividual> selectionOperator = new RouletteWheelSelection<>(random); // Selection Operator
        HelloWorldSinglePointCrossover crossoverOperator = new HelloWorldSinglePointCrossover(crossoverProbability, random); // Crossover Operator
        HelloWorldSinglePointMutation mutationOperator = new HelloWorldSinglePointMutation(mutationProbability, random); // Mutation Operator

        MultipleStoppingCondition stoppingCondition = new MultipleStoppingCondition();
        stoppingCondition.add(new MaxIterationsStoppingCondition(maxIterations));
        stoppingCondition.add(new MaxNoImprovementsStoppingConditions(maxIterationsNoImprovements));

//        StoppingCondition stoppingCondition = new MaxIterationsStoppingCondition(maxIterations);

        GenerationalGA.GASetting<MyIndividual> gaSetting = new GenerationalGA.GASetting<>(fitnessFunction, populationInitializer,
                selectionOperator, crossoverOperator, mutationOperator, stoppingCondition);
        SGA<MyIndividual> geneticAlgorithm = new SGA<>(gaSetting);
        GAResults<MyIndividual> GAResults = geneticAlgorithm.run();
        MyIndividual bestIndividual = GAResults.getBestIndividual();
        System.out.printf("Search terminated in %d/%d iterations.%n", GAResults.getNumberOfIterations(), maxIterations);
        System.out.printf("Best individual is %s, with fitness %.2f.%n", bestIndividual.getEncoding(), bestIndividual.getFitness());
    }

}
