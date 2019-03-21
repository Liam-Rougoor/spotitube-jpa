package liam.dea.resources.util;

public interface TokenValidator {

    void validateToken(String token);

    String getUserWithToken(String token);
}
