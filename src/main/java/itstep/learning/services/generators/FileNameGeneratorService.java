package itstep.learning.services.generators;

import java.util.Random;

public class FileNameGeneratorService implements GeneratorService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final int LENGHT = 10;

    @Override
    public String generate() {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGHT; i++) {
            result.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return result.toString();
    }
}
