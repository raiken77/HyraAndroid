package comp5216.sydney.edu.au.hyra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.RegisterPresenter;
import comp5216.sydney.edu.au.hyra.Resources.ProgressResouce;
import comp5216.sydney.edu.au.hyra.Service.RegisterService;
import comp5216.sydney.edu.au.hyra.View.RegisterView;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    EditText email;
    EditText password;
    EditText firstname;
    EditText lastname;
    ProgressResouce progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        progressBar = new ProgressResouce(this);
        email = (EditText)findViewById(R.id.email);
        password =(EditText)findViewById(R.id.password);
        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    //Delegates the registration to the presenter
    public void register(View v)
    {
        Database database = new Database(this);
        RegisterPresenter registerPresenter = new RegisterPresenter(new RegisterService(database),this);
        registerPresenter.registerUser();
    }

    @Override
    public Map<String, String> getUserInput() {
        Map<String,String> userInput = new HashMap<>();
        userInput.put("email",email.getText().toString());
        userInput.put("password",password.getText().toString());
        userInput.put("firstname",firstname.getText().toString());
        userInput.put("lastname",lastname.getText().toString());

        return userInput;


    }

    @Override
    public void showEmailError(int resId) {

        email.setError(getString(resId));
    }

    @Override
    public void showPasswordError(int resId) {

    }

    @Override
    public void showFirstnameError(int resId) {

    }

    @Override
    public void showSurnameError(int resId) {

    }

    @Override
    public void onComplete(boolean success) {


        if(success){
            Toast.makeText(this,"Successfully registered",Toast.LENGTH_LONG).show();
            Intent finishedProcessing = new Intent();
            finishedProcessing.putExtra("Success","Done");
            setResult(RESULT_OK);
            finish();

        }
        else
        {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void showProgress() {
        progressBar.show("Please wait...","Registering");

    }

    @Override
    public void updateProgress() {

    }
}
