package com.farmmanagement.pondlistpage;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.farmmanagement.R;
import com.farmmanagement.databinding.ActivityPondMainBinding;
import com.farmmanagement.loginpage.LoginActivity;
import com.farmmanagement.roomDP.PondDetails;
import com.farmmanagement.utils.NewPondFrag;
import com.farmmanagement.utils.PrefManager;

import java.util.Collections;
import java.util.List;

public class PondMainActivity extends AppCompatActivity implements PondAdapter.PondInterface {
    ActivityPondMainBinding dataBinding;
    PondsViewModel viewModel;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pond_main);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_pond_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        prefManager = new PrefManager(PondMainActivity.this);
        dataBinding.recyclerview.setLayoutManager(layoutManager);
        PondsViewModelFactory factory = new PondsViewModelFactory(this.getApplication(), PrefManager.getLoggedInMobileNumber());
        viewModel = ViewModelProviders.of(this, factory).get(PondsViewModel.class);
        dataBinding.nodataMessage.setVisibility(View.GONE);
        dataBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

        dataBinding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PondMainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        dataBinding.fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewPondFrag upiBs = NewPondFrag.newInstance(null, viewModel);
                Intent intent = new Intent();
                intent.putExtra("update", false);
                Bundle bundle = intent.getExtras();
                upiBs.setArguments(bundle);
                upiBs.show(getSupportFragmentManager(), upiBs.getTag());
            }
        });

        viewModel.mAllPonds.observe(this, new Observer<List<PondDetails>>() {
            @Override
            public void onChanged(List<PondDetails> pondDetails) {
                dataBinding.nodataMessage.setVisibility(pondDetails != null && pondDetails.size() != 0 ? View.GONE : View.VISIBLE);
                dataBinding.recyclerview.setVisibility(pondDetails != null && pondDetails.size() != 0 ? View.VISIBLE : View.GONE);
                if (pondDetails != null && pondDetails.size() != 0) {
                    for (int i = 0; i < pondDetails.size(); i++) {
                        Log.d("OBSERVE", "" + pondDetails.get(i).getMobilenumber() + " Login Number " + PrefManager.getLoggedInMobileNumber());
                        Log.d("OBSERVE", "" + pondDetails.get(i).getPondname());
                        Log.d("OBSERVE", "" + pondDetails.get(i).getPondid());
                    }
                    Collections.reverse(pondDetails);
                    PondAdapter adapter = new PondAdapter(PondMainActivity.this, pondDetails, PondMainActivity.this);
                    dataBinding.recyclerview.setAdapter(adapter);
                }
            }
        });

    }

    @Override
    public void update(PondDetails updatedDetails) {
        NewPondFrag pondFrag = NewPondFrag.newInstance(updatedDetails, viewModel);
        Intent intent = new Intent();
        intent.putExtra("update", true);
        Bundle bundle = intent.getExtras();
        pondFrag.setArguments(bundle);
        pondFrag.show(getSupportFragmentManager(), pondFrag.getTag());
    }

    @Override
    public void delete(PondDetails t) {
        viewModel.deletePond(t);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}