package stc.app.stairwaytocollege;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.StairwayToCollege.R;

public class DisplaySchoolActivity extends AppCompatActivity {

    private String auburnText =
            "Course Requirements: \n\n" +
            "English - 4 years\n\n" +
            "Social Studies - 3 years\n\n" +
            "Mathematics - 3 years\n" +
            "\t• This must include 1 year of Algebra I, 1 year of Algebra II, and one year of Geometry, Trigonometry, Calculus, or Analysis\n\n" +
            "Science - 2 years\n" +
            "\t• This must include 1 year of Biology and 1 year of a Physical Science\n\n" +
            "ACT/SAT 50th percentile: \n" +
            "\t• ACT: 25 - 30\n" +
            "\t• SAT: 1170 - 1300\n\n" +
            "GPA average (unweighted): 3.89";

    private String birminghamReqs =
            "Course Requirements: \n\n" +
            "English - 4 credits\n" +
            "\t• English 9\n" +
            "\t• English 10\n" +
            "\t• English 11\n" +
            "\t• English 12\n\n" +
            "Mathematics - 4 credits\n" +
            "\t• Algebra I\n" +
            "\t• Geometry\n" +
            "\t• Algebra II w/ Trig\n\n" +
            "Science - 4 credits\n" +
            "\t• Biology\n" +
            "\t• Physical Science (Chemistry, Physics, etc)\n" +
            "\t• 3rd and 4th choice from course list\n\n" +
            "History - 4 credits\n" +
            "\t• World History\n" +
            "\t• United States History I\n" +
            "\t• United States Government\n" +
            "\t• United States History II\n" +
            "\t• Economics\n\n" +
            "Physical Education - 1 credit\n" +
            "\t• Lifelong Individualized Fitness Education (LIFE) OR\n" +
            "\t• One JROTC credit\n\n" +
            "Health Education - 0.5 credits\n\n" +
            "Career Preparedness - 1 credit\n\n" +
            "Electives - 2.5 credits\n\n" +
            "Career and Technical Education AND/OR Foreign Language AND/OR Arts Education- 3 credits\n\n";

    private String tuscaloosaReqs =
            "Course Requirements: \n\n" +
            "English - 4 credits\n\n" +
            "Social Science - 4 credits\n\n" +
            "Mathematics - 3 credits\n\n" +
            "Natural Sciences - 3 credits\n\n" +
            "Foreign Language - 1 credit\n\n" +
            "Electives - 5 credits\n\n";


    private String[] birmingHamCourseOrder = new String[]{
            "Career and Technical Education",
            "English Language Arts (ELA)",
            "English as a Second Language (ESL)",
            "Fine Arts",
            "Foreign Language",
            "Mathematics",
            "Science",
            "Social Studies"
    };

    private String[] birmingHamCourses = new String[8];

    private String defaultText = "";//""School not found!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_school);

