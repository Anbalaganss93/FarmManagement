package com.farmmanagement.loginpage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.farmmanagement.R;
import com.farmmanagement.databinding.ActivityLoginBinding;
import com.farmmanagement.pondlistpage.PondMainActivity;
import com.farmmanagement.pondlistpage.PondsViewModel;
import com.farmmanagement.pondlistpage.PondsViewModelFactory;
import com.farmmanagement.registerpage.RegisterActivity;
import com.farmmanagement.registerpage.RegisterValidationModel;
import com.farmmanagement.roomDP.UserDetail;
import com.farmmanagement.utils.CommonUtil;
import com.farmmanagement.utils.PrefManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding activityLoginBinding;
    LoginViewModel loginViewModel;
    PondsViewModel pondsViewModel;
    PrefManager prefManager;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        activityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        prefManager = new PrefManager(LoginActivity.this);

        activityLoginBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobileno = activityLoginBinding.mobilenumber.getText().toString().trim();
                PrefManager.setLoggedInMobileNumber(mobileno);
                PondsViewModelFactory factory = new PondsViewModelFactory(getApplication(), PrefManager.getLoggedInMobileNumber());
                pondsViewModel = ViewModelProviders.of(LoginActivity.this, factory).get(PondsViewModel.class);
                RegisterValidationModel model = loginViewModel.loginValidation(mobileno);
                if (model.isValidation()) {
                    pondsViewModel.checkUserPresence(mobileno);
                    pondsViewModel.mUserPresence.observe(LoginActivity.this, new Observer<List<UserDetail>>() {
                        @Override
                        public void onChanged(List<UserDetail> userDetails) {
                            if (userDetails != null && userDetails.size() != 0) {
                                Intent intent = new Intent(LoginActivity.this, PondMainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                getViewModelStore().clear();
                                Toast.makeText(LoginActivity.this, "Please register your number", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "" + model.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
                CommonUtil.closeKeyPad(LoginActivity.this, activityLoginBinding.login);
            }
        });


        activityLoginBinding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}