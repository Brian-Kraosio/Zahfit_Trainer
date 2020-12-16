package com.example.zahfit_trainer.profile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.zahfit_trainer.R;
import com.example.zahfit_trainer.databinding.FragmentProfileBinding;
import com.example.zahfit_trainer.model.PersonalTrainer;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    private FirebaseUser user;
    private DatabaseReference mDatabaseReference;
    private Uri uri;
    PersonalTrainer personalTrainer;
    FirebaseStorage storage;
    StorageReference storageReference;
    private final int PICK_IMAGE_REQUEST = 1;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        View view = binding.getRoot();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String getTrainerId = user.getUid();
        StorageReference mStorageReference = FirebaseStorage.getInstance().getReference();
        mDatabaseReference.child("personal_trainer").child(getTrainerId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                personalTrainer = snapshot.getValue(PersonalTrainer.class);
                binding.trainerName.setText(personalTrainer.getPersonal_trainer_name());

                SpannableStringBuilder ssb = new SpannableStringBuilder();

                ssb.append(" ");

                ssb.setSpan(
                        new ImageSpan(getContext(), R.drawable.ic_baseline_star_rate_24),
                        ssb.length() - 1,
                        ssb.length(),
                        0);

                ssb.append(personalTrainer.getPersonal_trainer_ratings());

                binding.starRatings.setText(ssb);

                mStorageReference.child(getTrainerId + ".png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(view.getContext()).load(uri).into(binding.profilePicture);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        String uri = "@drawable/ic_baseline_person_24";
                        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
                        Drawable res = getActivity().getDrawable(imageResource);
                        binding.profilePicture.setImageDrawable(res);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Navigation.findNavController(view).navigate(R.id.loginFragment);
            }
        });

        binding.profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK){
            storageReference = FirebaseStorage.getInstance().getReference();
            mDatabaseReference = FirebaseDatabase.getInstance().getReference();
            String img = user.getUid() + ".png";
            uri = data.getData();
            StorageReference filepath=storageReference.child(img);
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    mDatabaseReference.child("personal_trainer").child(user.getUid()).setValue(personalTrainer);
                }
            });
        }
    }
}