package comp5216.sydney.edu.au.hyra.View;

/**
 * Created by apple on 10/15/15.
 */
public interface LoginView {


    String getEmail();
    String getPassword();

    //TODO progress bar integration with firebase
    void showProgress();
    void updateProgress();
    void onComplete(boolean success);

}
