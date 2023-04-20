package com.example.practiceapp_120922;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.mozilla.javascript.Context ;
import org.mozilla.javascript.Scriptable ;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewResult, textViewSoluton ;

    private Button buttonAC, buttonOpenBrac, buttonCloseBrac, buttonBackSpace, buttonDot, buttonEqual ;
    private Button buttonDiv, buttonMul, buttonSub, buttonAdd ;
    private Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = findViewById(R.id.textViewResult) ;
        textViewSoluton = findViewById(R.id.textViewSolution) ;

        assignId(buttonAC, R.id.buttonAC);
        assignId(buttonOpenBrac, R.id.buttonOpenBracket);
        assignId(buttonCloseBrac, R.id.buttonCloseBracket);
        assignId(buttonBackSpace, R.id.buttonBackSpace) ;
        assignId(buttonDot, R.id.buttonDot) ;
        assignId(buttonEqual, R.id.buttonEqual) ;
        assignId(buttonDiv, R.id.buttonDivide);
        assignId(buttonMul, R.id.buttonMul);
        assignId(buttonSub, R.id.buttonSub);
        assignId(buttonAdd, R.id.buttonAdd) ;
        assignId(button0, R.id.button0);
        assignId(button1, R.id.button1);
        assignId(button2, R.id.button2);
        assignId(button3, R.id.button3);
        assignId(button4, R.id.button4);
        assignId(button5, R.id.button5);
        assignId(button6, R.id.button6);
        assignId(button7, R.id.button7);
        assignId(button8, R.id.button8);
        assignId(button9, R.id.button9);


    }

    void assignId(Button button, int id) {
        button = findViewById(id) ;
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view ;


        String buttonText = button.getText().toString() ;
        String textInTVSol = textViewSoluton.getText().toString();

//        if (button.getId()==R.id.buttonAC) {
        if (buttonText.equals("AC")) {

            textViewSoluton.setText("");
            textViewResult.setText("0");

            return ;
        }

//        if (button.getId() == R.id.buttonBackSpace && textInTVSol.length() != 0) {
        if (buttonText.equals("") && textInTVSol.length() != 0) {

            textViewSoluton.setText(textInTVSol.substring(0, textInTVSol.length()-1));

            return ;
        }

        if (buttonText.equals("=")) {

            String ans = getResult(textInTVSol.replace("x", "*")) ;

            if (ans.endsWith(".0")) {
                ans = ans.replace(".0", "") ;
            }
            textViewResult.setText(ans);

            return ;
        }


        textViewSoluton.setText(textInTVSol + buttonText);



    }

    private String getResult(String expression) {
        try {
            Context context = Context.enter() ;
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, expression, "Javascript", 1, null).toString() ;

            return result ;
        }

        catch (Exception e) {
            return "Invalid" ;
        }
    }
}