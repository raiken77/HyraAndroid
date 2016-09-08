package comp5216.sydney.edu.au.hyra.Service;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.View.CheckRequestView;

/**
 * Created by apple on 10/17/15.
 */
public class CheckRequestService {

    Database data;

    public CheckRequestService(Database data) {
        this.data = data;
    }


    public void getSingleRequest(String requestID,final  CheckRequestView view)
    {
        data.getDatabaseRef().child("requests").child(data.getEmail()).child(requestID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("MOCO", dataSnapshot.getValue().toString());
                view.setView((Map<String, Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public void updateRentItems(final String operationID, final String category ,final CheckRequestView view,final String requestId)
    {


        data.getDatabaseRef().child("requests").child(data.getEmail()).child(requestId).removeValue();
        data.setQueryInstance(data.getDatabaseRef().child("rentItems"));
        data.getQueryInstance().orderByChild(operationID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,Object> value = new HashMap<String, Object>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                   value = (Map<String,Object>) snapshot.getValue();

                }

                data.getDatabaseRef().child("rentedItems").child(data.getEmail()).push().setValue(value, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        data.getDatabaseRef().child("rentItems").child(category).child(operationID).removeValue();
                    }
                });



            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
