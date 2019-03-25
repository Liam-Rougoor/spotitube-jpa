package liam.dea.services;

public interface TokenService {

    boolean validateToken(String token);

    String getUserWithToken(String token);
}
