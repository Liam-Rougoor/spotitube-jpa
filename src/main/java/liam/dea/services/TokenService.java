package liam.dea.services;

public interface TokenService {

    void validateToken(String token);

    String getUserWithToken(String token);
}
