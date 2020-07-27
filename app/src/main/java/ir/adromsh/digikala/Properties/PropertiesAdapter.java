package ir.adromsh.digikala.Properties;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import ir.adromsh.digikala.R;

public class PropertiesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TITLE = 1;
    private static final int PROPERTIESITEM = 3;
    List<Properties> propertiesList;
    Context context;

    public PropertiesAdapter(Context context, List<Properties> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == TITLE) {
            View view = layoutInflater.inflate(R.layout.properties_item_title, viewGroup, false);
            return new TitleViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.properties_item_param, viewGroup, false);
            return new PropertiesViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Properties properties=propertiesList.get(i);
        if(getItemViewType(i)==TITLE){
            ((TitleViewHolder) viewHolder).txtTitle.setText(properties.getTitle());
        }else{
            ((PropertiesViewHolder) viewHolder).txtKey.setText(properties.getTitle());
            if(properties.getValue().equals("دارد")){
                ((PropertiesViewHolder) viewHolder).imgHaveOrNot.setVisibility(View.VISIBLE);
                ((PropertiesViewHolder) viewHolder).txtValue.setVisibility(View.GONE);
                ((PropertiesViewHolder) viewHolder).imgHaveOrNot.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_done_green_24dp));
            }else if(properties.getValue().equals("ندارد")){
                ((PropertiesViewHolder) viewHolder).imgHaveOrNot.setVisibility(View.VISIBLE);
                ((PropertiesViewHolder) viewHolder).txtValue.setVisibility(View.GONE);
                ((PropertiesViewHolder) viewHolder).imgHaveOrNot.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_close_red_24dp));
            }else{
                ((PropertiesViewHolder) viewHolder).txtValue.setVisibility(View.VISIBLE);
                ((PropertiesViewHolder) viewHolder).imgHaveOrNot.setVisibility(View.GONE);
                ((PropertiesViewHolder) viewHolder).txtValue.setText(properties.getValue());
            }

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (propertiesList.get(position).getValue().equals("")) {
            return TITLE;
        } else {
            return PROPERTIESITEM;
        }
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }


    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_propertiesItemTitle_title);

        }
    }

    public class PropertiesViewHolder extends RecyclerView.ViewHolder {
        TextView txtKey,txtValue;
        ImageView imgHaveOrNot;
        public PropertiesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtKey=itemView.findViewById(R.id.txt_propertiesItemParam_key);
            txtValue=itemView.findViewById(R.id.txt_propertiesItemParam_value);
            imgHaveOrNot=itemView.findViewById(R.id.img_propertiesItem_haveOrNot);
        }
    }


}
