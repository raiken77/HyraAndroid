package comp5216.sydney.edu.au.hyra.CustomAdapter.AdapterModel;

/**
 * Created by apple on 10/15/15.
 */
public class Item {

    private String text;

    public Item(String itemtext)
    {
        text =itemtext;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
