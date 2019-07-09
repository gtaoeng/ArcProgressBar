package com.gtaoeng.arcprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gtaoeng.arclibrary.ArcProgress;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.AdapterViewHolder> {

    private Context mContext;
    private List<ItemBean> disData;

    public ItemAdapter(Context context, List<ItemBean> disData) {
        mContext = context;
        this.disData = disData;
    }


    @Override
    public ItemAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ItemAdapter.AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemAdapter.AdapterViewHolder holder, final int position) {

        final ItemBean obj = disData.get(position);
        holder.project_name.setText(obj.getName());

        holder.myProgress.setProgress((int) obj.getProgress());
        holder.myProgress.setMax(100);
        holder.myProgress.setOnCenterDraw(new ArcProgress.OnCenterDraw() {
            @Override
            public void draw(Canvas canvas, RectF rectF, float x, float y, float storkeWidth, int progress) {
                Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                int pix = 36;
                textPaint.setTextSize(pix);
                textPaint.setColor(mContext.getResources().getColor(R.color.textColor));
                String progressStr = String.valueOf(progress + "%");
                float textX = x - (textPaint.measureText(progressStr) / 2);
                float textY = y - ((textPaint.descent() + textPaint.ascent()) / 2);
                canvas.drawText(progressStr, textX, textY, textPaint);
            }
        });

    }

    private int getIdentifier(String idName) {
        int resId = mContext.getResources().getIdentifier(idName, "id", mContext.getPackageName());
        return resId;
    }

    @Override
    public int getItemCount() {
        return disData != null ? disData.size() : 0;
    }

    class AdapterViewHolder extends RecyclerView.ViewHolder {

        View rootView;
        TextView project_name;
        ArcProgress myProgress;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            project_name = rootView.findViewById(R.id.item_name);
            myProgress = rootView.findViewById(R.id.myProgress);
        }
    }
}
