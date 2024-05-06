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

public class GroceryListAdapter extends ArrayAdapter {
    
    public GroceryListAdapter(@NonNull Context context, ArrayList<GroceryList> arrayList) {
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(
                R.layout.grocery_list_view, parent, false);
            
        GroceryList thisList = (GroceryList)getItem(position);

        TextView tvName = rowView.findViewById(R.id.tv_grocery_list_name);
        tvName.setText(thisList.getName());
        //TextView tvId = rowView.findViewById(R.id.tv_grocery_list_id); //here for testing purposes
        //tvId.setText(""+thisList.getId());
        CheckBox cbSelected = rowView.findViewById(R.id.btn_grocery_list_selectable);
        if (thisList.isSelected())
            cbSelected.setChecked(true);
        else
            cbSelected.setChecked(false);

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thisList.setSelected(true);
                else
                    thisList.setSelected(false);
            }
        });

        return rowView;
    }
}
