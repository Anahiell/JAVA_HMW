package itstep.learning.services.generators;

import java.security.SecureRandom;
import java.util.Base64;

public class FasolGeneratorService implements GeneratorService {
    private static final int LENGTH = 16;

    @Override
    public String generate() {
        SecureRandom random = new SecureRandom();
        byte[] fasol = new byte[LENGTH];
        random.nextBytes(fasol);
        return Base64.getEncoder().encodeToString(fasol);
    }
}
