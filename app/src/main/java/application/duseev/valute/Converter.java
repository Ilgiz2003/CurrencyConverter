package application.duseev.valute;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void onButtonClick(View v){
        EditText sumRubText = (EditText) findViewById(R.id.editText);
        String chekEditText = sumRubText.getText().toString();
        int chekTextCounter=0;
        for(int i=0;i<chekEditText.length();i++){
            if(chekEditText.charAt(i) == '.' ){
                chekTextCounter++;
            }
            if (chekEditText.charAt(0) == '.' || chekEditText.charAt(chekEditText.length()-1)=='.') {
                chekTextCounter=2;
            }
        }

        if (chekEditText.isEmpty() || chekTextCounter>1) {
            Toast.makeText(this, "Поле ввода должно быть заполнено корректно!", Toast.LENGTH_SHORT).show();
        }
        else {
            TextView resText = (TextView) findViewById(R.id.result);
            Valute data = (Valute) getIntent().getExtras().getParcelable("Array_item");

            double sumRub = Double.parseDouble(sumRubText.getText().toString());
            double result = (sumRub / data.getValue()) * data.getNominal();
            resText.setText(Double.toString(result));
        }


    }
}
