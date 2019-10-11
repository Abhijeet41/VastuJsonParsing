package com.example.vastujsonparsing;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

    private Context context;

    //List to store all superheroes
    List<Model> modelList;

    public RecyclerAdapter(List<Model> modelList, MainActivity mainActivity) {
        this.context = mainActivity;
        this.modelList = modelList;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        final Model model =  modelList.get(position);

        holder.id.setText(model.getId());
        holder.table_name.setText(model.getTable_name());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NextActivity.class);
//attach the key value pair using putExtra to this intent
               // String user_name = "Jhon Doe";
                intent.putExtra("USER_NAME", model.getData_masters());
                intent.putExtra("value",model.getValue());
//starting the activity
                context.startActivity(intent);
            }
        });

       // holder.data_masters.setText(model.getData_masters());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView id,table_name,data_masters;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.txtId);
            table_name = itemView.findViewById(R.id.txtTable_name);
            data_masters = itemView.findViewById(R.id.txtData_masters);

            linearLayout = itemView.findViewById(R.id.ll);


        }
    }
}
