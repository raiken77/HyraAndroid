package comp5216.sydney.edu.au.hyra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.RequestItemPresenter;
import comp5216.sydney.edu.au.hyra.Service.RequestItemService;
import comp5216.sydney.edu.au.hyra.View.RequestItemView;

public class RequestsActivity extends AppCompatActivity implements RequestItemView {

    ListView requests;
    ArrayAdapter<String> requestsAdapter;
    ArrayList<String> requestsList;
    Map<String,Object> requestValues;
    ArrayList<String>requestID;
    public  final int APPROVE_REQUEST = 650;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        requests = (ListView)findViewById(R.id.requests);
        requestsList = new ArrayList<>();
        requestID = new ArrayList<>();
        requestsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,requestsList);
        requests.setAdapter(requestsAdapter);
        Database data = new Database(this);
        RequestItemPresenter presenter = new RequestItemPresenter(this, new RequestItemService(data));
        presenter.getRequests(data.getEmail());
        setListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_requests, menu);
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

    private void setListener()
    {
        requests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent checkRequestIntent = new Intent(RequestsActivity.this,CheckRequestActivity.class);
                checkRequestIntent.putExtra("requestID",requestID.get(position));
                Log.i("MOCO",requestID.get(position));
                startActivityForResult(checkRequestIntent, APPROVE_REQUEST);
            }
        });
    }

    @Override
    public void onComplete(boolean success) {

    }

    @Override
    public void setLayout(Map<String, Object> values) {

        requestID.add(values.get("requestID").toString());
        requestsAdapter.add(values.get("author").toString());
        requestsAdapter.notifyDataSetChanged();
    }

    @Override
    public void deleteItem(String requestID) {
        int position = requestID.indexOf(requestID);
        requestsList.remove(position);
        requestsAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == APPROVE_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(this,"Request has been approved",Toast.LENGTH_LONG).show();
            }
        }
    }
}
