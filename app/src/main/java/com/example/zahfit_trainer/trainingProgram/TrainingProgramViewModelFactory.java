package com.example.zahfit_trainer.trainingProgram;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.zahfit_trainer.model.Plan;

import java.util.List;

public class TrainingProgramViewModelFactory implements ViewModelProvider.Factory {
    private List<Plan> planList;

    public TrainingProgramViewModelFactory(List<Plan> planList) {
        this.planList = planList;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TrainingProgramViewModel.class)) {
            return (T) new TrainingProgramViewModel(planList);
        }
        throw new IllegalArgumentException("ViewModel Must be TrainingProgramViewModel");
    }
}
