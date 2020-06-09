package jvd.ir.digiknew.Detail;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import jvd.ir.digiknew.R;

public class NewFavDialog extends DialogFragment {

    View view;

    EditText edtFavTitle;

    TextView submit;

    onAddButtonClick onAddButtonClick;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (view==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.fav_dialog,null);
        }

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        setupViews();
        builder.setView(view);

        return builder.create();
    }

    public void setOnAddButtonClick(NewFavDialog.onAddButtonClick onAddButtonClick) {

        this.onAddButtonClick=onAddButtonClick;

    }

    private void setupViews() {
        edtFavTitle = view.findViewById(R.id.edt_favDialog_namee);
        submit = view.findViewById(R.id.txt_favDialog_submite);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick.onClick(edtFavTitle.getText().toString());
            }
        });

    }

    public interface onAddButtonClick{
        void onClick(String folderName);
    }
}
