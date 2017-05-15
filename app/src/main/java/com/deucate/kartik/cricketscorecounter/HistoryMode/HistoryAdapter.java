package com.deucate.kartik.cricketscorecounter.HistoryMode;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deucate.kartik.cricketscorecounter.R;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private List<HistoryGs> mList;

    public HistoryAdapter(List<HistoryGs> list) {
        mList = list;
    }

//    public HistoryAdapter() {    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.run_card_view,parent,false);
        return new HistoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {

        HistoryGs historyGs = mList.get(position);
        holder.mMoveTV.setText(historyGs.getMove()+"");
        holder.mBallTV.setText(historyGs.getBall()+"");

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder{
        TextView mBallTV,mMoveTV;

        HistoryViewHolder(View itemView) {
            super(itemView);

            mBallTV = (TextView) itemView.findViewById(R.id.cardBall);
            mMoveTV = (TextView) itemView.findViewById(R.id.cardMove);

        }
    }

}
