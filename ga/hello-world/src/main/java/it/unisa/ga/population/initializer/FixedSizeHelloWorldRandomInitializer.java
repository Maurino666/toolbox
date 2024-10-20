package it.unisa.ga.population.initializer;


import it.unisa.ga.individual.MyIndividual;
import it.unisa.ga.population.Population;
import it.unisa.ga.population.UpperBoundedPopulation;

import java.util.Random;

public class FixedSizeHelloWorldRandomInitializer extends PopulationInitializer<MyIndividual> {

    private final int numberOfIndividuals;
    private final int maxLen;

    public FixedSizeHelloWorldRandomInitializer(int numberOfIndividuals, int maxLen) {
        this.numberOfIndividuals = Math.max(numberOfIndividuals, 1);
        this.maxLen = Math.max(maxLen, 1);
    }

    @Override
    public Population<MyIndividual> initialize() {
        UpperBoundedPopulation<MyIndividual> population = new UpperBoundedPopulation<>(0, numberOfIndividuals);
        for (int i = 0; i < numberOfIndividuals; i++) {
            String randomCoding = generateRandomString();
            MyIndividual individual = new MyIndividual(maxLen, randomCoding);
            population.add(individual);
        }
        return population;
    }

    private String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(maxLen);

        // Generates casual string containing unicode characters from 32 (space) to 126 (~)
        for (int i = 0; i < maxLen; i++) {
            int codiceUnicode = random.nextInt(126 - 32 + 1) + 32;
            sb.append((char) codiceUnicode);  // Converts unicode to char
        }

        return sb.toString();
    }

}
