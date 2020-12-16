package com.example.zahfit_trainer.trainingProgram;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zahfit_trainer.databinding.TrainingProgramItemBinding;
import com.example.zahfit_trainer.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class TrainingProgramAdapter extends RecyclerView.Adapter<TrainingProgramAdapter.TrainingViewHolder> {
    List<Plan> list = new ArrayList<>();


    public TrainingProgramAdapter() {
    }

    public void setList(List<Plan> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrainingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TrainingProgramItemBinding binding = TrainingProgramItemBinding.inflate(layoutInflater, parent, false);
        return new TrainingProgramAdapter.TrainingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingViewHolder holder, int position) {
        Plan plan = list.get(position);
        holder.bind(plan);
    }

    @Override
    public int getItemCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    public class TrainingViewHolder extends RecyclerView.ViewHolder {
        private TrainingProgramItemBinding binding;

        public TrainingViewHolder(TrainingProgramItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Plan plan) {
            Log.i("URI", "img uri: " + plan.getUri());
            Glide.with(binding.getRoot().getContext()).load(plan.getUri()).into(binding.imageView2);
            binding.setPlan(plan);
            binding.executePendingBindings();
        }
    }
}
