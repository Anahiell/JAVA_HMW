package itstep.learning.services.generators;

import java.util.Random;

public class BasicPasswordGeneratorService implements GeneratorService {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+";
    private static final int LENGTH = 12;

    @Override
    public String generate() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
}
