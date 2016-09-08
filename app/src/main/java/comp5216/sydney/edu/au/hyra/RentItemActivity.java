package comp5216.sydney.edu.au.hyra;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Fragments.ArduinoKitFragment;
import comp5216.sydney.edu.au.hyra.Fragments.BooksFragment;
import comp5216.sydney.edu.au.hyra.Fragments.CablesFragment;
import comp5216.sydney.edu.au.hyra.Fragments.CalculatorsFragment;
import comp5216.sydney.edu.au.hyra.Fragments.LaptopChargerFragment;
import comp5216.sydney.edu.au.hyra.Presenter.RentItemPresenter;
import comp5216.sydney.edu.au.hyra.Service.RentItemService;
import comp5216.sydney.edu.au.hyra.View.RentItemView;

public class RentItemActivity extends FragmentActivity implements RentItemView {
    Spinner categorySpinner;
    ArrayAdapter<CharSequence> categoryAdapter;

    BooksFragment booksFragment;
    CalculatorsFragment calculatorsFragment;
    ArduinoKitFragment arduinoKitFragment;
    CablesFragment cablesFragment;
    LaptopChargerFragment laptopChargerFragment;
    String fragmentTag;
    Map<String,Object> input;
    EditText price;
    EditText description;
    EditText title;
    Database data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent_item);
        data = new Database(this);
        price = (EditText)findViewById(R.id.price);
        description = (EditText)findViewById(R.id.description);
        title = (EditText)findViewById(R.id.titleValue);
        input = new HashMap<>();
        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        categoryAdapter = categoryAdapter.createFromResource(this,R.array.categories,android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setPrompt("Category");
        setupListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rent_item, menu);
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


    //TODO really bad method... Try to find another way to render fragments dynamically
    private void setupListener()
    {
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                if (manager.getBackStackEntryCount() > 0) {
                    manager.popBackStackImmediate();

                }
                switch (categoryAdapter.getItem(position).toString()) {
                    case "books":
                        fragmentTag = "books";

                         booksFragment = new BooksFragment();
                        transaction.replace(R.id.dynamicFragment,booksFragment,"books");
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;
                    case "Calculators":
                        fragmentTag = "calculator";
                        calculatorsFragment = new CalculatorsFragment();
                        transaction.replace(R.id.dynamicFragment,calculatorsFragment,"calculator");
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;

                    case "Arduino":
                        fragmentTag = "arduino";
                       arduinoKitFragment = new ArduinoKitFragment();
                        transaction.replace(R.id.dynamicFragment, arduinoKitFragment,"arduino");
                        transaction.addToBackStack(null);
                        transaction.commit();
                        break;


                    default:
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void saveValues()
    {
        input.put("price",price.getText().toString());
        input.put("description",description.getText().toString());
        input.put("user",data.getEmail());
        input.put("Title",title.getText().toString());
        input.put("category", categorySpinner.getSelectedItem().toString());
        RentItemPresenter presenter = new RentItemPresenter(new RentItemService(data),this);
        presenter.addItem();


    }




    public void RentItem(View v)
    {
        FragmentManager manager = getFragmentManager();
        Log.i("MOCO", fragmentTag);
            switch (fragmentTag){


                case "books":
                    booksFragment = (BooksFragment)manager.findFragmentByTag(fragmentTag);
                    input.put("ISBN",booksFragment.getISBN());
                    input.put("Author",booksFragment.getAuthor());
                    input.put("Title",booksFragment.getBookTitle());
                    saveValues();

                    break;

                case "calculator":
                    calculatorsFragment =(CalculatorsFragment)manager.findFragmentByTag(fragmentTag);
                    input.put("brand",calculatorsFragment.getBrand());
                    saveValues();
                    break;

                case "arduino":
                    arduinoKitFragment =(ArduinoKitFragment)manager.findFragmentByTag(fragmentTag);
                    input.put("microcontroller",arduinoKitFragment.getMicrocontroller());
                    input.put("features",arduinoKitFragment.getFeatures());
                    input.put("clockspeed",arduinoKitFragment.getClockSpeed());
                    saveValues();
                    break;

                default:
                    break;
            }
    }

    @Override
    public void setDetails(Map<String, Object> itemDetails) {

    }

    @Override
    public Map<String, Object> getDetails() {
        return input;
    }

    @Override
    public void onComplete(boolean success) {
        if(success) {
            setResult(RESULT_OK);
            finish();
        }
        else {
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }
    }
}
