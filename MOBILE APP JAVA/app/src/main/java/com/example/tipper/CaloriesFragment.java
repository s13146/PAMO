package com.example.tipper;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.tipper.databinding.FragmentCaloriesBinding;


public class CaloriesFragment extends Fragment {
    private FragmentCaloriesBinding binding;

    private EditText weightEdit;
    private EditText growthEdit;
    private EditText ageEdit;
    RadioGroup sexRadioGroup;
    RadioButton maleRadioButton, femaleRadioButton;
    private TextView ppmTextView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding= FragmentCaloriesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        weightEdit = root.findViewById(R.id.weightEditText);

        weightEdit = root.findViewById(R.id.weightEditText);
        growthEdit = root.findViewById(R.id.growthEditText);
        ageEdit = root.findViewById(R.id.ageEditText);
        ppmTextView = root.findViewById(R.id.ppmTextView);
        sexRadioGroup = root.findViewById(R.id.sexRadioGroup);
        maleRadioButton = root.findViewById(R.id.maleRadioButton);
        femaleRadioButton = root.findViewById(R.id.femaleRadioButton);

        Button button = root.findViewById(R.id.button);

        button.setOnClickListener(view -> {
            double weight = Double.parseDouble(weightEdit.getText().toString());
            double height = Double.parseDouble(growthEdit.getText().toString());
            int age = Integer.parseInt(ageEdit.getText().toString());
            int sex = 0;
            if (maleRadioButton.isChecked()) {
                sex = 1;
            } else if (femaleRadioButton.isChecked()) {
                sex = 2;
            }
            double ppm = calculatePPM(weight, height, age, sex);
            weightEdit.getText().clear();
            growthEdit.getText().clear();
            ageEdit.getText().clear();
            sexRadioGroup.clearCheck();
            closeKeyboard();
            ppmTextView.setText(String.format("Twoje zapotrzebowanie kalorii to: %.2f", ppm));

        });
        return root;

    }
    private void closeKeyboard() {
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.closeKeyboard();

    }
    private double calculatePPM(double weight, double height, int age, int sex) {
        switch (sex) {
            case 2:
                return 655.1f + (9.563f * weight) + (1.85f * height) - (4.676f * age);
            case 1:
                return 66.5f + (13.75f * weight) + (5.003f * height) - (6.775f * age);
        }
        return 0;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}