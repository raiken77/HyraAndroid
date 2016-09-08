package comp5216.sydney.edu.au.hyra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Model.roomMember;
import comp5216.sydney.edu.au.hyra.Presenter.ChatRoomPresenter;
import comp5216.sydney.edu.au.hyra.Service.ChatRoomService;
import comp5216.sydney.edu.au.hyra.View.ChatRoomView;

public class ChatRoomsActivity extends AppCompatActivity implements ChatRoomView {

    ListView chatRooms;
    ArrayList<roomMember>chatRoomsList;
    ArrayAdapter<roomMember>chatRoomsAdapter;
    Database data;
    ChatRoomPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_rooms);
        chatRooms = (ListView)findViewById(R.id.chatRooms);
        chatRoomsList = new ArrayList<roomMember>();
        chatRoomsAdapter = new ArrayAdapter<roomMember>(this,android.R.layout.simple_list_item_1,chatRoomsList);
        chatRooms.setAdapter(chatRoomsAdapter);
        setListeners();
        data = new Database(this);
        presenter = new ChatRoomPresenter(new ChatRoomService(data),this);
        presenter.retrieveChatRooms(data.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat_rooms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(roomMember members) {
        chatRoomsAdapter.add(members);
        chatRoomsAdapter.notifyDataSetChanged();
    }


    private void setListeners()
    {
        chatRooms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent chatIntent  = new Intent(ChatRoomsActivity.this, ChatActivity.class);
                chatIntent.putExtra("user",data.getEmail());
                chatIntent.putExtra("room",chatRoomsAdapter.getItem(position).getRoomID());
                startActivity(chatIntent);
            }
        });
    }
}
