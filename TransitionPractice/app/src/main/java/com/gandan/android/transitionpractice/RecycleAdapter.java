package com.gandan.android.transitionpractice;

import android.animation.TimeInterpolator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.transition.ChangeBounds;
import android.support.transition.ChangeImageTransform;
import android.support.transition.Transition;
import android.support.transition.TransitionSet;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
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
        //아래와 같이 setBackgroundColor로 컬러를 바꾸면 cornerRadius와 elevation 설정 등을 초기화 시켜버린다.
        holder.cardViewItem.setBackgroundColor(Color.WHITE);
        //따라서 아래와 같이 setCardBackgroundColor로 카드뷰의 배경색을 바꿔야 한다.
        holder.cardViewItem.setCardBackgroundColor(Color.WHITE);
        holder.itemView.startAnimation(animation);
        int pos = position+1;
        holder.txtOne.setText(context.getString(R.string.title, pos));
        holder.txtTwo.setText(context.getString(R.string.contents, pos));
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
        CardView cardViewItem;

        public Holder(final View itemView) {
            super(itemView);

            constraintItem = itemView.findViewById(R.id.constraintItem);

            txtOne = itemView.findViewById(R.id.txtOne);
            txtTwo = itemView.findViewById(R.id.txtTwo);
            imgItem = itemView.findViewById(R.id.imgItem);

            cardViewItem = itemView.findViewById(R.id.cardview_item);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("images", "https://cdn.pixabay.com/photo/2018/05/30/15/31/rustic-3441673_1280.jpg");
                    intent.putExtra("firstTextView", txtOne.getText().toString());
                    intent.putExtra("secondTextView", txtTwo.getText().toString());
                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imgItem,"image");
                    android.transition.Transition transition = new android.transition.TransitionSet();
                    transition.setInterpolator(new TimeInterpolator() {
                        @Override
                        public float getInterpolation(float v) {
                            return 3600f;
                        }
                    });
                    transition.setDuration(6000);
                    transition.addTarget(((Activity) context).getWindow().getDecorView());
                    ((Activity) context).getWindow().setSharedElementExitTransition(transition);
                    context.startActivity(intent, optionsCompat.toBundle());
                }
            });
        }
    }
}
