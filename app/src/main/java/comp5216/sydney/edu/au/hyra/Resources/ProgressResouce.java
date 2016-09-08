package comp5216.sydney.edu.au.hyra.Resources;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by apple on 10/18/15.
 */
public class ProgressResouce {

    ProgressDialog dialog;
    Context context;

    public ProgressResouce(Context context) {
        this.context = context;

    }


    public void show(String message,String title)
    {
        dialog = ProgressDialog.show(context,title,message);
        dialog.setCancelable(false);

    }
    public void dismiss()
    {
        dialog.dismiss();
    }
}
