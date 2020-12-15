package com.example.zahfit_trainer.trainerHomepage;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.zahfit_trainer.R;
import com.example.zahfit_trainer.addProgram.AddProgramFragment;
import com.example.zahfit_trainer.databinding.FragmentTrainerHomepageBinding;
import com.example.zahfit_trainer.login.LoginFragment;
import com.example.zahfit_trainer.profile.ProfileFragment;
import com.example.zahfit_trainer.trainingProgram.TrainingProgramFragment;

public class TrainerHomepageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    FragmentTrainerHomepageBinding binding;
    private String tabTitles[] = {"Training Program", "", "Profile"};
    private Context context;

    public TrainerHomepageAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public int getCount(){
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position){
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new TrainingProgramFragment();
                break;
            case 1:
                fragment = new AddProgramFragment();
                break;
            case 2 :
                fragment = new ProfileFragment();
                break;
        }
        return fragment;
    }

    @Override public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}
