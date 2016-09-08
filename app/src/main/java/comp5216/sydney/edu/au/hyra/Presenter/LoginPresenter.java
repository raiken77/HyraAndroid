package comp5216.sydney.edu.au.hyra.Presenter;

import comp5216.sydney.edu.au.hyra.Service.LoginService;
import comp5216.sydney.edu.au.hyra.View.LoginView;

/**
 * Created by apple on 10/15/15.
 */
public class LoginPresenter {

   private  LoginService loginService;
    private LoginView loginView;

    public LoginPresenter(LoginService service,LoginView view) {
        this.loginService = service;
        this.loginView = view;
    }

    public void authenticateUser()
    {
        loginService.authUser(loginView.getEmail(),loginView.getPassword(),loginView);
    }
}
