package xyz.siavash.instagramhelper.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import xyz.siavash.instagramhelper.R;
import xyz.siavash.instagramhelper.mvp.ui.components.CircleTransform;
import xyz.siavash.instagramhelper.mvp.uimodel.UserObject;

/**
 * Created by siavash on 6/22/16.
 */

public class RelatedUserAdapter extends RecyclerView.Adapter<RelatedUserAdapter.RelatedUserViewHolder> {

    private Context mContext;
    private List<UserObject> mUserObjectList;

    public RelatedUserAdapter(Context context){
        mContext=context;
        mUserObjectList= new ArrayList<UserObject>();

    }

    @Override
    public RelatedUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_user_related,parent,false);
        return new RelatedUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelatedUserViewHolder holder, int position) {

        holder.textUserName.setText(mUserObjectList.get(position).userName);
        holder.textRelation.setText(mUserObjectList.get(position).followingState);
        Picasso.with(this.mContext).load(mUserObjectList.get(position).imageAddress)
                .transform(new CircleTransform())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUserObjectList.size();
    }
    static class RelatedUserViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.row_user_image)
        ImageView imageView;

        @Bind(R.id.row_user_name)
        TextView textUserName;

        @Bind(R.id.row_user_relation)
        TextView textRelation;

        public RelatedUserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<UserObject> userObjects){
        mUserObjectList.clear();
        mUserObjectList.addAll(userObjects);
        notifyDataSetChanged();
    }
}
