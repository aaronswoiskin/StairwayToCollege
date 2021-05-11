package stc.app.stairwaytocollege;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.app.StairwayToCollege.R;

import java.util.ArrayList;

public class TuscaloosaList extends AppCompatActivity {

    private String[] tuscaloosaSubjects = new String[]{"English",
                                                        "Social Studies",
                                                        "Mathematics",
                                                        "Science",
                                                        "Foreign Language"};
/*
    String[] englishReqs = new String[]{"English 9 (S1)", "English 9 (S2)", "English 10 (S1)", "English 10 (S2)", "English 11 (S1)", "English 11 (S2)", "English 12 (S1)", "English 12 (S2)" };
    String[] socialScienceReqs = new String[]{"World History"};
    String[] mathematicsReqs = new String[]{"Algebra I (S1)", "Algebra I (S2)", "Algebra II (S1)", "Algebra II (S2)", "Geometry"};
    String[] scienceReqs = new String[]{"Chemistry", "Physics"};
    String[] languageReqs = new String[]{"Spanish 1", "Spanish 2"};
*/

/*
    String[] englishReqs = {"English 9", "English 10", "English 11",  "English 12"};
    String[] socialScienceReqs = {"World History"};
    String[] mathematicsReqs = {"Algebra I", "Algebra II", "Geometry"};
    String[] scienceReqs = {"Chemistry", "Physics"};
    String[] languageReqs = {"Spanish 1", "Spanish 2"};
*/
    String[] englishReqs = {"English 1", "English 2", "English 3", "English 4" };
    String[] socialScienceReqs = {"Social Studies 1", "Social Studies 2", "Social Studies 3"};
    String[] mathematicsReqs = {"Math 1", "Math 2", "Math 3" };
    String[] scienceReqs = {"Science 1", "Science 2"};

    //String[][] subjectReqs = new String[][]{englishReqs, socialScienceReqs, mathematicsReqs, scienceReqs};
    String[][] subjectReqs = new String[][]{{}, {}, {}, {}};
    TextView[] titleTexts;
    TextView[] subjectTexts;

//    private int[] tuscaloosaCourseUnitsRequired = new int[]{4,3,3,3,3,3};
    private int[] tuscaloosaCourseUnitsRequired = new int[]{2,2,2,2,2,2};
    // temporary requirements

    private String checkMark = "✓";

