package Models;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GaussianMatrix<T extends Number> {
    private final int size;
    private final Coefficients<T> coefficients;
    private final Constants<T> constants;
    private final List<Integer> ind;


    public GaussianMatrix(final int size, final Coefficients<T> coefficients, final Constants<T> constants, final List<Integer> ind){
        this.size = size;
        this.coefficients = coefficients;
        this.constants = constants;
        this.ind = ind;
    }

    public GaussianMatrix(final int size, final Coefficients<T> coefficients, final Constants<T> constants){
        this.size = size;
        this.coefficients = coefficients;
        this.constants = constants;
        this.ind = IntStream.rangeClosed(0,size - 1).boxed().collect(Collectors.toList());
    }

    public int size(){ return  size; }

    public Coefficients<T> getCoefficients(){ return coefficients; }

    public Constants<T> getConstants(){ return constants; }

    public List<Integer> getInd(){ return ind; }

    public void print(){
        coefficients.print();
        constants.print();
    }

    private void printRow(final List<T> row){ System.out.println(Arrays.toString(row.toArray())); }

    @Override
    public String toString(){
        final String nl = "\n";
        final StringBuilder sb = new StringBuilder();
        return sb.append(size).append(nl).append(coefficients).append(constants).append(nl).toString();
    }

}
