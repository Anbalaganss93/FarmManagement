package com.farmmanagement.pondlistpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.farmmanagement.R;
import com.farmmanagement.databinding.RecyclerviewPondLayoutBinding;
import com.farmmanagement.roomDP.PondDetails;

import java.util.List;

public class PondAdapter extends RecyclerView.Adapter<PondAdapter.TasksViewHolder> {

    private Context mCtx;
    private List<PondDetails> taskList;
    private PondInterface pondInterface;

    public PondAdapter(Context mCtx, List<PondDetails> taskList, PondMainActivity listner) {
        this.mCtx = mCtx;
        this.taskList = taskList;
        this.pondInterface = listner;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerviewPondLayoutBinding recyclerviewPondLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_pond_layout, parent, false);
        return new TasksViewHolder(recyclerviewPondLayoutBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        final PondDetails t = taskList.get(position);
        holder.recyclerviewPondLayoutBinding.pondName.setText(t.getPondname());
        holder.recyclerviewPondLayoutBinding.pondSize.setText("" + t.getPondsize()+" hectares");
        holder.recyclerviewPondLayoutBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pondInterface.delete(t);
            }
        });
        holder.recyclerviewPondLayoutBinding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pondInterface.update(t);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RecyclerviewPondLayoutBinding recyclerviewPondLayoutBinding;

        public TasksViewHolder(RecyclerviewPondLayoutBinding recyclerviewPondLayoutBinding) {
            super(recyclerviewPondLayoutBinding.getRoot());
            this.recyclerviewPondLayoutBinding = recyclerviewPondLayoutBinding;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }

    public interface PondInterface {
        void delete(PondDetails t);

        void update(PondDetails updatedDetails);
    }
}
