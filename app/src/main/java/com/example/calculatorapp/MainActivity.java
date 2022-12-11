package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView TVResult , TVSolution ;
    private MaterialButton ButtonC, ButtonOB , ButtonCB , Button7, Button8 ,Button9 , Button4,Button5 ,Button6 ,Button3 ,Button2 ,Button1 ,ButtonDot ,ButtonZ;
    private MaterialButton ButtonDiv, ButtonMul , ButtonAdd, ButtonSub ,ButtonAC, ButtonEql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TVResult = findViewById(R.id.idTVResult);
        TVSolution = findViewById(R.id.idTVSolution);
        assignID(ButtonSub,R.id.idButton_minus);
        assignID(ButtonAC,R.id.idButton_0);
        assignID(ButtonAdd,R.id.idButton_add);
        assignID(Button7,R.id.idButton_7);
        assignID(Button6,R.id.idButton_6);
        assignID(Button5,R.id.idButton_5);
        assignID(Button8,R.id.idButton_8);
        assignID(Button9,R.id.idButton_9);
        assignID(ButtonZ,R.id.idButton_0);
        assignID(Button4,R.id.idButton_4);
        assignID(Button3,R.id.idButton_3);
        assignID(Button2,R.id.idButton_2);
        assignID(Button1,R.id.idButton_1);
        assignID(ButtonMul,R.id.idButton_multiply);
        assignID(ButtonDiv,R.id.idButton_divide);
        assignID(ButtonC,R.id.idButton_C);
        assignID(ButtonCB,R.id.idButton_close_bracket);
        assignID(ButtonOB,R.id.idButton_open_bracket);
        assignID(ButtonDot,R.id.idButton_decimal);
        assignID(ButtonEql,R.id.idButton_equal);

    }
void assignID(MaterialButton btn , int id){
        btn = findViewById(id);
        btn.setOnClickListener(this );
}
    @Override
    public void onClick(View view) {
        MaterialButton Button = (MaterialButton) view;
        String ButtonText = Button.getText().toString();
        String dataToCalculate = TVSolution.getText().toString();

        if (ButtonText.equals("AC")) {
            TVSolution.setText("");
            TVResult.setText("0");
            return;
        }
        if (ButtonText.equals("=")) {
            TVSolution.setText(TVResult.getText());
            return;
        }

        if (ButtonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + ButtonText;

        }
        TVSolution.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("Err")){
            TVResult.setText(finalResult);
        }
    }

    String getResult (String data){
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalResult.endsWith(".0")){
               finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }
    }
}