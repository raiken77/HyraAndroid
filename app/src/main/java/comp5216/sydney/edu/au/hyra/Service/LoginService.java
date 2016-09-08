package comp5216.sydney.edu.au.hyra.Service;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.LoginView;

/**
 * Created by apple on 10/15/15.
 */
public class LoginService {

    Database databaseConn;
    public LoginService(Database database) {
        databaseConn = database;
    }


    public void authUser(final String email,final String password, final LoginView view)

    {
        view.showProgress();
        databaseConn.getDatabaseRef().authWithPassword(email, password, new Firebase.AuthResultHandler() {


            //TODO let the view handle the result of the operation with popup dialog or toast
            @Override
            public void onAuthenticated(AuthData authData) {
                view.onComplete(true);
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                view.onComplete(false);
                Log.i("MOCO",firebaseError.getMessage());
            }
        });


    }



}
