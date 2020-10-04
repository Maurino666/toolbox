package it.unisa.ga.operators.crossover;

import it.unisa.ga.individuals.Individual;
import it.unisa.ga.operators.GeneticOperator;
import it.unisa.ga.populations.Population;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CrossoverOperator<T extends Individual> extends GeneticOperator<T> {

    class Pairing {
        protected final T firstParent;
        protected final T secondParent;

        protected Pairing(T firstParent, T secondParent) {
            this.firstParent = firstParent;
            this.secondParent = secondParent;
        }
    }

    protected List<Pairing> makePairings(Population<T> population) {
        List<Pairing> pairings = new ArrayList<>();
        // No pairings if there are not enough parents
        if (population.size() < 2) {
            return pairings;
        }

        // Convert to list and randomly permute it
        ArrayList<T> populationList = new ArrayList<>(population);
        Collections.shuffle(populationList);

        // If odd, the last element is not considered
        if (populationList.size() % 2 != 0) {
            populationList.remove(populationList.size() - 1);
        }
        for (int i = 0; i < populationList.size(); i = i + 2) {
            T firstParent = populationList.get(i);
            T secondParent = populationList.get(i + 1);
            Pairing pairing = new Pairing(firstParent, secondParent);
            pairings.add(pairing);
        }
        return pairings;
    }

}
