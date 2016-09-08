package comp5216.sydney.edu.au.hyra.Presenter;

import comp5216.sydney.edu.au.hyra.Service.CheckRequestService;
import comp5216.sydney.edu.au.hyra.View.CheckRequestView;

/**
 * Created by apple on 10/17/15.
 */
public class CheckRequestPresenter {

    CheckRequestService service;
    CheckRequestView view;

    public CheckRequestPresenter(CheckRequestService service, CheckRequestView view) {
        this.service = service;
        this.view = view;
    }

    public void getSingleRequest(String requestID)
    {
        service.getSingleRequest(requestID,view);
    }

    public void updateRentItem(String operationID,String category,String requestID)
    {
        service.updateRentItems(operationID,category,view,requestID);
    }



}
