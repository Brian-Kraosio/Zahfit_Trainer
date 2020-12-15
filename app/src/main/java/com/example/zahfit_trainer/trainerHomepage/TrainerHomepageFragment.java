package com.example.zahfit_trainer.trainerHomepage;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zahfit_trainer.R;
import com.example.zahfit_trainer.databinding.FragmentTrainerHomepageBinding;

public class TrainerHomepageFragment extends Fragment {

    FragmentTrainerHomepageBinding binding;
    TrainerHomepageAdapter adapter;
    private int tabIcon[] = {R.drawable.ic_baseline_list, R.drawable.ic_baseline_add_circle_24, R.drawable.ic_baseline_person_24};

    public TrainerHomepageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trainer_homepage, container, false);

//
        binding.viewPager.setAdapter(new TrainerHomepageAdapter(getChildFragmentManager()));
        binding.tabs.setupWithViewPager(binding.viewPager);
        setTabIcon();
        return binding.getRoot();
    }

    private void setTabIcon(){
        binding.tabs.getTabAt(0).setIcon(tabIcon[0]);
        binding.tabs.getTabAt(1).setIcon(tabIcon[1]);
        binding.tabs.getTabAt(2).setIcon(tabIcon[2]);
    }
}