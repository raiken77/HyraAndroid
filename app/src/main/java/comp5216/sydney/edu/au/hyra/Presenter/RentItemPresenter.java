package comp5216.sydney.edu.au.hyra.Presenter;

import java.util.Map;

import comp5216.sydney.edu.au.hyra.Service.RentItemService;
import comp5216.sydney.edu.au.hyra.View.RentItemView;

/**
 * Created by apple on 10/15/15.
 */
//TODO Check the logic when the user enters his details to rent an item

    //This class is mainly for the users who are renting their items after filling  a form, this class will process the info
public class RentItemPresenter {

    private RentItemService rentService;
    private RentItemView rentView;

    public  RentItemPresenter(RentItemService service,RentItemView view)
    {
        this.rentService = service;
        this.rentView = view;

    }

    public void addItem()
    {
        Map<String,Object> itemDetails = rentView.getDetails();
        rentService.addItem(itemDetails,rentView);

    }

    private void checkiInfo(Map<String,Object> input)
    {

    }


}
