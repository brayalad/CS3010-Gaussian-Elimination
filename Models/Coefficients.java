package Models;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Coefficients<T extends  Number> {
    private final List<List<T>> matrix;

    public Coefficients(final List<List<T>> matrix){
        this.matrix = matrix;
    }

    public T get(final int row, final int column){
        return matrix.get(row).get(column);
    }

    public List<T> get(final int row) { return matrix.get(row); }

    public void set(final int row, final int column, final T value){
        matrix.get(row).set(column,value);
    }

    public int size(){ return matrix.size(); }

    public List<List<T>> getMatrix(){ return List.copyOf(matrix); }

    public void print(){
        matrix.stream()
                .map(List::toArray)
                .map(Arrays::toString)
                .forEach(System.out::println);
    }



    public static <T extends Number> Coefficients<T> copyOf(final Coefficients<T> coefficients){
        final List<List<T>> original = coefficients.getMatrix();

        final List<List<T>> copy = new Vector<>(original.size());
        for(final List<T> row : original){
            copy.add(new Vector<>(row));
        }

        return new Coefficients<>(copy);
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for(List<T> row : matrix) {
            final StringBuilder rsb = new StringBuilder();
            for (final T n : row) {
                rsb.append(String.format("%s ", String.valueOf(n)));
            }
            sb.append(String.format("%s\n", rsb.toString()));
        }

        return sb.toString();
    }

}
