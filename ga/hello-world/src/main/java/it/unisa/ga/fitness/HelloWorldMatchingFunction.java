package it.unisa.ga.fitness;

import it.unisa.ga.individual.MyIndividual;

public class HelloWorldMatchingFunction extends FitnessFunction<MyIndividual>{

    public HelloWorldMatchingFunction() {
        super(true);
    }

    @Override
    public void evaluate(MyIndividual individual) {
        int matchingValue = HelloWorldMatchingCharacters(individual.getEncoding());
        individual.setFitness(matchingValue);
    }

    private int HelloWorldMatchingCharacters(String decode){
        String str = "Hello World!";
        int result = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == decode.charAt(i)) { result++; }
        }

        return result;
    }
}
