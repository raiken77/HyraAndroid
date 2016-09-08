package comp5216.sydney.edu.au.hyra.View;

import java.util.Map;

/**
 * Created by apple on 10/17/15.
 */
public interface CheckRequestView {

    void setView(Map<String,Object> values);

    void onComplete(boolean success);
}
