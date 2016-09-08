package comp5216.sydney.edu.au.hyra.Service;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.OnlineItemsView;

/**
 * Created by apple on 10/15/15.
 */

//Follow the structure /rentitems/category/All the items for that category on firebase
    //item description will have user, item name, item category,price and location
public class OnlineItemsService {

    Database database;

    public OnlineItemsService(Database database) {
        this.database = database;
    }

    public void getItems(String category,final OnlineItemsView view)
    {

        database.getDatabaseRef().child("rentItems").child(category).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                view.setViewWithData((Map<String,Object>)dataSnapshot.getValue());
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

    public void getSingleItem(String item,String category,final OnlineItemsView view)
    {

        database.setQueryInstance(database.getDatabaseRef().child("rentItems").child(category));

        database.getQueryInstance().orderByChild("Title").equalTo(item).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String,Object> values = new HashMap<String, Object>((Map<String, Object>) snapshot.getValue());
                    values.put("operationID",snapshot.getKey());
                   Log.i("MOCO", (dataSnapshot.getValue().toString()));
                            view.setViewWithData(values);
                }

                database.getDatabaseRef().removeEventListener(this);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }


    public void createChat(String currentUser,String otherUser, final OnlineItemsView view)
    {
        final Random randomGenerator = new Random();
        final String roomID = "room" + randomGenerator.nextInt(1000000);
        Map<String,String> roomMembers = new HashMap<>();
        roomMembers.put("memberone",currentUser);
        roomMembers.put("membertwo",otherUser);
        database.getDatabaseRef().child("rooms").child(roomID).setValue(roomMembers, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if(firebaseError == null)
                {
                    view.onComplete(true,roomID);
                }
                else
                {
                    view.onComplete(false,null);
                }
            }
        });
    }

}
