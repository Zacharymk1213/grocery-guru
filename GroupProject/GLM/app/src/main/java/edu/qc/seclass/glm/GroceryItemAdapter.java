package edu.qc.seclass.glm;

import android.view.KeyEvent;
import android.view.View.OnKeyListener;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.ImageButton;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;

import java.util.ArrayList;

public class GroceryItemAdapter extends ArrayAdapter {

    private GroceryList parentList;

    public GroceryItemAdapter(@NonNull Context context, ArrayList<GroceryItem> arrayList) {
        super(context, 0, arrayList);
    }
    
    public GroceryItemAdapter(@NonNull Context context, ArrayList<GroceryItem> arrayList,
        GroceryList pl) {
        super(context, 0, arrayList);
        parentList = pl;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View rowView = convertView;

        if (rowView == null)
            rowView = LayoutInflater.from(getContext()).inflate(
                R.layout.grocery_item_view, parent, false);
            
        GroceryItem thisItem = (GroceryItem)getItem(position);

        //initialize views
        TextView tvName = rowView.findViewById(R.id.tv_grocery_item_name);
        tvName.setText(thisItem.getName());

        TextView tvType = rowView.findViewById(R.id.tv_grocery_item_type);
        tvType.setText(thisItem.getType());

        //TextView tvId = rowView.findViewById(R.id.tv_grocery_item_id); //here for testing purposes
        //tvId.setText(""+thisItem.getId());

        EditText etQuantity = rowView.findViewById(R.id.et_grocery_item_quantity);
        etQuantity.setText(""+thisItem.getQuantity());
        if (parentList == null)
            etQuantity.setVisibility(View.INVISIBLE);
        etQuantity.setEnabled(parentList != null);

        CheckBox cbSelected = rowView.findViewById(R.id.grocery_item_checkbox);
        if (thisItem.isSelected())
            cbSelected.setChecked(true);
        else
            cbSelected.setChecked(false);

        ImageButton ibtnDelete = rowView.findViewById(R.id.ibtn_grocery_item_delete);
        if (parentList == null)
            ibtnDelete.setVisibility(View.INVISIBLE);
        ibtnDelete.setEnabled(parentList != null);

        //set listeners
        etQuantity.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)) {
                    etQuantity.requestFocus();
                    int quantity = Integer.parseInt(etQuantity.getText().toString().trim());
                    thisItem.setQuantity(quantity);
                    return true;
                } else
                    return false;
            }
        });

        cbSelected.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    thisItem.setSelected(true);
                else
                    thisItem.setSelected(false);
            }
        });

        if (ibtnDelete.isEnabled()) {
            Context context = getContext();
            ibtnDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    AlertDialog.Builder alertDelete = new AlertDialog.Builder(context);
                    alertDelete.setTitle("Delete Item");
                    alertDelete.setMessage("Are you sure you want to delete "
                        + thisItem.getName() + "?");
                    alertDelete.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            parentList.deleteItem(thisItem.getId());
                            remove(thisItem); //from this ArrayAdapter
                            //save file
                            User.getInstance().saveUserData();
                        }
                    });
                    alertDelete.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // close dialog
                            dialog.cancel();
                        }
                    });
                    alertDelete.show();
                }
            });
        }
        return rowView;
    }
    
}