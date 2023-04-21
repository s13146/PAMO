package com.example.tipper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class QuizFragment extends Fragment {
    private TextView mQuestionTextView;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private Button mNextButton;

    private ArrayList<Question> questionList;
    private int mCurrentIndex;
    private int mScore;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quiz, container, false);

        initializeViews();
        setOnClickListeners();

        initiateQuestions();
        mCurrentIndex = 0;
        mScore = 0;
        setQuestion();

        return view;
    }

    private void initializeViews() {
        mQuestionTextView = view.findViewById(R.id.question_text_view);
        mRadioGroup = view.findViewById(R.id.answer_radio_group);
        mRadioButton1 = view.findViewById(R.id.choice_a_button);
        mRadioButton2 = view.findViewById(R.id.choice_b_button);
        mRadioButton3 = view.findViewById(R.id.choice_c_button);
        mNextButton = view.findViewById(R.id.submit_button);
    }

    private void setOnClickListeners() {
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleNextButtonClick();
            }
        });
    }

    private void handleNextButtonClick() {
        checkAnswer();
        mCurrentIndex++;
        if (mCurrentIndex < questionList.size()) {
            setQuestion();
        } else {
            endQuiz();
        }
    }

    private void setQuestion() {
        Question currentQuestion = questionList.get(mCurrentIndex);

        mQuestionTextView.setText(currentQuestion.getQuestion());
        mRadioButton1.setText(currentQuestion.getAnswers()[0]);
        mRadioButton2.setText(currentQuestion.getAnswers()[1]);
        mRadioButton3.setText(currentQuestion.getAnswers()[2]);
        mRadioGroup.clearCheck();
    }

    private void checkAnswer() {
        int correctAnswerIndex = questionList.get(mCurrentIndex).getCorrectAnswerIndex();
        int selectedAnswerIndex = mRadioGroup.getCheckedRadioButtonId();

        if (selectedAnswerIndex != -1) {
            RadioButton selectedAnswer = view.findViewById(selectedAnswerIndex);
            int selectedAnswerValue = mRadioGroup.indexOfChild(selectedAnswer);
            if (selectedAnswerValue == correctAnswerIndex) {
                mScore++;
            }
        }
    }

    private void endQuiz() {
        String text = "Your score is " + mScore + " out of " + questionList.size();
        showEndQuizDialog(text);
    }

    private void showEndQuizDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mCurrentIndex = 0;
                        mScore = 0;
                        setQuestion();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void initiateQuestions() {
        questionList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            Question question = new Question(
                    getResources().getString(getResources().getIdentifier("question_" + i, "string", getActivity().getPackageName())),
                    new String[]{
                            getResources().getString(getResources().getIdentifier("q" + i + "_answer_a", "string", getActivity().getPackageName())),
                            getResources().getString(getResources().getIdentifier("q" + i + "_answer_b", "string", getActivity().getPackageName())),
                            getResources().getString(getResources().getIdentifier("q" + i + "_answer_c", "string", getActivity().getPackageName()))
                    },
                    Integer.parseInt(getResources().getString(getResources().getIdentifier("q" + i + "_answer_correct_index", "string", getActivity().getPackageName())))
            );
            questionList.add(question);
        }
    }
}
