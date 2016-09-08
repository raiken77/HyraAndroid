package comp5216.sydney.edu.au.hyra.Service;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.RequestItemView;

/**
 * Created by apple on 10/17/15.
 */
public class RequestItemService {

    Database data;

    public RequestItemService(Database data) {
        this.data = data;
    }

    public void sendRequest(final Map<String,Object> values, final RequestItemView view)
    {

        data.getDatabaseRef().child("requests").child(values.get("user").toString()).push().setValue(values, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if(firebaseError == null)
                {
                    data.getDatabaseRef().child("requestSent").child(values.get("author").toString()).push().setValue(values, new Firebase.CompletionListener() {
                        @Override
                        public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                            if(firebaseError == null)
                            {

                                view.onComplete(true);
                            }
                        }
                    });
                }
            }
        });

    }


    public void getRequests(String userID, final RequestItemView view)
    {
        data.getDatabaseRef().child("requests").child(userID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> values = new HashMap<String, Object>((Map<String,Object>)dataSnapshot.getValue());
                values.put("requestID", dataSnapshot.getKey());
               // Log.i("MOCO",values.get("requestID").toString());
                view.setLayout(values);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
               view.deleteItem(dataSnapshot.getKey());
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
