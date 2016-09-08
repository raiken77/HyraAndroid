package comp5216.sydney.edu.au.hyra.Service;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.RegisterView;

/**
 * Created by apple on 10/15/15.
 */
public class RegisterService {

    private Database database;
    public RegisterService(Database data)
    {
    database = data;

    }

    public void addUser(final Map<String,String> input,final RegisterView view)
    {
        view.showProgress();
        database.getDatabaseRef().child("users").createUser(input.get("email"), input.get("password"), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onError(FirebaseError firebaseError) {

                Log.i("MOCO",firebaseError.getMessage());
                view.onComplete(false);
            }

            @Override
            public void onSuccess(Map<String, Object> stringStringMap) {

                input.remove("password");
                database.getDatabaseRef().child("users").child(stringStringMap.get("uid").toString()).setValue(input);
                view.onComplete(true);

            }
        });
    }
}


