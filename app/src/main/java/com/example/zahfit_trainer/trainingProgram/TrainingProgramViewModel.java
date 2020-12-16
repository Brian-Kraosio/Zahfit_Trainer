package com.example.zahfit_trainer.trainingProgram;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.zahfit_trainer.model.Plan;

import java.util.List;

public class TrainingProgramViewModel extends ViewModel {
    private List<Plan> planList = null;
    private MutableLiveData<List<Plan>> listMutableLiveData = new MutableLiveData<>();

    public TrainingProgramViewModel() {
    }

    public TrainingProgramViewModel(List<Plan> planList) {
        this.planList = planList;
    }

    public TrainingProgramViewModel(MutableLiveData<List<Plan>> listMutableLiveData) {
        this.listMutableLiveData = listMutableLiveData;
    }

    public TrainingProgramViewModel(List<Plan> planList, MutableLiveData<List<Plan>> listMutableLiveData) {
        this.planList = planList;
        this.listMutableLiveData = listMutableLiveData;
    }

    public LiveData<List<Plan>> listLiveData(){
        return listMutableLiveData;
    }


}
