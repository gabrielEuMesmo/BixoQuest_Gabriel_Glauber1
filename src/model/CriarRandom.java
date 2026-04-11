package model;

import java.util.Random;

public class CriarRandom {

    private static final String[] nomesColegas = {"Gbriel"};
private static final String[] cargosColegas = {"EXA", "TEC", "ALG"};


    private static final Random random = new Random();

    public static Colega criarColega(){
        boolean cargo = random.nextBoolean();
        String cargoTipo = "";
        if(cargo){
            cargoTipo = cargosColegas[random.nextInt(2)];
        }
        return new Colega(nomesColegas[random.nextInt(nomesColegas.length)], random.nextInt(1,10), cargo, cargoTipo, random.nextInt(10), random.nextInt(10), random.nextInt(10));
    }
}
