package stc.app.stairwaytocollege;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.app.StairwayToCollege.R;

public class FirstTimeInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_time_info);
    }

    public void firstTimeQuestionOnClick(View v) {
        RadioButton b = (RadioButton) findViewById(v.getId());
        if (!b.isChecked()) {
            b.setChecked(false);
        }
    }

    public void firstTimeQuestionSubmit(View v) {
        Intent intent = new Intent(this, ChooseSchool.class);
        startActivity(intent);
    }

    public void firstTimeQuestionOnScroll(View v) {

    }
}