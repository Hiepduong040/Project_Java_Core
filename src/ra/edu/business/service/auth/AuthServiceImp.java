package ra.edu.business.service.auth;

import ra.edu.business.dao.auth.AuthDAOImp;

public class AuthServiceImp implements AuthService {
    private AuthDAOImp authDAOImp;

    public AuthServiceImp() {
        authDAOImp = new AuthDAOImp();
    }

    @Override
    public Object login(String username, String password) {
        return authDAOImp.login(username, password);
    }
}