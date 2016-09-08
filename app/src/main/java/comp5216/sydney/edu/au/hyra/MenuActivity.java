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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;

//TODO put a custom adapter with a textview and an image
public class    MenuActivity extends AppCompatActivity {

    ListView items;
    List<String> itemsList;
    ArrayAdapter<CharSequence> itemsAdapter;
    public static final int RENT_ITEM = 850;
    public static final int PROFILE = 950;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemsmenu);
        items = (ListView)findViewById(R.id.ItemsList);
        itemsList = new ArrayList<>();
        itemsAdapter =  ArrayAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_list_item_1);
        items.setAdapter(itemsAdapter);
        setUpListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_items, menu);
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

        //Take to request page
        if(id == R.id.requests)
        {
            Intent requestsIntent = new Intent(this,RequestsActivity.class);
            startActivity(requestsIntent);
        }

        //Take to profile page
        if(id == R.id.profile)
        {
            Intent profileIntent=  new Intent(this,ProfileActivity.class);
            startActivity(profileIntent);
        }

        if(id == R.id.logoutButton)
        {
            Intent logoutIntent = new Intent(this,LoginActivity.class);
            Database data = new Database(this);
            data.getDatabaseRef().unauth();
            startActivity(logoutIntent);
        }

        return super.onOptionsItemSelected(item);
    }


    private void setUpListener()
    {
        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent getItems = new Intent(MenuActivity.this,OnlineItemsActivity.class);
                getItems.putExtra("Category",itemsAdapter.getItem(position));
                startActivity(getItems);
            }
        });
    }

    //Disabling back button to prevent user from going back to login screen
    @Override
    public void onBackPressed() {

    }


    public void rentItem(View v)
    {
        Intent rentIntent =  new Intent(this,RentItemActivity.class);
        startActivityForResult(rentIntent,RENT_ITEM);
    }

    public void openChatRoom(View v)
    {
        Intent chatRoomIntent = new Intent(this,ChatRoomsActivity.class);
        startActivity(chatRoomIntent);
    }

    public void showRequests()
    {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RENT_ITEM)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this,"Item has been placed online successfully",Toast.LENGTH_LONG).show();
            }
        }
    }


}
