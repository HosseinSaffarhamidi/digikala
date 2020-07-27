package ir.adromsh.digikala.Main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.R;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.CatsViewHolder> {
    List<Cat> cats;
    OnCatClickListener onCatClickListener;
    public CatsAdapter(List<Cat> cats,OnCatClickListener onCatClickListener){
        this.cats=cats;
        this.onCatClickListener=onCatClickListener;
    }
    @NonNull
    @Override
    public CatsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cat_item,viewGroup,false);
        return new CatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatsViewHolder catsViewHolder, final int i) {
        final Cat cat=cats.get(i);
        catsViewHolder.txtTitle.setText(cat.getTitle());
        catsViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCatClickListener.onClick(i,cat.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cats.size();
    }

    public class CatsViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        RelativeLayout parent;
        String id;
        public CatsViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_catItem_title);
            parent=itemView.findViewById(R.id.rel_catItem_parent);
        }
    }

    public interface OnCatClickListener{
        void onClick(int postion,String title);
    }
}
