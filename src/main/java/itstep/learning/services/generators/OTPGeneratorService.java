package itstep.learning.services.generators;

import java.util.Random;

public class OTPGeneratorService implements GeneratorService {
    private static final int LENGTH = 6;

    @Override
    public String generate() {
        Random rand = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) {
            otp.append(rand.nextInt(10));
        }
        return otp.toString();
    }
}
