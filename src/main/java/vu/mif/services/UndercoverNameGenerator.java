package vu.mif.services;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ApplicationScoped
public class UndercoverNameGenerator {

    private String[] undercoverNames = {"vanagas", "siaubunas", "velnias", "grafas", "hitmanas" };

    private int[] undercoverNameNumber = {69, 420, 123, 1337, 007};


    public String generateUndercoverName() {
        Random rand = new Random();
        int rand_int = rand.nextInt(5);
        int rand_int2 = rand.nextInt(5);
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
         String generatedUndercoverName = undercoverNames[rand_int] + undercoverNameNumber[rand_int2];
        return generatedUndercoverName;
    }
}
