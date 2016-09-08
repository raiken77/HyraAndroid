package comp5216.sydney.edu.au.hyra.Presenter;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.Service.RequestItemService;
import comp5216.sydney.edu.au.hyra.View.RequestItemView;

/**
 * Created by apple on 10/17/15.
 */
public class RequestItemPresenter {

    RequestItemView view;
    RequestItemService service;

    public RequestItemPresenter(RequestItemView view, RequestItemService service) {
        this.view = view;
        this.service = service;
    }


    public void sendRequest(Map<String,Object> values)
    {
        service.sendRequest(values,view);
    }

    public void getRequests(String userID)
    {
        service.getRequests(userID,view);
    }
}
