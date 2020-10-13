package it.unisa.ga.populations;

import it.unisa.ga.individuals.Individual;

import java.util.HashSet;
import java.util.Objects;

public class Population<T extends Individual> extends HashSet<T> {

    private final long id;
    private T bestIndividual;

    public Population(long id) {
        this.id = id;
        this.bestIndividual = null;
    }

    public long getId() {
        return id;
    }

    public T getBestIndividual() {
        return bestIndividual;
    }

    public void setBestIndividual(T bestIndividual) {
        this.bestIndividual = bestIndividual;
    }

    public double getAverageFitness() {
        return super.stream()
                .mapToDouble(Individual::getFitness)
                .average().orElse(0.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Population<?> that = (Population<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
