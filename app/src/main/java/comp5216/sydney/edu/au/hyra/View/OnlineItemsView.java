package comp5216.sydney.edu.au.hyra.View;

import java.util.Map;

/**
 * Created by apple on 10/15/15.
 */
public interface OnlineItemsView {

        void setViewWithData(Map<String,Object> items);

        void onComplete(boolean success, String roomID);


}
