package jvd.ir.digiknew.Comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import jvd.ir.digiknew.Model.Comments;
import jvd.ir.digiknew.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    List<Comments> comments;
    Context context;

    OnLikeClick onLikeClick;

    OnDisLikeClick onDisLikeClick;


    String id;

    public CommentAdapter (Context context,List<Comments> comments,OnDisLikeClick onDisLikeClick,OnLikeClick onLikeClick){
        this.comments=comments;
        this.context=context;
        this.onDisLikeClick=onDisLikeClick;
        this.onLikeClick=onLikeClick;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.comment_item,parent,false);
        return new CommentViewHolder(view);
    }


    public void changeLikeListener(Comments commentss){

        int position = comments.indexOf(commentss);
        Comments findComment = comments.get(position);

        int currentLike = Integer.parseInt(findComment.getLikes());

        currentLike++;

        findComment.setLikes(currentLike+"");

        notifyDataSetChanged();

    }


    public void changeDisLikeListener(Comments commentss){
        int position=comments.indexOf(commentss);

        Comments findComment=comments.get(position);
        int currentDisLike=Integer.parseInt(findComment.getDislikes());
        currentDisLike++;
        findComment.setDislikes(currentDisLike+"");

        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        final Comments comment=comments.get(position);
        id=comment.getId();
        holder.txtUserName.setText(comment.getUser());
        holder.txtLike.setText(comment.getLikes());
        holder.txtDislike.setText(comment.getDislikes());
        holder.txtComment.setText(comment.getMatn());
        String suggestion=comment.getLikes();

        if (suggestion.equals("3")){
            holder.txtSuggestion.setVisibility(View.VISIBLE);
        } else{
            holder.txtSuggestion.setVisibility(View.GONE);
        }

        holder.relativeLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLikeClick.onClick(comment);
            }
        });

        holder.relativeDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDisLikeClick.onClick(comment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{

        TextView txtUserName,txtComment,txtLike,txtDislike,txtSuggestion;
        RelativeLayout relativeLike,relativeDislike;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            txtComment=itemView.findViewById(R.id.txt_commentItem_comment);
            txtLike=itemView.findViewById(R.id.txt_commentItem_like);
            txtDislike=itemView.findViewById(R.id.txt_commentItem_dislike);
            txtUserName=itemView.findViewById(R.id.txt_commentItem_username);
            txtSuggestion=itemView.findViewById(R.id.txt_commentItem_suggestion);
            relativeDislike=itemView.findViewById(R.id.relative_commentItem_disLike);
            relativeLike=itemView.findViewById(R.id.relative_commentItem_like);
        }
    }

    public interface OnLikeClick{
        void onClick(Comments comments);
    }

    public interface OnDisLikeClick{
        void onClick(Comments comments);
    }

}
