package comp5216.sydney.edu.au.hyra.CustomAdapter.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import comp5216.sydney.edu.au.hyra.CustomAdapter.AdapterModel.Item;
import comp5216.sydney.edu.au.hyra.CustomAdapter.AdapterView.ItemsView;

/**
 * Created by apple on 10/15/15.
 */
public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context context, List<Item> items) {
        super(context,0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemsView itemView = (ItemsView)convertView;
        if(itemView == null)
        {
            itemView = ItemsView.inflate(parent);
        }

        itemView.setItem(getItem(position));
        return itemView;
    }
}
