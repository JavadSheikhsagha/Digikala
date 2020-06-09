package jvd.ir.digiknew.Detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import jvd.ir.digiknew.R;

public class FavoriteBottomSheet extends BottomSheetDialogFragment {

    View view;

    RelativeLayout parentNewFav, parentAddToFav;

    TextView txtCount;

    OnAddButtonClick onAddButtonClick;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.bottom_sheet_favorite, null);
        }
        setupViews();
        return view;
    }

    public void setOnAddButtonClick(OnAddButtonClick onAddButtonClick) {
        this.onAddButtonClick = onAddButtonClick;

    }

    private void setupViews() {

        parentNewFav = view.findViewById(R.id.relllllllllllllllll);
        parentAddToFav = view.findViewById(R.id.rel_bottomSheet_favorite);

        txtCount= view.findViewById(R.id.txt_bottomSheet_Count);

        parentNewFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddButtonClick.onClick();
            }
        });

    }

    public interface OnAddButtonClick{
        void onClick();
    }
}
