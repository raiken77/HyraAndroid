package comp5216.sydney.edu.au.hyra.Presenter;

import comp5216.sydney.edu.au.hyra.Service.ChatRoomService;
import comp5216.sydney.edu.au.hyra.View.ChatRoomView;

/**
 * Created by apple on 10/17/15.
 */
public class ChatRoomPresenter {


    ChatRoomService service;
    ChatRoomView view;

    public ChatRoomPresenter(ChatRoomService service, ChatRoomView view) {
        this.service = service;
        this.view = view;
    }

    public void retrieveChatRooms(String userID)
    {
            service.getRooms(userID,view);
    }
}
