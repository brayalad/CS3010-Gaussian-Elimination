package Models;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Constants<T extends Number> {
    private final List<T> constants;

    public Constants(final List<T> constants){
        this.constants = constants;
    }

    public T get(final int index){
        return constants.get(index);
    }

    public void set(final int index, final T value){
        constants.set(index, value);
    }

    public List<T> getConstants(){ return List.copyOf(constants); }

    public int size(){ return constants.size(); }

    public void print(){
        System.out.println(Arrays.toString(constants.toArray()));
    }

    public static <T extends Number> Constants<T> copyOf(final Constants<T> constants){
        return new Constants<>(new Vector<>(constants.getConstants()));
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for(final T n : constants) {
            sb.append(String.format("%s ", String.valueOf(n)));
        }

        return sb.toString();
    }

}
