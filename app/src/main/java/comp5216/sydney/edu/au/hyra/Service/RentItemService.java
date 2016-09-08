package comp5216.sydney.edu.au.hyra.Service;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.RentItemView;

/**
 * Created by apple on 10/15/15.
 */
public class RentItemService {

    Database database;

    public RentItemService(Database data)
    {
        this.database = data;
    }

    //TODO add view progress bar logic
    public void addItem(Map<String,Object> itemDetails, final RentItemView view)
    {
        database.getDatabaseRef().child("rentItems").child(itemDetails.get("category").toString()).push().setValue(itemDetails, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {

                    view.onComplete(true);

            }
        });
    }
}
