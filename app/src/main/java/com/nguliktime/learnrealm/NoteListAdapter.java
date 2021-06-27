package com.nguliktime.learnrealm;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.MyViewHolder> {
    private List<NoteModel> noteModels;
    Context context;
    private Callback callback;

    interface Callback {
        void onClick(int position);
    }

    public NoteListAdapter(Context context, List<NoteModel> noteModels, Callback callback){
        this.context = context;
        this.noteModels = noteModels;
        this.callback = callback;
    }

    @Override
    public NoteListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notelist, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final NoteListAdapter.MyViewHolder holder, int position) {
        final NoteModel model = noteModels.get(position);
        holder.name.setText(model.getName().toString());
        holder.note.setText(model.getDetailnote());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(holder.getAdapterPosition());
//                Intent intent = new Intent(v.getContext(), DetailActivity.class);
//                intent.putExtra("id", model.getId().toString());
//                intent.putExtra("nim", model.getName().toString());
//                intent.putExtra("nama", model.getDetailnote());
//                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, note;

        public MyViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.tvNameNote);
            note = itemView.findViewById(R.id.tvDetailNote);
        }
    }
}
