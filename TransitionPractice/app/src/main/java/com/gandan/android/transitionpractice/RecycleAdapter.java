package com.gandan.android.transitionpractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeImageTransform;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder> {

    Context context;

    public RecycleAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.up_from_bottom);
        holder.itemView.startAnimation(animation);
        holder.txtOne.setText(position+1+"번째 제목");
        holder.txtTwo.setText(position+1+"번째 내용");
        Glide.with(context).load("https://cdn.pixabay.com/photo/2018/05/30/15/31/rustic-3441673_1280.jpg").apply(RequestOptions.centerCropTransform()).into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView txtOne, txtTwo;
        ConstraintLayout constraintItem;
        ImageView imgItem;

        public Holder(final View itemView) {
            super(itemView);

            constraintItem = itemView.findViewById(R.id.constraintItem);

            txtOne = itemView.findViewById(R.id.txtOne);
            txtTwo = itemView.findViewById(R.id.txtTwo);
            imgItem = itemView.findViewById(R.id.imgItem);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("images", "https://cdn.pixabay.com/photo/2018/05/30/15/31/rustic-3441673_1280.jpg");
                    intent.putExtra("firstTextView", txtOne.getText().toString());
                    intent.putExtra("secondTextView", txtTwo.getText().toString());
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imgItem,"image");
                    android.transition.Transition transition = new android.transition.TransitionSet();
                    transition.setDuration(600000);
                    ((Activity) context).getWindow().setSharedElementExitTransition(transition);
                    context.startActivity(intent, optionsCompat.toBundle());
                }
            });
        }
    }
}
