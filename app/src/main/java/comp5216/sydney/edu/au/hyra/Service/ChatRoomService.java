package comp5216.sydney.edu.au.hyra.Service;

import android.util.Log;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Model.roomMember;
import comp5216.sydney.edu.au.hyra.View.ChatRoomView;

/**
 * Created by apple on 10/17/15.
 */
public class ChatRoomService {

    Database database;
    public ChatRoomService(Database data) {
        this.database = data;
    }


    public void getRooms(String userID, final ChatRoomView view)
    {
        database.setQueryInstance(database.getDatabaseRef().child("rooms"));
        database.getQueryInstance().orderByChild(userID).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("MOCO", dataSnapshot.getValue().toString());
//                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Map<String, Object> input = (Map<String, Object>) dataSnapshot.getValue();
                    roomMember members = new roomMember();
                    members.setMemberOne(input.get("memberone").toString());
                    members.setMemberOne(input.get("membertwo").toString());
                    members.setRoomID(dataSnapshot.getKey());

                    view.setData(members);
//                }
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
