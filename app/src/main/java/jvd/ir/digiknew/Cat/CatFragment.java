package jvd.ir.digiknew.Cat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import jvd.ir.digiknew.R;

public class CatFragment extends Fragment {

    View view;

    public static CatFragment newInstance(String title) {

        Bundle args = new Bundle();
        args.putString("title",title);
        CatFragment fragment = new CatFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view==null){
            view=getLayoutInflater().inflate(R.layout.cat_fragment,container,false);
        }

        setupViews();
        return view;
    }

    private void setupViews() {

        String title = getArguments().getString("title");

        RecyclerView recyclerView=view.findViewById(R.id.rv_catFragment_catItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}