        populateBirmingHamCourses(birmingHamCourses);
        Button clearButton = (Button) findViewById(R.id.resetCourseList);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringArrayExtra(Home.EXTRA_MESSAGE)[0];
        String option = intent.getStringArrayExtra(Home.EXTRA_MESSAGE)[1];

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);
        Spinner birminghamCourseTypeSpinner = (Spinner) findViewById(R.id.birmingham_coursetype_spinner);

        ArrayAdapter<String> birminghamAdapter = new ArrayAdapter<String>(DisplaySchoolActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.birmingham_coursetypes));
        birminghamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birminghamCourseTypeSpinner.setAdapter(birminghamAdapter);

        if (option.equals("Course List")){
            message = "";
        } else {
            Button button = (Button) findViewById(R.id.submitCourseList);
            birminghamCourseTypeSpinner.setVisibility(View.INVISIBLE);
            button.setVisibility(View.INVISIBLE);
        }

        switch (message.toLowerCase()) {
            case "auburn":
                textView.setText(auburnText);
                break;
            case "birmingham":
                textView.setText(birminghamReqs);
                break;
            case "tuscaloosa":
                textView.setText(tuscaloosaReqs);
                break;
            default:
                textView.setText(defaultText);
                break;
        }
    }

    void populateBirmingHamCourses(String[] courseList){
         courseList[0] = birmingHamCourseOrder[0] + "\n\n" +
                "Career Preparedness [Grade 9-12]\n" +
                "Introduction to Animation and Visual Communication [Grade 10-11]\n" +
                "Animation Layout [Grade 10-11]\n" +
                "Animated Filmmaking [Grade 11-12]\n" +
                "Character Animation [Grade 11-12]\n" +
                "Storyboarding [Grade 11]\n" +
                "Advanced Animation Portfolio [Grade 11-12]\n" +
                "Business Finance [Grade 10]\n" +
                "Insurance Services [Grade 11 ]\n";

         courseList[1] = birmingHamCourseOrder[1] + "\n\n" +
                "English 9- Literature Focus: World Literature\n" +
                "English 9, Advanced\n" +
                "English 10- American Literature prior to 1900\n" +
                "English 10, Advanced\n" +
                "English 11- Literature Focus: American Literature\n" +
                "English AP Language and Composition 11\n" +
                "English 12- Literature Focus: British Literature\n" +
                "English AP Literature and Composition 12\n" +
                "Creative Writing\n" +
                "Public Speaking\n";

         courseList[2] = birmingHamCourseOrder[2] +
                "ESL I\n" +
                "ESL II\n" +
                "ESL III";

         courseList[3] = birmingHamCourseOrder[3] + "\n\n" +
                "Introduction to Concert Band I (one-half credit)\n" +
                "Concert Band II (one-half credit)\n" +
                "Concert Band III (one-half credit)\n" +
                "Concert Band IV (one-half credit)\n" +
                "Introduction to Marching Band I (one-half credit)\n" +
                "Marching Band II (one-half credit)\n" +
                "Marching Band III (one-half credit)\n" +
                "Marching Band IV (one-half credit)\n";

         courseList[4] = birmingHamCourseOrder[4] + "\n\n" +
                "French 1\n" +
                "French 2\n" +
                "French 3\n" +
                "French 4\n" +
                "French B, SLIB\n" +
                "Spanish 1\n" +
                "Spanish 2\n" +
                "Spanish 3\n" +
                "Spanish 4\n" +
                "Spanish Language, AP" +
                "Spanish B, SLIB";

         courseList[5] = birmingHamCourseOrder[5] + "\n\n" +
                "Algebra I\n" +
                "Algebra I, Advanced\n" +
                "Geometry\n" +
                "Geometry, Advanced\n" +
                "Algebraic Connections\n" +
                "Algebra II with Trigonometry\n" +
                "Algebra II\n" +
                "Algebra II with Trigonometry, Advanced\n" +
                "Precalculus Course\n" +
                "Advanced Placement Calculus Workshop\n";

         courseList[6] = birmingHamCourseOrder[6] + "\n\n" +
                "Biology\n" +
                "Advanced Biology\n" +
                "Physical Science\n" +
                "Chemistry\n" +
                "Advanced Chemistry\n" +
                "Advanced Placement Chemistry Workshop (Lab)\n" +
                "Advanced Placement Chemistry\n" +
                "Advanced Physics\n" +
                "Advanced Placement Physics Workshop (Lab)\n" ;

         courseList[7] = birmingHamCourseOrder[7] + "\n\n" +
                 "Social Studies 9- World History: 1500 to the Present\n" +
                 "Social Studies 9*Pre-AP World History: 1500 to the Present\n" +
                 "Social Studies 11-U. S. History II-Industrial Revolution to the Present\n" +
                 "Social Studies 11*AP U. S. History-11\n" +
                 "Advanced Placement (AP) U. S. History 11\n" +
                 "Social Studies 12 U. S. Government\n" +
                 "Social Studies 12 Economics\n" +
                 "Social Studies 12 *AP U. S. Government & Politics\n" +
                 "Advanced Placement (AP) U. S. Government & Politics\n";

    }

    public void showBirminghamCourses(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        Spinner spinner = (Spinner) findViewById(R.id.birmingham_coursetype_spinner);;
        Button submitButton = (Button) findViewById(R.id.submitCourseList);
        Button clearButton = (Button) findViewById(R.id.resetCourseList);

        int index = spinner.getSelectedItemPosition();
        textView.setText(birmingHamCourses[index]);
        spinner.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);
        clearButton.setVisibility(View.VISIBLE);

    }

    public void resetCourseTypes(View view) {
        TextView textView = (TextView) findViewById(R.id.textView);
        Spinner spinner = (Spinner) findViewById(R.id.birmingham_coursetype_spinner);
        Button submitButton = (Button) findViewById(R.id.submitCourseList);
        Button clearButton = (Button) findViewById(R.id.resetCourseList);

        textView.setText("");
        spinner.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        clearButton.setVisibility(View.INVISIBLE);

    }
}