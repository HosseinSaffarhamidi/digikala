package ir.adromsh.digikala.Filter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Model.FilterItem;
import ir.adromsh.digikala.Model.Value;
import ir.adromsh.digikala.R;

public class RightFilterItemAdapter extends RecyclerView.Adapter<RightFilterItemAdapter.RighViewHolder> {
    List<FilterItem> filterItems;
    OnFilterItemClick onFilterItemClick;
    List<Value> passedValue;
    Context context;
    int rowIndex;

    public RightFilterItemAdapter(Context context, List<FilterItem> filterItems, OnFilterItemClick onFilterItemClick) {
        this.filterItems = filterItems;
        this.context = context;
        this.onFilterItemClick = onFilterItemClick;
        passedValue = new ArrayList<>();
    }

    public void onBindCount(List<Value> values, String parent) {
        for (int i = 0; i < filterItems.size(); i++) {
            if (filterItems.get(i).getTitle().equals(parent)) {
                filterItems.get(i).setValues(values);
                passedValue = filterItems.get(i).getValues();
                for (int j = 0; j <passedValue.size() ; j++) {
                    Log.i("LOG", "onBindCount: "+passedValue.get(j).getTitle()+"//"+passedValue.get(j).isChecked());
                }
                notifyDataSetChanged();
            }
        }


    }

    @NonNull
    @Override
    public RighViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.right_item, viewGroup, false);
        return new RighViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RighViewHolder righViewHolder, final int i) {
        final FilterItem filterItem = filterItems.get(i);
        righViewHolder.txtTitle.setText(filterItem.getTitle());
        List<Value> values = filterItem.getValues();
        int count = 0;
        for (int j = 0; j < values.size(); j++) {
            if (values.get(j).isChecked()) {
                count++;
                righViewHolder.txtCount.setText(count + "");
            }
        }

        righViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rowIndex = i;
                List<Value> testValues = filterItems.get(i).getValues();
                String testTitle = filterItem.getTitle();
                onFilterItemClick.onItemClick(testValues, testTitle);
                notifyDataSetChanged();
            }
        });

//adapter color design
        if (rowIndex == i) {
            if (count > 0) {
                righViewHolder.txtCount.setVisibility(View.VISIBLE);
            } else {
                righViewHolder.txtCount.setVisibility(View.GONE);

            }
            righViewHolder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.colorWhite));
            righViewHolder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
            righViewHolder.txtCount.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_right_item_black));
            righViewHolder.txtCount.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        } else {
            if (count > 0) {
                righViewHolder.txtCount.setVisibility(View.VISIBLE);
            } else {
                righViewHolder.txtCount.setVisibility(View.GONE);

            }
            righViewHolder.parent.setBackgroundColor(ContextCompat.getColor(context, R.color.filterColor));
            righViewHolder.txtTitle.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
            righViewHolder.txtCount.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_right_item));
            righViewHolder.txtCount.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

        }
    }

    @Override
    public int getItemCount() {
        return filterItems.size();
    }

    public class RighViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout parent;
        TextView txtTitle, txtCount;

        public RighViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCount = itemView.findViewById(R.id.txt_rightItem_count);
            txtTitle = itemView.findViewById(R.id.txt_rightItem__title);
            parent = itemView.findViewById(R.id.rel_rightItem_parent);
        }

    }

    public interface OnFilterItemClick {
        void onItemClick(List<Value> values, String title);
    }
}
