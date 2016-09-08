package comp5216.sydney.edu.au.hyra.View;

import java.util.Map;

/**
 * Created by apple on 10/17/15.
 */
public interface RequestItemView {

    void onComplete(boolean success);

    void setLayout(Map<String,Object> values);

    void deleteItem(String requestID);
}
