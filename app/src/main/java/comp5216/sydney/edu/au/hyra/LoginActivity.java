package comp5216.sydney.edu.au.hyra;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import comp5216.sydney.edu.au.hyra.DatabaseResource.Database;
import comp5216.sydney.edu.au.hyra.Presenter.LoginPresenter;
import comp5216.sydney.edu.au.hyra.Resources.ProgressResouce;
import comp5216.sydney.edu.au.hyra.Service.LoginService;
import comp5216.sydney.edu.au.hyra.View.LoginView;


public class LoginActivity extends AppCompatActivity implements LoginView {


    Button loginButton;
    Button registerButton;
    EditText emailText;
    EditText passwordText;
    ProgressResouce progressBar;

    public final static int REGISTER_CODE = 900;


    public LoginActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlogin);
        progressBar = new ProgressResouce(this);

        loginButton= (Button)findViewById(R.id.loginBut);
        registerButton = (Button)findViewById(R.id.registerBut);
        emailText =(EditText)findViewById(R.id.emailText);
        passwordText =(EditText)findViewById(R.id.passwordText);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    //Let's the presenter handle all the auth logic
    public void authenticate(View v) {

        Database data = new Database(this);
        LoginPresenter presenter = new LoginPresenter(new LoginService(data),this);
        presenter.authenticateUser();

    }

    //Gets the user to the register page
    public void registerUser(View v)
    {
        Intent registerIntent = new Intent(this,RegisterActivity.class);
        startActivityForResult(registerIntent, REGISTER_CODE);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REGISTER_CODE)

        {

            if(resultCode == RESULT_OK)
            {

                Toast.makeText(this,"Your account has been created successfully",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public String getEmail() {
        return emailText.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordText.getText().toString();
    }

    @Override
    public void showProgress() {
    progressBar.show("Please wait...", "Logging in");

    }

    @Override
    public void updateProgress() {

    }

    //TODO remove the vulgar toast afterwards
    @Override
    public void onComplete(boolean success) {

        progressBar.dismiss();
        if(success)
        {


            Intent mainMenu = new Intent(this,MenuActivity.class);
            startActivity(mainMenu);
        }

        else
        {
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed() {

    }
}
