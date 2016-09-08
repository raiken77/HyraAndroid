package comp5216.sydney.edu.au.hyra.Presenter;

import comp5216.sydney.edu.au.hyra.Service.OnlineItemsService;
import comp5216.sydney.edu.au.hyra.View.OnlineItemsView;

/**
 * Created by apple on 10/15/15.
 */
public class OnlineItemsPresenter {

    private OnlineItemsView onlineItemsView;
    private OnlineItemsService onlineItemsService;

    public OnlineItemsPresenter(OnlineItemsService service,OnlineItemsView view) {
        this.onlineItemsService = service;
        this.onlineItemsView = view;
    }


    public void getRentItems(String category)
    {
        this.onlineItemsService.getItems(category,onlineItemsView);
    }

    public void getSingleItem(String item,String category)
    {
        onlineItemsService.getSingleItem(item,category,onlineItemsView);
    }

    public void createChatRoom(String currentUser, String otherUser)
    {
        onlineItemsService.createChat(currentUser,otherUser,onlineItemsView);
    }
}
