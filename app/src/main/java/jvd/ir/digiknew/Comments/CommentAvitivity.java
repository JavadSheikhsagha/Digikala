package jvd.ir.digiknew.Comments;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Comments.AddComment.AddCommentActivity;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Comments;
import jvd.ir.digiknew.R;

public class CommentAvitivity extends AppCompatActivity {

    CommentViewModel commentViewModel=new CommentViewModel();

    String id,title;

    TextView txtPdTitle;

    RecyclerView rvComments;

    ImageView imgClose;

    CommentAdapter commentAdapter;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    FrameLayout ProgressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        setupViews();

        getComments(id);

    }



    private void setupViews() {


        ProgressFrame=findViewById(R.id.comment_frame);
        imgClose=findViewById(R.id.img_comment_close);
        txtPdTitle=findViewById(R.id.txt_comment_title);
        rvComments=findViewById(R.id.rv_comment_comments);

        rvComments.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CommentAvitivity.this, AddCommentActivity.class);
                startActivity(intent);
            }
        });

        id=getIntent().getExtras().getString("id");
        title=getIntent().getExtras().getString("name");

        txtPdTitle.setText(title);

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }


    private void getComments(final String id) {
        commentViewModel.getComments(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Comments>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final List<Comments> comments) {
                        ProgressFrame.setVisibility(View.GONE);
                        commentAdapter=new CommentAdapter(CommentAvitivity.this, comments, new CommentAdapter.OnDisLikeClick() {
                            @Override
                            public void onClick(final Comments comments1) {
                                commentViewModel.getDislikeComments(comments1).subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Message>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(Message message) {
                                                if (message.getStatus().equals("error")){
                                                    Toast.makeText(CommentAvitivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    commentAdapter.changeDisLikeListener(comments1);
                                                    Toast.makeText(CommentAvitivity.this, "SuccessA", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.e("LOG12", "onError: "+e.toString());
                                            }
                                        });
                            }
                        }, new CommentAdapter.OnLikeClick() {
                            @Override
                            public void onClick(final Comments comments1) {
                                commentViewModel.getLikeComment(comments1).subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Message>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {

                                            }

                                            @Override
                                            public void onSuccess(Message message) {
                                                if (message.getStatus().equals("error")){
                                                    Toast.makeText(CommentAvitivity.this, "Error", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    commentAdapter.changeLikeListener(comments1);
                                                    Toast.makeText(CommentAvitivity.this, "SuccessB", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.e("LOG12", "onError: "+e.toString());
                                            }
                                        });
                            }
                        });

                        rvComments.setAdapter(commentAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG12", "onError: "+e.toString());
                    }
                });
    }



    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
