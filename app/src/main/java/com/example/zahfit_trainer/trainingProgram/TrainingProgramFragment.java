package com.example.zahfit_trainer.trainingProgram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfit_trainer.R;
import com.example.zahfit_trainer.databinding.FragmentTrainingProgramBinding;
import com.example.zahfit_trainer.model.Plan;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class TrainingProgramFragment extends Fragment {

    private FragmentTrainingProgramBinding binding;
    private TrainingProgramViewModel viewModel;
    private List<Plan> planList;
    private DatabaseReference mDatabase;
    private FirebaseUser user;

    public TrainingProgramFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_training_program, container, false);
        TrainingProgramViewModelFactory trainingProgramViewModelFactory = new TrainingProgramViewModelFactory(planList);
        viewModel = new ViewModelProvider(this, trainingProgramViewModelFactory).get(TrainingProgramViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        setupRvTrainingProgram();
        return binding.getRoot();
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

    private void setupRvTrainingProgram(){
        RecyclerView recyclerView = binding.trainingProgramRv;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        user = FirebaseAuth.getInstance().getCurrentUser();
        String getTrainerId = user.getUid();
        mDatabase.child("exercise_plan").orderByChild("personal_trainer_id").equalTo(getTrainerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                planList = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Plan plan = dataSnapshot.getValue(Plan.class);
                    plan.setPlan_key(dataSnapshot.getKey());
                    planList.add(plan);
                }
                recyclerView.setLayoutManager(linearLayoutManager);
                TrainingProgramAdapter adapter = new TrainingProgramAdapter();
                recyclerView.setAdapter(adapter);
                adapter.setList(planList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}