package comp5216.sydney.edu.au.hyra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.OnlineItemsPresenter;
import comp5216.sydney.edu.au.hyra.Service.OnlineItemsService;
import comp5216.sydney.edu.au.hyra.View.OnlineItemsView;

public class ItemDetailsActivity extends AppCompatActivity implements OnlineItemsView {

    Button chatButton;
    Button requestButton;
    OnlineItemsPresenter presenter;
    Database database;
    Map<String,Object> retrievedItems;
    public final int REQUEST_ITEM = 600;

    TextView user;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_item_details);
        user = (TextView)findViewById(R.id.usernameValue);
        price = (TextView)findViewById(R.id.priceValue);
        chatButton = (Button)findViewById(R.id.chat);
        requestButton = (Button)findViewById(R.id.requestItem);
        String itemName = (getIntent().getExtras().get("item").toString() == null) ? null :getIntent().getExtras().get("item").toString() ;
        String itemCategory = (getIntent().getExtras().get("category").toString() == null) ? null :(getIntent().getExtras().get("category").toString()) ;

        if(itemName !=null && itemCategory != null) {
             database = new Database(this);

            presenter = new OnlineItemsPresenter(new OnlineItemsService(database), this);

            presenter.getSingleItem(itemName,itemCategory);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_details, menu);
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

    //TODO improve linear layout by already specifying some child view elements in the XML e.g price
    @Override
    public void setViewWithData(Map<String, Object> items) {


        retrievedItems = new HashMap<>(items);

        items.remove("operationID");

        user.setText(items.get("user").toString());

        if(user.getText().toString().equalsIgnoreCase(database.getEmail()))
        {
            chatButton.setVisibility(View.GONE);
            requestButton.setVisibility(View.GONE);
        }

        price.setText(items.get("price").toString());
        items.remove("user");
        items.remove("price");



        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.values);

        for(Map.Entry<String,Object> entry : items.entrySet())
        {

            TextView key = new TextView(this);

            TextView value = new TextView(this);

            TextView whiteSpace =new TextView(this);
            whiteSpace.setText(" ");
            key.setText(entry.getKey() + ":");

                value.setText(entry.getValue().toString());
            linearLayout.addView(key);
            linearLayout.addView(value);
            linearLayout.addView(whiteSpace);
        }

    }

    @Override
    public void onComplete(boolean success,String roomID) {

        if(success)
        {
            Intent chatIntent=  new Intent(this,ChatActivity.class);
            chatIntent.putExtra("otherUser",user.getText().toString());
            chatIntent.putExtra("room",roomID);
            chatIntent.putExtra("user",database.getEmail());
            startActivity(chatIntent);
        }
    }

    public void openChat(View v)
    {
        presenter.createChatRoom(database.getEmail(),user.getText().toString());
    }
    public void openRequestView(View v)
    {
        Intent reqestItemIntent= new Intent(this,RequestItemActivity.class);
        reqestItemIntent.putExtra("values",(HashMap<String,Object>)retrievedItems);
        startActivityForResult(reqestItemIntent, REQUEST_ITEM);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_ITEM)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this,"Request Sent",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
