package nl.han.dea.marijn.services.token;

import java.util.UUID;

public class UUIDGenerator implements TokenGenerator {

    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
