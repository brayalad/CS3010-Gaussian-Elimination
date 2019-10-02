import Algorithims.GaussianElimination;
import Algorithims.GaussianEliminationDelegate;
import Algorithims.NaiveGaussianElimination;
import Algorithims.ScaledPartialPivoting;
import Engine.Engine;
import UI.TextUserInterface;
import UI.UserInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Arrays;


public class gaussian {

    public static void main(String[] args){
        final Map<String, GaussianElimination> algorithms = new HashMap<>();
        algorithms.put("--nge", new NaiveGaussianElimination());
        algorithms.put("--spp", new ScaledPartialPivoting());

        final GaussianEliminationDelegate gaussianEliminationDelegate = new GaussianEliminationDelegate(Collections.unmodifiableMap(algorithms));
        final UserInterface ui = new TextUserInterface(new Scanner(System.in));

        final Engine engine = new Engine(gaussianEliminationDelegate, ui);

        engine.start(Arrays.asList(args));
    }

}
