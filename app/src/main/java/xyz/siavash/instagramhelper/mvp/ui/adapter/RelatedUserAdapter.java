package xyz.siavash.instagramhelper.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;
import java.util.zip.Inflater;

import xyz.siavash.instagramhelper.R;
import xyz.siavash.instagramhelper.mvp.uimodel.UserObject;

/**
 * Created by siavash on 6/22/16.
 */

public class RelatedUserAdapter extends RecyclerView.Adapter<RelatedUserAdapter.RelatedUserViewHolder> {

    private Context mContext;
    private List<UserObject> mUserObjectList;

    public RelatedUserAdapter(Context context, List<UserObject> userObjectList){
        mContext=context;
        mUserObjectList= Collections.emptyList();
        mUserObjectList.addAll(userObjectList);

    }

    @Override
    public RelatedUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_user_related,parent,false);
        return new RelatedUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RelatedUserViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mUserObjectList.size();
    }
    static class RelatedUserViewHolder extends RecyclerView.ViewHolder{

        public RelatedUserViewHolder(View itemView) {
            super(itemView);
        }
    }
}
