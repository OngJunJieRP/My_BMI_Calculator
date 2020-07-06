package sg.edu.rp.c346.id19032110.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.preference.PreferenceManager;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText etHeight;
    EditText etWeight;
    Button btnCalculate;
    Button btnReset;
    TextView tvDate;
    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etHeight = findViewById(R.id.editTextHeight);
        etWeight = findViewById(R.id.editTextWeight);
        btnCalculate = findViewById(R.id.buttonCalculate);
        btnReset = findViewById(R.id.buttonReset);
        tvDate = findViewById(R.id.textViewLastDate);
        tvBMI = findViewById(R.id.textViewLastBMI);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String currentTime = Calendar.getInstance().getTime().toString();

            Double BMI = 0.0;
            double weight = Double.parseDouble(etWeight.getText().toString());
            Double height = Double.parseDouble(etHeight.getText().toString());
            BMI = weight / (height * height);

            String stringBMI = String.format("%s %.2f", "Last Calculated BMI: " , BMI);
            tvBMI.setText(stringBMI);
            tvDate.setText("Last Calculated Date: " + currentTime);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            etHeight.setText("");
            etWeight.setText("");
            tvDate.setText("Last Calculated BMI: ");
            tvBMI.setText("Last Calculated Date: ");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();

        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();
        String time = tvDate.getText().toString();
        String BMI = tvBMI.getText().toString();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor prefEdit = prefs.edit();

        prefEdit.putString("Weight", weight);
        prefEdit.putString("Height", height);
        prefEdit.putString("Time", time);
        prefEdit.putString("BMI", BMI);

        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        String msgHeight = prefs.getString("Height", "");
        String msgWeight = prefs.getString("Weight", "");
        String msgDate = prefs.getString("Time", tvDate.getText().toString());
        String msgBMI = prefs.getString("BMI", tvBMI.getText().toString());

        etWeight.setText(msgWeight);
        etHeight.setText(msgHeight);
    }
}

