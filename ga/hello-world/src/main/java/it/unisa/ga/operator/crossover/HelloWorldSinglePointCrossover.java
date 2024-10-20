package it.unisa.ga.operator.crossover;



import java.util.Random;
import it.unisa.ga.individual.MyIndividual;

public class HelloWorldSinglePointCrossover extends CrossoverOperator<MyIndividual> {


    public HelloWorldSinglePointCrossover(double crossoverProbability, Random random) {
        super(crossoverProbability, random);
    }

    @Override
    protected Pairing cross(Pairing pairing) throws CloneNotSupportedException {
        String firstCoding = pairing.getFirstIndividual().getEncoding();
        String secondCoding = pairing.getSecondIndividual().getEncoding();
        int minLength = Math.min(firstCoding.length(), secondCoding.length());
        // e.g. if cutPoint = 1, then the cut occurs between 0 and 1, if cutpoint = 2, it occurs between 1 and 2, and so on...
        int cutPoint = random.nextInt(minLength - 1) + 1;

        // Splitting the strings at the cut point
        String firstCodingLeft = firstCoding.substring(0, cutPoint);
        String firstCodingRight = firstCoding.substring(cutPoint, minLength);

        String secondCodingLeft = secondCoding.substring(0, cutPoint);
        String secondCodingRight = secondCoding.substring(cutPoint, minLength);

        // Cross the strings
        String newFirstCoding = firstCodingLeft + secondCodingRight;
        String newSecondCoding = secondCodingLeft + firstCodingRight;

        MyIndividual offspring1 = (MyIndividual) pairing.getFirstIndividual().clone();
        offspring1.setEncoding(newFirstCoding);
        MyIndividual offspring2 = (MyIndividual) pairing.getSecondIndividual().clone();
        offspring2.setEncoding(newSecondCoding);

        return new Pairing(offspring1, offspring2);
    }
}
