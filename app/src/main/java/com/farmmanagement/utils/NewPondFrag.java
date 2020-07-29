package com.farmmanagement.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.farmmanagement.R;
import com.farmmanagement.databinding.NewPondLayoutBinding;
import com.farmmanagement.pondlistpage.PondsViewModel;
import com.farmmanagement.roomDP.PondDetails;

import java.util.Objects;

public class NewPondFrag extends DialogFragment {
    NewPondLayoutBinding binding;
    static PondsViewModel pondsViewModel;
    static PondDetails pondDetails;
    private boolean update;
    PrefManager prefManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.MyAlertDialogTheme);
    }

    public static NewPondFrag newInstance(PondDetails updatedDetails, PondsViewModel viewModel) {
        Bundle args = new Bundle();
        NewPondFrag fragment = new NewPondFrag();
        fragment.setArguments(args);
        pondsViewModel = viewModel;
        pondDetails = updatedDetails;
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        prefManager=new PrefManager(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.new_pond_layout, container, false);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        Bundle args = getArguments();
        if (args != null) {
            update = args.getBoolean("update", false);
        }
        binding.create.setText(update ? "Update" : "Add Pond");
        if (update) {
            binding.newpondName.setText(pondDetails.getPondname());
            binding.newPondSize.setText("" + pondDetails.getPondsize());
        }
        binding.create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Objects.requireNonNull(binding.newpondName.getText()).toString();
                if (pondsViewModel.createPondValidation(name, binding.newPondSize.getText().toString())) {
                    float size = Float.parseFloat(binding.newPondSize.getText().toString());
                    if (update) {
                        pondsViewModel.updatePond(new PondDetails(pondDetails.getPondid(), name, size, pondDetails.getMobilenumber()));
                    } else {
                        pondsViewModel.insertPond(new PondDetails(System.currentTimeMillis(), name, size, PrefManager.getLoggedInMobileNumber()));
                    }
                    dismiss();
                } else {
                    if (getActivity() != null) {
                        CommonUtil.closeKeyPad(getActivity(), binding.create);
                    }
                    Toast.makeText(getActivity(), "Enter valid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return binding.getRoot();
    }
}
