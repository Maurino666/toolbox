package it.unisa.ga.operator.mutation;

import it.unisa.ga.individual.MyIndividual;
import java.util.Random;

public class HelloWorldSinglePointMutation extends MutationOperator<MyIndividual> {

    public HelloWorldSinglePointMutation(double mutationProbability, Random random) {
        super(mutationProbability, random);
    }

    @Override
    protected MyIndividual mutate(MyIndividual individual) throws CloneNotSupportedException {
        String coding = individual.getEncoding();
        int pos = random.nextInt(coding.length());
        char[] chars = coding.toCharArray();
        chars[pos] = (char) (random.nextInt(126 - 32 + 1) + 32);
        MyIndividual mutatedIndividual = (MyIndividual) individual.clone();
        mutatedIndividual.setEncoding(String.valueOf(chars));
        return mutatedIndividual;
    }
}
