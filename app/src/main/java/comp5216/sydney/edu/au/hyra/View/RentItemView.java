package comp5216.sydney.edu.au.hyra.View;

import java.util.Map;

/**
 * Created by apple on 10/15/15.
 */
public interface RentItemView {

    //Set the details in the activity with the values from the map
    void setDetails(Map<String,Object> itemDetails);

    Map<String,Object> getDetails();

    void onComplete(boolean success);
}
