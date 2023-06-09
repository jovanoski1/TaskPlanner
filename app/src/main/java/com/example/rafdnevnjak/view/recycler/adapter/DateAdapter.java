package com.example.rafdnevnjak.view.recycler.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rafdnevnjak.R;
import com.example.rafdnevnjak.model.DutyPriority;
import com.example.rafdnevnjak.model.MyDate;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

public class DateAdapter extends ListAdapter<MyDate, DateAdapter.ViewHolder> {

    private final Consumer<MyDate> onDateClicked;

    public DateAdapter(@NonNull DiffUtil.ItemCallback<MyDate> diffCallback, Consumer<MyDate> onDateClicked) {
        super(diffCallback);
        this.onDateClicked = onDateClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Pravi novi view sa mydate_layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mydate_layout, parent, false);
        return new ViewHolder(view, parent.getContext(), position ->{
            MyDate myDate = getItem(position);
            onDateClicked.accept(myDate);
        });
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MyDate myDate = getItem(position);
        holder.bind(myDate);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context, Consumer<Integer> onItemClicked) {
            super(itemView);
            this.context = context;
            itemView.setOnClickListener(v->{
                if(getBindingAdapterPosition() != RecyclerView.NO_POSITION){
                    onItemClicked.accept(getBindingAdapterPosition());
                }
            });
        }


        public void bind(MyDate myDate){
            //System.out.println(myDate.getDate().getDayOfMonth());
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd");
            ((TextView)itemView.findViewById(R.id.dateTV)).setText(dtf.format(myDate.getDate())+".");
            ((RelativeLayout)itemView.findViewById(R.id.colorDayLayout)).setBackgroundColor(Color.parseColor("#FFFFFF"));

            if(myDate.getHighestPriority()!=null && myDate.getHighestPriority().equals(DutyPriority.LOW))
                ((RelativeLayout)itemView.findViewById(R.id.colorDayLayout)).setBackgroundColor(Color.parseColor("#57CC99"));
            else if(myDate.getHighestPriority()!=null && myDate.getHighestPriority().equals(DutyPriority.MID))
                ((RelativeLayout)itemView.findViewById(R.id.colorDayLayout)).setBackgroundColor(Color.parseColor("#F7CD35"));
            else if (myDate.getHighestPriority()!=null && myDate.getHighestPriority().equals(DutyPriority.HIGH))
                ((RelativeLayout)itemView.findViewById(R.id.colorDayLayout)).setBackgroundColor(Color.parseColor("#F58A51"));
        }
    }
}
