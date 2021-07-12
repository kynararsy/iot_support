package com.example.iot_asma_support;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<History> listHistory;
    LayoutInflater inflater;

    public Adapter(Context context, List<History> listHistory){
        this.inflater = LayoutInflater.from(context);
        this.listHistory = listHistory;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView date;
        TextView time;
        TextView detak;
        TextView kbb;
        TextView debu;
        ImageView dateImage;
        ImageView timeImage;
        ImageView detakImage;
        ImageView kbbImage;
        ImageView debuImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.txtCalendar);
            time = itemView.findViewById(R.id.txtTime);
            detak = itemView.findViewById(R.id.txtDetak);
            kbb = itemView.findViewById(R.id.txtKelembaban);
            debu = itemView.findViewById(R.id.txtDebu);
            dateImage = itemView.findViewById(R.id.ivDate);
            timeImage = itemView.findViewById(R.id.ivTime);
            detakImage = itemView.findViewById(R.id.ivDetak);
            kbbImage = itemView.findViewById(R.id.ivKbb);
            debuImage = itemView.findViewById(R.id.ivDebu);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.date.setText(listHistory.get(position).getDate());
        holder.time.setText(listHistory.get(position).getDate());
        holder.detak.setText(listHistory.get(position).getDetak()+ " BPM");
        holder.kbb.setText(listHistory.get(position).getKbb() + " %");
        holder.debu.setText(listHistory.get(position).getDebu() + " pq/m3");
        holder.dateImage.setImageResource(R.drawable.date);
        holder.timeImage.setImageResource(R.drawable.time);
        holder.detakImage.setImageResource(R.drawable.detak);
        holder.kbbImage.setImageResource(R.drawable.kbb);
        holder.debuImage.setImageResource(R.drawable.debu);
    }

    @Override
    public int getItemCount() {
        return listHistory.size();
    }
}
