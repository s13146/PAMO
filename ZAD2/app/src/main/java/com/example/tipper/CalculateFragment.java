package com.example.tipper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import com.example.tipper.databinding.ActivityMainBinding;
import com.example.tipper.databinding.FragmentCalculateBinding;

public class CalculateFragment extends Fragment {
    private FragmentCalculateBinding binding;

    private EditText weightEdit;
    private EditText growthEdit;
    private TextView bmiTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= FragmentCalculateBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        weightEdit = root.findViewById(R.id.weightEditText);

        weightEdit = root.findViewById(R.id.weightEditText);
        growthEdit = root.findViewById(R.id.growthEditText);
        bmiTextView = root.findViewById(R.id.bmiTextView);
        Button button = root.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
                closeKeyboard();
            }

        });
        return root;

    }

    private void calculate() {

        float weight = Float.parseFloat(weightEdit.getText().toString());
        float growth = Float.parseFloat(growthEdit.getText().toString());
        float bmi = (100 * 100 * weight) / (growth * growth);
        String calculatedBmi = String.format("%.2f", bmi);
        bmiTextView.setText("Twoje BMI: " + calculatedBmi);
    }

    private void closeKeyboard() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.closeKeyboard();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
