package comp5216.sydney.edu.au.hyra.View;

import java.util.Map;

/**
 * Created by apple on 10/15/15.
 */
public interface RegisterView {

    Map<String,String> getUserInput();

    void showEmailError(int resId);
    void showPasswordError(int resId);
    void showFirstnameError(int resId);
    void showSurnameError(int resId);

    void onComplete(boolean success);
    void showProgress();
    void updateProgress();
}
