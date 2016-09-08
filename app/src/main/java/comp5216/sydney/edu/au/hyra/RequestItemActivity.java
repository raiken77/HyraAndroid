package comp5216.sydney.edu.au.hyra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.RequestItemPresenter;
import comp5216.sydney.edu.au.hyra.Service.RequestItemService;
import comp5216.sydney.edu.au.hyra.View.RequestItemView;

public class RequestItemActivity extends AppCompatActivity implements RequestItemView {

    Map<String,Object> values;
    Spinner numHours;
    ArrayAdapter<CharSequence>hoursAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_item);
        values = (HashMap<String,Object>)getIntent().getExtras().get("values");
        numHours = (Spinner)findViewById(R.id.numHours);
        hoursAdapter = hoursAdapter.createFromResource(this,R.array.hours,android.R.layout.simple_spinner_item);
        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numHours.setAdapter(hoursAdapter);
        numHours.setPrompt("Category");
        populateView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_request_item, menu);
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

    private void populateView()
    {

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.requestItemLayout);

        for(Map.Entry<String,Object> entry : values.entrySet())
        {
            TextView key = new TextView(this);
            TextView value = new TextView(this);
            TextView whiteSpace =new TextView(this);
            whiteSpace.setText(" ");
            key.setText(entry.getKey() + ":");
            if(entry.getKey().equalsIgnoreCase("operationID"))
            {
                continue;
            }
            if(entry.getKey().equalsIgnoreCase("price"))
            {

                value.setText("$"+entry.getValue().toString());
            }
            else {
                value.setText(entry.getValue().toString());
            }
            linearLayout.addView(key);
            linearLayout.addView(value);
            linearLayout.addView(whiteSpace);
        }
    }

    public void sendRequest(View v)
    {
        Database data = new Database(this);
        RequestItemPresenter presenter = new RequestItemPresenter(this,new RequestItemService(data));
        values.put("author",data.getEmail());
        values.put("numHours",numHours.getSelectedItem().toString());
        presenter.sendRequest(values);
    }

    @Override
    public void onComplete(boolean success) {

        if(success)
        {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void setLayout(Map<String, Object> values) {

    }

    @Override
    public void deleteItem(String requestID) {

    }
}
