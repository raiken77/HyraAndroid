package comp5216.sydney.edu.au.hyra.Service;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.RentItemView;

/**
 * Created by apple on 10/15/15.
 */
public class RentedItemService {

Database database;
    public RentedItemService(Database data) {
        this.database = data;
    }



    public void getRentedItems(final RentItemView view)
    {
        database.getDatabaseRef().child("RentedItems").child(database.getUserID()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               view.setDetails((Map<String,Object>)dataSnapshot.getValue());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
