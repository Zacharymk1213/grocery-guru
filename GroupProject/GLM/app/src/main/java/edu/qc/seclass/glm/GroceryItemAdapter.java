package edu.qc.seclass.glm;

import android.content.Context;
import androidx.annotation.NonNull; 
import androidx.annotation.Nullable; 
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CheckBox;

import java.util.ArrayList;

public class GroceryItemAdapter extends ArrayAdapter {
    
    public GroceryItemAdapter(@NonNull Context context, ArrayList<GroceryItem> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(
                R.layout.list_item_layout, parent, false);
            
        GroceryItem thisItem = (GroceryItem)getItem(position);

        TextView tvName = rowView.findViewById(R.id.tv_grocery_item_name);
        tvName.setText(thisItem.getName());
        TextView tvType = rowView.findViewById(R.id.tv_grocery_item_type);
        tvType.setText(thisItem.getType());
        TextView tvId = rowView.findViewById(R.id.tv_grocery_item_id); //here for testing purposes
        tvId.setText(""+thisItem.getId());
        CheckBox cbSelected = rowView.findViewById(R.id.grocery_item_checkbox);
        if (thisItem.isSelected())
            cbSelected.setChecked(true);
        else
            cbSelected.setChecked(false);

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thisItem.setSelected(true);
                else
                    thisItem.setSelected(false);
            }
        });

        return rowView;
    }
    
}