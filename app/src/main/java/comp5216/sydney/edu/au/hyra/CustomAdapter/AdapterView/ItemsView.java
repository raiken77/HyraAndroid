package comp5216.sydney.edu.au.hyra.CustomAdapter.AdapterView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import comp5216.sydney.edu.au.hyra.CustomAdapter.AdapterModel.Item;
import comp5216.sydney.edu.au.hyra.R;

/**
 * Created by apple on 10/15/15.
 */
public class ItemsView extends RelativeLayout {
    private TextView mItemText;
    private ImageView mImageView;

    public ItemsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.itemadapter_customview, this, true);
        setupViewItems();
    }

    public static ItemsView inflate(ViewGroup parent)
    {

        ItemsView itemView = (ItemsView) LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.custom_itemview,parent,false);
        return itemView;
    }

    private void setupViewItems() {
        mItemText = (TextView) findViewById(R.id.itemText);
//        mImageView = (ImageView) findViewById(R.id.arrowImage);
    }

    public void setItem(Item item) {
        mItemText.setText(item.getText());
//        mImageView.setImageResource(R.drawable.arrow);
    }
}
