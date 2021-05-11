package stc.app.stairwaytocollege;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.StairwayToCollege.R;

public class ChooseSchool extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_school);

        Spinner spinnerHS = (Spinner) findViewById(R.id.spinnerSelectHS);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.HighSchoolList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHS.setAdapter(adapter);

        Spinner spinnerUniv = (Spinner) findViewById(R.id.spinnerSelectUniv);
        adapter = ArrayAdapter.createFromResource(this, R.array.UniversityList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUniv.setAdapter(adapter);
    }

    public void sendMessage(View view) {
        //Intent intent = new Intent(this, DisplaySchoolActivity.class);
        Intent intent = new Intent(this, BirminghamHSActivity.class);

        Spinner userHS = (Spinner) findViewById(R.id.spinnerSelectHS);
        Spinner userUniv = (Spinner) findViewById(R.id.spinnerSelectUniv);

        boolean schoolsChosen = true;

        Toast toast = null;

        switch (userHS.getSelectedItem().toString()) {
            case "Select High School":
                Log.d("f", "a");
                toast = Toast.makeText(getApplicationContext(), "Please select a high school", Toast.LENGTH_SHORT);
                schoolsChosen = false;
                break;
            case "Ramsay High School":
            case "r":
                intent = new Intent(this, BirminghamHSActivity.class);
                break;
            default:
                toast = Toast.makeText(getApplicationContext(), "High school not found!", Toast.LENGTH_SHORT);
        }

        switch (userUniv.getSelectedItem().toString()) {
            case "Select University":
                Log.d("f", "b");
                toast = Toast.makeText(getApplicationContext(), "Please select a university", Toast.LENGTH_SHORT);
                schoolsChosen = false;
                break;
            case "University of Alabama":
            case "Auburn University":
            case "":
                intent.putExtra(Home.EXTRA_TARGET_UNIV, userUniv.getSelectedItem().toString());
                break;
            default:
                toast = Toast.makeText(getApplicationContext(), "University not found!", Toast.LENGTH_SHORT);
        }

        if (toast != null) {
            toast.show();
        }

        if (schoolsChosen) {
            //intent = new Intent(this, TuscaloosaRecommendCourses.class);
            startActivity(intent);
        }
    }
    /*
    jack's notes

    add major for recommendations
    add icons to HS class selection
    color buttons/icons/background, each subject different color
    progress tracker for HS classes
    description button for each class (new page)

    animations/sound effects (?)

    make labels pop
    add logo
    fix rows of buttons
    
    add AP/IB/Honors
        new color
        5.0 gpa

    first time questions: grade
    dropdown
    button text brighter/bigger
    add copyright symbol/app logo

    add "admissions checklist"
    add "course recommendations"
    change dropdown color
    "Recommend Me Courses To Admission"
    "continuation"
    */
}