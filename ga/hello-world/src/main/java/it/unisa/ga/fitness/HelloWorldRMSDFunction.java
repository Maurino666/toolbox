package it.unisa.ga.fitness;

import it.unisa.ga.individual.MyIndividual;

public class HelloWorldRMSDFunction extends FitnessFunction<MyIndividual>{
    public HelloWorldRMSDFunction() {
        super(false);
    }

    @Override
    public void evaluate(MyIndividual individual) {
        int msa = HelloWorldRMSD(individual.getEncoding());
        individual.setFitness(msa);
    }

    // Calculates "Hello World!" RMSD value for the given string
    private int HelloWorldRMSD(String decode) {
        String str = "Hello World!";
        int squaredErrorsSum = 0;

        //Calculates the sum of the Squared Error for each character in the string
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);  // str character at index i
            char ch2 = decode.charAt(i);  // decode character at index i
            squaredErrorsSum += (int) Math.pow(ch - ch2, 2);  // Squared Error for the character
        }

        return (int) Math.sqrt((double) squaredErrorsSum / str.length()); //Root Mean Square Deviation
    }
}
