package comp5216.sydney.edu.au.hyra.DatabaseResource;

import android.content.Context;

import com.firebase.client.Firebase;
import com.firebase.client.Query;


public class Database {

    public static final String firebaseUrl = "https://sizzling-inferno-941.firebaseio.com/";
    String userID;
    private Firebase databaseRef;
    private Query queryInstance;
    private String email;
    private String username;




    public Database(Context context){

        Firebase.setAndroidContext(context);
        databaseRef = new Firebase(firebaseUrl);

        if (databaseRef.getAuth() != null) {
            userID = databaseRef.getAuth().getUid();
        }
        queryInstance = databaseRef;


    }

    public Firebase getDatabaseRef() {
        return databaseRef;
    }

    public String getUserID() {
        return userID;
    }

    public Query getQueryInstance() {

        return queryInstance;
    }

    public void setQueryInstance(Query queryInstance) {
        this.queryInstance = queryInstance;
    }

    public String getEmail() {
        if(email == null)
        {
            String [] tempEmail = databaseRef.getAuth().getProviderData().get("email").toString().split("@");
            email =  tempEmail[0];
        }
        return email;
    }

//    public String getSplitEmail()
//    {
//        if(username == null)
//        {
//            username = email.split("@")[0];
//        }
//        return username;
//    }
}
