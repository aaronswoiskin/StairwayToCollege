package stc.app.stairwaytocollege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.app.StairwayToCollege.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class BirminghamHSActivity extends AppCompatActivity {

    ArrayList<String> coursesTaken = new ArrayList<String>();
    String[] subjects = {"Career and Technical Education", "English", "ESL", "Fine Arts", "Mathematics", "Science", "Social Studies"};
    Dictionary gpaDict = new Hashtable<CheckBox, TextView>();
    LinearLayout[] courseLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birmingham_hs);

        courseLayouts = new LinearLayout[] {findViewById(R.id.linear0),
                                            findViewById(R.id.linear1),
                                            findViewById(R.id.linear2),
                                            findViewById(R.id.linear3),
                                            findViewById(R.id.linear4),
                                            findViewById(R.id.linear5),
                                            findViewById(R.id.linear6)};

        int[] courseListIDs = new int[]{R.array.RamsayCourse0, R.array.RamsayCourse1, R.array.RamsayCourse2, R.array.RamsayCourse3, R.array.RamsayCourse4, R.array.RamsayCourse5, R.array.RamsayCourse6};

        String[] classes = null;
        ArrayList<String> list = new ArrayList<String>();
        Log.e("", "nope2");
        File file = new File(getFilesDir(), "assets/RamsayCourses0");
        try {
            Log.e("", "nope");
            Scanner scanner = new Scanner(file);
            int size = 0;
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
                size++;
            }
            scanner.close();

            classes = new String[size];
            for (int i=0;i<size;i++) {
                classes[i] = list.get(i);
            }
            Log.e("", Integer.toString(size));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //populateCourse(courseLayouts[0], classes);

        for (int i=0;i<courseListIDs.length;i++) {
            populateCourse(courseLayouts[i], getResources().getStringArray(courseListIDs[i]));
        }

        //makeGPADict();
    }

    public void boxChecked(View v) {
        addGradesUnderBox((CheckBox) v);
    }

    public void addGradesUnderBox(CheckBox v) {
        addGradesUnderBox(v, 0);
    }

    public void addGradesUnderBox(CheckBox v, int gradeIndex) {
        CheckBox cb = v;
        LinearLayout layout = (LinearLayout) v.getParent();
        int index = layout.indexOfChild(v);

        Spinner spinner = makeGradeSpinner(gradeIndex);

        int sign = 1;

        if (cb.isChecked()) {
            layout.addView(spinner, index + 1);
            sign = 1;
        } else {
            layout.removeViewAt(index + 1);
            sign = -1;
        }
    }

    public Spinner makeGradeSpinner(int index) {
        Spinner spinner = new Spinner(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.letterGrades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setDropDownWidth(400);
        spinner.setPopupBackgroundResource(R.color.backgroundColor2);
        spinner.setSelection(index);

        return spinner;
    }

    public void showCourses(View v) {
        ScrollView s = null;
        Button b = (Button) findViewById(v.getId());

        switch(v.getId()) {
            case R.id.showBirminghamCourse0:
                s = (ScrollView) findViewById(R.id.scrollView0);
                break;
            case R.id.showBirminghamCourse1:
                s = (ScrollView) findViewById(R.id.scrollView1);
                break;
            case R.id.showBirminghamCourse2:
                s = (ScrollView) findViewById(R.id.scrollView2);
                break;
            case R.id.showBirminghamCourse3:
                s = (ScrollView) findViewById(R.id.scrollView3);
                break;
            case R.id.showBirminghamCourse4:
                s = (ScrollView) findViewById(R.id.scrollView4);
                break;
            case R.id.showBirminghamCourse5:
                s = (ScrollView) findViewById(R.id.scrollView5);
                break;
            case R.id.showBirminghamCourse6:
                s = (ScrollView) findViewById(R.id.scrollView6);
                break;
        }

        if (s.getVisibility() == View.GONE) {
            s.setVisibility(View.VISIBLE);
        } else {
            s.setVisibility(View.GONE);
        }
    }

    public void submitCourses(View v) {

        ArrayList<String> coursesTaken = new ArrayList<String>();
        ArrayList<String> subjectsTaken = new ArrayList<String>();
        ArrayList<String> gradesReceived = new ArrayList<String>();

        for (int i = 0; i < courseLayouts.length; i++) {
            LinearLayout layout = courseLayouts[i];
            int n = layout.getChildCount();
            for (int j = 0; j < n; j++) {
                View temp = layout.getChildAt(j);

                if (temp instanceof CheckBox) {
                    CheckBox check = (CheckBox) layout.getChildAt(j);
                    if (check.isChecked()) {
                        coursesTaken.add(check.getText().toString());
                        subjectsTaken.add(subjects[i]);
                    }
                } else if (temp instanceof Spinner) {
                    Spinner spinner = (Spinner) layout.getChildAt(j);
                    if (spinner.getSelectedItemPosition() > 0) {
                        String text = spinner.getSelectedItem().toString();
                        gradesReceived.add(text);
                    } else {
                        gradesReceived.add("");
                    }
                }
            }
        }

        // clear then add selected courses to user profile
        SharedPreferences sharedPref = getSharedPreferences(Home.curUsernameKey, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
        for (int i=0;i<coursesTaken.size();i++){
            editor.putString(coursesTaken.get(i), gradesReceived.get(i));
        }
        editor.commit();

        Intent intent = null;
        String univ = getIntent().getStringExtra(Home.EXTRA_TARGET_UNIV);

        intent = new Intent(this, TuscaloosaList.class);

        intent.putExtra(Home.EXTRA_TARGET_UNIV, univ);
        intent.putExtra(Home.EXTRA_COURSES_TAKEN, coursesTaken);
        intent.putExtra(Home.EXTRA_SUBJECTS_TAKEN, subjectsTaken);
        intent.putExtra(Home.EXTRA_GRADES_RECEIVED, gradesReceived);

        startActivity(intent);
    }

    private void populateCourse(LinearLayout listLayout, String[] courseList) {
        for (String course : courseList) {
            CheckBox box = new CheckBox(this);
            box.setText(course);
            box.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    boxChecked(view);
                }
            });
            if (TuscaloosaList.checkIfCourseAdvanced(course)) {
                box.setTextColor(getResources().getColor(R.color.textColorAP));
            }

            SharedPreferences sharedPref = getSharedPreferences(Home.curUsernameKey, Context.MODE_PRIVATE);
            String grade = sharedPref.getString(course, "nope");

            listLayout.addView(box);

            if (!grade.equals("nope")) {
                String[] letterGrades = getResources().getStringArray(R.array.letterGrades);
                int index = 0;
                for (int i=0;i<letterGrades.length;i++) {
                    if (letterGrades[i].equals(grade)) {
                        index = i;
                    }
                }
                box.setChecked(true);
                addGradesUnderBox(box, index);
            }

        }
    }

    private void makeGPADict() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.activity_birmingham_hs, null);

        Log.d("gpa", "start");

        //setContentView(v);

        int id = 0;
        for (int i = 0; i < courseLayouts.length; i++) {
            LinearLayout layout = courseLayouts[i];
            int n = layout.getChildCount();

            for (int j = n-1; j >= 0; j--) {
                TextView text = new TextView(this);
                text.setHint("GPA: ");
                text.setId(id++);
                text.setVisibility(View.VISIBLE);
                layout.addView(text, j+1);
                Log.d("gpa", Integer.toString((text.getId())));
            }
        }
    }
}