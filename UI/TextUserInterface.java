package UI;

import Models.GaussianMatrix;

import java.util.List;
import java.util.Scanner;

public class TextUserInterface implements UserInterface{
    private final Scanner scanner;

    public TextUserInterface(final Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public void menu(){
        System.out.println(MENU);
    }

    @Override
    public void algorithms(){
        System.out.print(ALGORITHM_MENU);
    }

    @Override
    public void printError(final String error){
        System.out.printf(ERROR_FORMAT, error);
    }

    @Override
    public void printMatrix(final GaussianMatrix<?> gaussianMatrix){
        System.out.printf(PRINT_MATRIX_FORMAT, gaussianMatrix);
    }

    @Override
    public void operation(final List<?> gaussianMatrix, final String fileName){
        System.out.printf(OPERATION_FORMAT, gaussianMatrix, fileName);
    }

    @Override
    public String getInput(){
        System.out.print(ENTER_CHOICE);
        return scanner.nextLine();
    }

    public void invalidInput(){
        System.out.println(INVALID_INPUT);
    }

}
