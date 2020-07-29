package com.farmmanagement.registerpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.farmmanagement.R;
import com.farmmanagement.databinding.ActivityRegisterBinding;
import com.farmmanagement.loginpage.LoginActivity;
import com.farmmanagement.pondlistpage.PondsViewModel;
import com.farmmanagement.pondlistpage.PondsViewModelFactory;
import com.farmmanagement.roomDP.UserDetail;
import com.farmmanagement.utils.PrefManager;


public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding registerBinding;
    RegisterViewModel registerViewModel;
    PondsViewModel pondsViewModel;
    PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerBinding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        prefManager = new PrefManager(RegisterActivity.this);
        PondsViewModelFactory factory = new PondsViewModelFactory(this.getApplication(), PrefManager.getLoggedInMobileNumber());
        pondsViewModel = ViewModelProviders.of(this, factory).get(PondsViewModel.class);
        registerBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearData();
                callLogin();
            }
        });
        registerBinding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobilenumber = registerBinding.mobilenumber.getText().toString().trim();
                String userName = registerBinding.userName.getText().toString().trim();
                String farmName = registerBinding.farmName.getText().toString().trim();
                String farmAddress = registerBinding.farmAddress.getText().toString().trim();
                String pinCode = registerBinding.pinCode.getText().toString().trim();
                RegisterValidationModel model = registerViewModel.loginValidation(mobilenumber, userName, farmName, farmAddress, pinCode);
                if (model.isValidation()) {
                    PrefManager.setLoggedInMobileNumber(mobilenumber);
                    pondsViewModel.insert(new UserDetail(mobilenumber, userName, farmName, farmAddress, pinCode));
                } else {
                    Toast.makeText(RegisterActivity.this, "" + model.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void callLogin() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        callLogin();
    }

    private void clearData() {
        registerBinding.mobilenumber.setText("");
        registerBinding.userName.setText("");
        registerBinding.farmName.setText("");
        registerBinding.farmAddress.setText("");
        registerBinding.pinCode.setText("");
    }
}