    // attaboy animation
    private TextView attaAnimationTitle, attaAnimationText;
    private ImageView attaAnimationLogo;
    private TextView attaAnimationBackground;
    final Handler handler = new Handler();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuscaloosa_list);

        titleTexts = new TextView[]{
                findViewById(R.id.tuscaloosaSubjectTitle0),
                findViewById(R.id.tuscaloosaSubjectTitle1),
                findViewById(R.id.tuscaloosaSubjectTitle2),
                findViewById(R.id.tuscaloosaSubjectTitle3),
                //findViewById(R.id.tuscaloosaSubjectTitle4),
        };

        subjectTexts = new TextView[]{
                findViewById(R.id.tuscaloosaSubject0),
                findViewById(R.id.tuscaloosaSubject1),
                findViewById(R.id.tuscaloosaSubject2),
                findViewById(R.id.tuscaloosaSubject3),
                //findViewById(R.id.tuscaloosaSubject4)
        };

        Intent intent = getIntent();
        changeColors(intent.getStringExtra(Home.EXTRA_TARGET_UNIV));
        setAttaAnimation(intent.getStringExtra(Home.EXTRA_TARGET_UNIV));

        TextView t = findViewById(R.id.tuscaloosaListTitle);
        t.setText(intent.getStringExtra(Home.EXTRA_TARGET_UNIV));

        ArrayList<String> coursesTaken = intent.getStringArrayListExtra(Home.EXTRA_COURSES_TAKEN);
        ArrayList<String> subjectsTaken = intent.getStringArrayListExtra(Home.EXTRA_SUBJECTS_TAKEN);
        ArrayList<String> gradesReceived = intent.getStringArrayListExtra(Home.EXTRA_GRADES_RECEIVED);

        // calculate units per subject
        float[] unitsTaken = new float[tuscaloosaSubjects.length];
        for (float i : unitsTaken) {i = 0;}

        for (int i = 0; i < subjectsTaken.size(); i++) {
            for (int j = 0; j < tuscaloosaSubjects.length; j++) {
                if (subjectsTaken.get(i).equals(tuscaloosaSubjects[j])) {
                    unitsTaken[j]+=1;
                }
            }
        }

        // calculate/display GPA
        float total = 0, count = 0;
        for (int i = 0; i < gradesReceived.size(); i++) {
            String grade = gradesReceived.get(i);
            if (Home.LETTER_TO_GPA.get(grade) != null) {
                String points = (String) Home.LETTER_TO_GPA.get(grade);
                Log.e("", points);
                if (points != "") {
                    total += Float.parseFloat(points);
                    count++;
                    if (checkIfCourseAdvanced(coursesTaken.get(i))) {
                        total++;
                    }
                }
            }
        }
        TextView gpaText = (TextView) findViewById(R.id.tuscaloosaGPA);
        gpaText.setText(count > 0 ? String.format("GPA: %.2f", total/count) : "GPA: N/A");

        // write remaining requirements
        boolean allReqsMet = true;
        for (int i = 0; i < subjectTexts.length; i++) {
            TextView title = titleTexts[i];
            TextView text = subjectTexts[i];
            String output = "";

            boolean reqMissing = false;
            boolean unitsMet = unitsTaken[i] >= tuscaloosaCourseUnitsRequired[i];
            String icon = unitsMet ? checkMark + " " : "X ";
            title.setText(icon + tuscaloosaSubjects[i] + " - " + Integer.toString((int)unitsTaken[i]) + "/" +  Integer.toString(tuscaloosaCourseUnitsRequired[i]) + " units");
            for (String req : subjectReqs[i]){
                if (coursesTaken.indexOf(req) < 0) {
                    output += "    • " + req + "\n";
                    reqMissing = true;
                }
            }
            if (reqMissing || !unitsMet) {
                allReqsMet = false;
            }
            //text.setTextColor((!reqMissing && unitsMet) ? Color.rgb(0, 204, 0) : Color.rgb(204, 0, 0));
            text.setText(output);
        }
        if (allReqsMet) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    attaAnimate();
                }
            }, 2000);
        }

        String output = "";
        String icon = "";
        for (int i = 0; i < tuscaloosaSubjects.length; i++) {
            icon = unitsTaken[i] >= tuscaloosaCourseUnitsRequired[i] ? checkMark + " " : "X ";
            output += icon + tuscaloosaSubjects[i] + " - " + Float.toString(unitsTaken[i]) + "/" +  Integer.toString(tuscaloosaCourseUnitsRequired[i]) + ".0 units\n\n";
        }

        //TextView text = findViewById(R.id.tuscaloosaStatus);
        //text.setText(output);
    }

    public void recTuscaloosaCourses(View v) {
        Intent intent = new Intent(this, TuscaloosaRecommendCourses.class);
        intent.putExtra(Home.EXTRA_TARGET_UNIV, getIntent().getStringExtra(Home.EXTRA_TARGET_UNIV));
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void changeColors(String school) {
        ImageView logo = (ImageView) findViewById(R.id.schoolLogoReqs);
        int backgroundImage = 0;
        int backgroundColor, buttonColor, buttonTextColor, submitColor, submitTextColor, textColor, titleColor;
        switch(school) {
            case "University of Alabama":
                backgroundImage = R.drawable.university_of_alabama_logo;
                textColor = getResources().getColor(R.color.textColorAlabama);
                titleColor = getResources().getColor(R.color.titleColorAlabama);
                backgroundColor = getResources().getColor(R.color.backgroundColorAlabama);
                buttonColor = getResources().getColor(R.color.buttonBackgroundColorAlabama);
                buttonTextColor = getResources().getColor(R.color.buttonTextColorAlabama);
                submitColor = getResources().getColor(R.color.buttonSubmitBackgroundColorAlabama);
                submitTextColor = getResources().getColor(R.color.buttonSubmitTextColorAlabama);
                break;
            case "Auburn University":
                backgroundImage = R.drawable.auburn_university_logo;
                textColor = getResources().getColor(R.color.textColorAuburn);
                backgroundColor = getResources().getColor(R.color.backgroundColorAuburn);
                titleColor = getResources().getColor(R.color.titleColorAuburn);
                buttonColor = getResources().getColor(R.color.buttonBackgroundColorAuburn);
                buttonTextColor = getResources().getColor(R.color.buttonTextColorAuburn);
                submitColor = getResources().getColor(R.color.buttonSubmitBackgroundColorAuburn);
                submitTextColor = getResources().getColor(R.color.buttonSubmitTextColorAuburn);
                break;
            default:
                return;
        }

        logo.setImageResource(backgroundImage);
        ((TextView) findViewById(R.id.tuscaloosaListBackground)).setBackgroundColor(backgroundColor);

        ((TextView) findViewById(R.id.tuscaloosaListTitle)).setTextColor(titleColor);
        ((TextView) findViewById(R.id.tuscaloosaListTitle2)).setTextColor(titleColor);
        ((TextView) findViewById(R.id.tuscaloosaGPA)).setTextColor(titleColor);

        for (int i = 0; i < titleTexts.length; i++) {
            titleTexts[i].setTextColor(textColor);
            subjectTexts[i].setTextColor(textColor);
        }

        Button button = findViewById(R.id.tuscaloosaRecommendButton);
        button.setBackgroundColor(submitColor);
        button.setTextColor(submitTextColor);
    }

    private void setAttaAnimation(String school) {
        switch(school) {
            case "Auburn University":
                attaAnimationTitle = findViewById(R.id.auburnAnimationTitle);
                attaAnimationText = findViewById(R.id.auburnAnimationText);
                attaAnimationLogo = findViewById(R.id.auburnAnimationLogo);
                attaAnimationBackground = findViewById(R.id.animationBackground);

                attaAnimationBackground.setBackgroundColor(getResources().getColor(R.color.backgroundColorAuburn));
                break;

            case "University of Alabama":
                attaAnimationTitle = findViewById(R.id.alabamaAnimationTitle);
                attaAnimationText = findViewById(R.id.alabamaAnimationText);
                attaAnimationLogo = findViewById(R.id.alababamaAnimationLogo);
                attaAnimationBackground = findViewById(R.id.animationBackground);

                attaAnimationBackground.setBackgroundColor(getResources().getColor(R.color.backgroundColorAlabama));
                break;
        }
    }

    private void attaAnimate() {
        attaAnimationBackground.setVisibility(View.VISIBLE);
        attaAnimationTitle.setVisibility(View.VISIBLE);
        attaAnimationText.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                attaAnimationLogo.setVisibility(View.VISIBLE);
            }
        }, 500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clearTexts();
            }
        }, 5000);
    }

    private void clearTexts() {
        attaAnimationBackground.setVisibility(View.GONE);

        attaAnimationTitle.setVisibility(View.GONE);
        attaAnimationText.setVisibility(View.GONE);

        attaAnimationLogo.setVisibility(View.GONE);
    }

    public static boolean checkIfCourseAdvanced(String course){
        return (course.substring(0,3).equals("AP ") ||
                course.contains(" AP ") ||
                course.substring(0,3).equals("IB ") ||
                course.contains(" IB ") ||
                course.contains("Advanced"));
    }
}