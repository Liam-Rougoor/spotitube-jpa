package liam.dea.services;

import liam.dea.dataobjects.Login;

public interface LoginService {
    Login getLogin(String user, String password);
}
