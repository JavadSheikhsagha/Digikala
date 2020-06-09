package jvd.ir.digiknew.Comments.AddComment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import jvd.ir.digiknew.R;

public class AddCommentActivity extends AppCompatActivity {

    ImageView imgBack;

    Button btnWrite,btnPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);

        setupViews();
    }

    private void setupViews() {

        imgBack=findViewById(R.id.img_addComment_back);
        btnWrite=findViewById(R.id.btn_addComment_writeComment);
        btnPoint=findViewById(R.id.btn_addComment_setPoint);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
