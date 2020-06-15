package com.stappco.password.sesamo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.stappco.password.sesamo.R;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<ExampleItemSocial> mExampleList;


    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextTitle;
        public TextView mTextPassword;
        public TextView mTextNotes;
        public Button eye,eye_un;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.title_text);
            mTextPassword = itemView.findViewById(R.id.password_text);
            mTextNotes = itemView.findViewById(R.id.note_text);
            eye = itemView.findViewById(R.id.eye);
            eye_un = itemView.findViewById(R.id.eye_un);
        }
    }
    public ExampleAdapter(ArrayList<ExampleItemSocial> exampleList) {
        mExampleList = exampleList;
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item_social, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(final ExampleViewHolder holder, int position) {
        ExampleItemSocial currentItem = mExampleList.get(position);
        holder.mTextTitle.setText(currentItem.getTitle());
        holder.mTextPassword.setText(currentItem.getPassword());
        holder.mTextNotes.setText(currentItem.getNotes());


        holder.eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mTextPassword.setVisibility(View.VISIBLE);
                holder.mTextNotes.setVisibility(View.GONE);
                holder.eye.setVisibility(View.GONE);
                holder.eye_un.setVisibility(View.VISIBLE);
            }
        });

        holder.eye_un.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mTextPassword.setVisibility(View.GONE);
                holder.mTextNotes.setVisibility(View.VISIBLE);
                holder.eye.setVisibility(View.VISIBLE);
                holder.eye_un.setVisibility(View.GONE);
            }
        });


    }
    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
