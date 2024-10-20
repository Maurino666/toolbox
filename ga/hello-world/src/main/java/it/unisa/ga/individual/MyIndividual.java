package it.unisa.ga.individual;

public class MyIndividual extends StringIndividual {

    private final int maxLen;

    public MyIndividual(int maxLen, String coding) {
        super(coding);
        this.maxLen = maxLen;

        // Truncate or pad the string to maxLen
        if(coding.length() > maxLen){
            setEncoding(coding.substring(0, maxLen));
        } else if(coding.length() < maxLen){
            setEncoding(coding + " ".repeat(maxLen - coding.length()));
        }

    }

    public int getMaxLen() {
        return maxLen;
    }

    @Override
    public String toString() {
        return "MyIndividual{" +
                super.toString() +
                ", maxLen=" + maxLen +
                '}';
    }
}
