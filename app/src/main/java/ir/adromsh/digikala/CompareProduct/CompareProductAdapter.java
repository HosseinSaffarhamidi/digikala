package ir.adromsh.digikala.CompareProduct;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import ir.adromsh.digikala.R;

public class CompareProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TITLE = 1000;
    private static final int ITEM = 1003;
    Context context;
    List<Properties> originalProperties;

    public CompareProductAdapter(Context context,List<Properties> originalProperties) {
        this.context = context;
        this.originalProperties = originalProperties;
    }

    public void bindSecondProduct(List<Properties> secondProperties){
        originalProperties=secondProperties;
        notifyDataSetChanged();
    }



    @Override
    public int getItemViewType(int position) {
        if (originalProperties.get(position).getValue().equals("")) {
            return TITLE;
        } else {
            return ITEM;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        if (i == TITLE) {
            View view = layoutInflater.inflate(R.layout.properties_item_title, viewGroup, false);
            return new TitleViewHolder(view);
        } else {
            View view = layoutInflater.inflate(R.layout.compare_item, viewGroup, false);
            return new ItemViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Properties properties = originalProperties.get(i);
        if (getItemViewType(i) == TITLE) {
            ((TitleViewHolder) viewHolder).txtTitle.setText(properties.getTitle());
        } else {
            ((ItemViewHolder) viewHolder).txtOriginal.setText(properties.getValue());
            ((ItemViewHolder) viewHolder).txtOriginalTitle.setText(properties.getTitle());

            if(properties.getmSecond().equals("")){
                ((ItemViewHolder) viewHolder).txtSecond.setText("");
            }else{
                ((ItemViewHolder) viewHolder).txtSecond.setText(properties.getmSecond());
            }

        }
    }

    @Override
    public int getItemCount() {
        return originalProperties.size();
    }

    public class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_propertiesItemTitle_title);

        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView txtOriginal, txtSecond,txtOriginalTitle;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOriginal = itemView.findViewById(R.id.txt_compareItem_original);
            txtSecond = itemView.findViewById(R.id.txt_compareItem_second);
            txtOriginalTitle = itemView.findViewById(R.id.txt_compareItem_originalTitle);
        }
    }
}
