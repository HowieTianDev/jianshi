package com.howietian.jianshi.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.howietian.jianshi.R;
import com.howietian.jianshi.entities.Unit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by HowieTian on 2017/10/28 0028.
 */

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {
    private Context context;
    private List<Unit> unitList = new ArrayList<>();
    private onItemClickListener listener;

    public interface onItemClickListener {
        void onClick(int position);
    }

    public void setOnClickListener(onItemClickListener listener) {
        this.listener = listener;
    }

    public UnitAdapter(Context context, List<Unit> units) {
        this.context = context;
        this.unitList = units;
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UnitViewHolder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);
        holder = new UnitViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UnitViewHolder holder, final int position) {
        final Unit unit = unitList.get(position);
        holder.tvName.setText(unit.getName());
        holder.tvGroup.setText(unit.getGroup());
        holder.tvBrief.setText(unit.getBrief());
        holder.tvPName.setText(unit.getpName());
        holder.tvPosition.setText(unit.getPosition());
        holder.tvTitle.setText(unit.getTitle());
        holder.tvEducation.setText(unit.getEducation());
        holder.tvPhone.setText(unit.getPhoneNum());
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    static class UnitViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_group)
        TextView tvGroup;
        @Bind(R.id.tv_brief)
        TextView tvBrief;
        @Bind(R.id.tv_pName)
        TextView tvPName;
        @Bind(R.id.tv_position)
        TextView tvPosition;
        @Bind(R.id.tv_title)
        TextView tvTitle;
        @Bind(R.id.tv_education)
        TextView tvEducation;
        @Bind(R.id.tv_phone)
        TextView tvPhone;

        public UnitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
