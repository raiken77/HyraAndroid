package comp5216.sydney.edu.au.hyra.Presenter;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.R;
import comp5216.sydney.edu.au.hyra.Service.RegisterService;
import comp5216.sydney.edu.au.hyra.View.RegisterView;

/**
 * Created by apple on 10/15/15.
 */
public class RegisterPresenter {

    RegisterService registerService;
    RegisterView registerView;

    private static final String delimiter="sydney.edu.au";

    public RegisterPresenter(RegisterService service, RegisterView view)
    {
        registerService = service;
        registerView = view;

    }

    public void registerUser()
    {
        Map<String,String> userInput  =  registerView.getUserInput();
        if(isValid(userInput)) {
            registerService.addUser(userInput, registerView);
        }
    }

    //TODO do a bunch of checks on user input
    private boolean isValid(Map<String,String> input)
    {


        if(!checkEmail(input.get("email")))
        {
            registerView.showEmailError(R.string.email_error);
            return false;
        }




        return true;
    }



    private boolean checkEmail(String email)
    {
        String [] separateEmail = email.split("@");

        if(separateEmail.length >  0) {
            String trimmedDelimiter = separateEmail[1].substring(separateEmail[1].indexOf(".") + 1);

            if(trimmedDelimiter.equalsIgnoreCase(delimiter))
            {
                return true;
            }
            else
            {
                return false;
            }
        }


        return false;
    }
}
