package comp5216.sydney.edu.au.hyra;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.CheckRequestPresenter;
import comp5216.sydney.edu.au.hyra.Service.CheckRequestService;
import comp5216.sydney.edu.au.hyra.View.CheckRequestView;


public class CheckRequestActivity extends AppCompatActivity implements CheckRequestView {

    private Map<String,Object> requestValues;

    Database database;
    CheckRequestPresenter presenter;
    String requestId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_request);
        requestId = getIntent().getExtras().getString("requestID");
        database = new Database(this);
        presenter = new CheckRequestPresenter(new CheckRequestService(database),this);
        presenter.getSingleRequest(requestId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_check_request, menu);
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
    public void setView(Map<String, Object> values) {
        requestValues = new HashMap<>(values);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.requestValues);

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


    @Override
    public void onComplete(boolean success) {
        if(success)
        {
            setResult(RESULT_OK);
            finish();
        }
    }

    public void approve(View v)
    {
        presenter.updateRentItem(requestValues.get("operationID").toString(),requestValues.get("category").toString(),
                requestId);
    }
}
