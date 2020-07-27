package ir.adromsh.digikala.Detail;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ir.adromsh.digikala.R;

public class NewFolderDialog extends DialogFragment {
    View view;
    EditText edtFolder;
    TextView txtSubmit;
    OnSubmitClick onSubmitClick;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.new_folder_dialog, null);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        setupViews();
        builder.setView(view);
        return builder.create();
    }

    public void setOnSubmitClick(OnSubmitClick onSubmitClick) {

        this.onSubmitClick = onSubmitClick;
    }

    private void setupViews() {
        edtFolder = view.findViewById(R.id.edt_newFolderDialog);
        txtSubmit = view.findViewById(R.id.txt_newFolderDialog_submit);
        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmitClick.onClick(edtFolder.getText().toString());
            }
        });
    }

    public interface OnSubmitClick {
        void onClick(String folderName);
    }
}
