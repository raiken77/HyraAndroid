package comp5216.sydney.edu.au.hyra.Model;

/**
 * Created by apple on 10/17/15.
 */
public class roomMember {

    private String roomID;
    private String memberone;
    private String membertwo;


    public roomMember(String roomID, String memberOne, String memberTwo) {
        this.roomID = roomID;
        this.memberone = memberOne;
        this.membertwo = memberTwo;
    }

    public roomMember() {
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getMemberOne() {
        return memberone;
    }

    public void setMemberOne(String memberOne) {
        this.memberone = memberOne;
    }

    public String getMemberTwo() {
        return membertwo;
    }

    public void setMemberTwo(String memberTwo) {
        this.membertwo = memberTwo;
    }

    public String toString()
    {
        return memberone;
    }
}
