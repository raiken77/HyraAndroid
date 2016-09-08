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
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.OnlineItemsPresenter;
import comp5216.sydney.edu.au.hyra.Resources.ProgressResouce;
import comp5216.sydney.edu.au.hyra.Service.OnlineItemsService;
import comp5216.sydney.edu.au.hyra.View.OnlineItemsView;

public class OnlineItemsActivity extends AppCompatActivity implements OnlineItemsView{


    ListView itemsView;
    ArrayAdapter<String> itemsAdapter;
    ArrayList<String> itemsList;
    ProgressResouce dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_items);
        dialog = new ProgressResouce(this);
        itemsView = (ListView)findViewById(R.id.itemsView);
        itemsList = new ArrayList<>();
        itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemsList);
        itemsView.setAdapter(itemsAdapter);
       String category =  (getIntent().getExtras().get("Category").toString().isEmpty()) ? null :getIntent().getExtras().get("Category").toString() ;

        if(category != null) {

            Database data = new Database(this);
            OnlineItemsPresenter onlineItemsPresenter = new OnlineItemsPresenter(new OnlineItemsService(data), this);
            onlineItemsPresenter.getRentItems(category);
        }

        setupListener(category);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_online_items, menu);
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
    public void setViewWithData(Map<String, Object> items) {

        itemsAdapter.add(items.get("Title").toString());
        itemsAdapter.notifyDataSetChanged();
    }



    private void setupListener(final String category)
    {
        itemsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent itemDetails = new Intent(OnlineItemsActivity.this, ItemDetailsActivity.class);
                itemDetails.putExtra("item", itemsAdapter.getItem(position));
                itemDetails.putExtra("category", category);
                startActivity(itemDetails);
            }
        });
    }



    @Override
    public void onComplete(boolean success, String roomID) {

    }
}
