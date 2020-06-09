package jvd.ir.digiknew.Filter;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import jvd.ir.digiknew.R;

public class FilterDialog extends DialogFragment {

    View view;

    LinearLayout mostView,cheap,expensive,newest;

    RadioButton mostViewRB,cheapRb,expensiveRb,newestRb;

    private static final int MOST_VIEW = 1;
    private static final int MOST_NEW = 2;
    private static final int MOST_CHEAP = 3;
    private static final int MOST_EXPENSIVE = 4;

    public static final int SITUATION = 1;

    OnDialogItemClick onDialogItemClick;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        view=LayoutInflater.from(getContext()).inflate(R.layout.filter_dialog,null);
        setupViews();
        builder.setView(view);
        return builder.create();
    }

    public void setOnDialogItemClick(OnDialogItemClick onDialogItemClick) {
        this.onDialogItemClick = onDialogItemClick;
    }

    private void setupViews() {

        mostView = view.findViewById(R.id.linear_filterDialog_mostView);
        newest = view.findViewById(R.id.linear_filterDialog_new);
        cheap = view.findViewById(R.id.linear_filterDialog_cheap);
        expensive = view.findViewById(R.id.linear_filterDialog_expensive);

        mostViewRB = view.findViewById(R.id.rb_filterDialog_mostViews);
        cheapRb = view.findViewById(R.id.rb_filterDialog_cheap);
        expensiveRb = view.findViewById(R.id.rb_filterDialog_expensive);
        newestRb = view.findViewById(R.id.rb_filterDialog_newest);

        mostView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_VIEW);
            }
        });

        newest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_NEW);
            }
        });

        cheap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_CHEAP);
            }
        });

        expensive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDialogItemClick.onClick(MOST_EXPENSIVE);
            }
        });
    }

    public interface OnDialogItemClick{
        void onClick(int sort);
    }
}
