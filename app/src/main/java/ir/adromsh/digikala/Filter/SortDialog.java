package ir.adromsh.digikala.Filter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import ir.adromsh.digikala.R;

public class SortDialog extends DialogFragment {
     private static final int MOST_VIEW = 1;
     private static final int MOST_SELL = 2;
     private static final int PRICE_DESC = 3;
     private static final int PRICE_ASC = 4;
     private static final int NEWEST = 5;

     public static int situation = 1;
     OnDialogItemClick onDialogItemClick;


    View view;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        if(view==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.filter_dialog,null);
        }
        setupVeiws();
        builder.setView(view);
        return builder.create();
    }

    public void setOnDialogItemClick(OnDialogItemClick onDialogItemClick) {
        this.onDialogItemClick = onDialogItemClick;
    }

    private void setupVeiws() {
        LinearLayout mostView=view.findViewById(R.id.linear_filterDialog_mostView);
        LinearLayout mostSell=view.findViewById(R.id.linear_filterDialog_mostSell);
        LinearLayout priceDesc=view.findViewById(R.id.linear_filterDialog_priceDesc);
        LinearLayout priceAsc=view.findViewById(R.id.linear_filterDialog_priceAsc);
        final LinearLayout newest=view.findViewById(R.id.linear_filterDialog_newest);
        
        RadioButton radioMostView=view.findViewById(R.id.radio_filterDialog_mostView);
        RadioButton radioMostSell=view.findViewById(R.id.radio_filterDialog_mostSell);
        RadioButton radioPriceDesc=view.findViewById(R.id.radio_filterDialog_priceDesc);
        RadioButton radioPriceAsc=view.findViewById(R.id.radio_filterDialog_priceAsc);
        RadioButton radioNewest=view.findViewById(R.id.radio_filterDialog_newest);


        switch (situation){
            case MOST_VIEW:
                radioMostView.setChecked(true);
                break;
            case MOST_SELL:
                radioMostSell.setChecked(true);
                break;
            case PRICE_DESC:
                radioPriceDesc.setChecked(true);
                break;
            case PRICE_ASC:
                radioPriceAsc.setChecked(true);
                break;
            case NEWEST:
                radioNewest.setChecked(true);
                break;
        }

        mostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_VIEW);
                situation=MOST_VIEW;
                dismiss();
                situation =MOST_VIEW;
            }
        });
        mostSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_SELL);
                situation=MOST_SELL;
                dismiss();
                situation =MOST_SELL;
            }
        });
        priceDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(PRICE_DESC);
                situation=PRICE_DESC;
                dismiss();
                situation =PRICE_DESC;
            }
        });
        priceAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(PRICE_ASC);
                situation=PRICE_ASC;
                dismiss();
                situation =PRICE_ASC;
            }
        });
        newest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(NEWEST);
                situation=NEWEST;
                dismiss();
                situation =NEWEST;
            }
        });


        radioMostView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SortDialog.this.dismiss();
                situation=MOST_VIEW;
            }
        });
        radioMostSell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SortDialog.this.dismiss();
                situation=MOST_SELL;
            }
        });
        radioPriceDesc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SortDialog.this.dismiss();
                situation=PRICE_DESC;
            }
        });
        radioPriceAsc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SortDialog.this.dismiss();
                situation=PRICE_ASC;
            }
        });
        radioNewest.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SortDialog.this.dismiss();
                situation=NEWEST;
            }
        });
        
        
    }

    public interface OnDialogItemClick{
        void onClick(int sort);
    }
}
