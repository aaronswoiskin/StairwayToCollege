package stc.app.stairwaytocollege;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.StairwayToCollege.R;

import java.util.ArrayList;

public class TuscaloosaRecommendCourses extends AppCompatActivity {

    private ArrayList<String> recommendedSubject0 = new ArrayList<String>();
    private ArrayList<String> recommendedSubject1 = new ArrayList<String>();
    private ArrayList<String> recommendedSubject2 = new ArrayList<String>();
    private ArrayList<String> recommendedSubject3 = new ArrayList<String>();
    private ArrayList<String> recommendedSubject4 = new ArrayList<String>();

    private int backgroundColor, buttonColor, buttonTextColor, submitColor, submitTextColor, textColor, titleColor;
    Button[] courseButtons;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuscaloosa_recommend_courses);

        courseButtons = new Button[] {
                findViewById(R.id.tuscaloosaCourseRec0),
                findViewById(R.id.tuscaloosaCourseRec1),
                findViewById(R.id.tuscaloosaCourseRec2),
                findViewById(R.id.tuscaloosaCourseRec3),
                findViewById(R.id.tuscaloosaCourseRec4),
        };

        Intent intent = getIntent();

        changeColors(intent.getStringExtra(Home.EXTRA_TARGET_UNIV));

        TextView t = findViewById(R.id.tuscaloosaRecommendTitle);
        t.setText(intent.getStringExtra(Home.EXTRA_TARGET_UNIV).trim());

        ImageView logo = findViewById(R.id.schoolLogoRecommend);
        int image = 0;
        switch(intent.getStringExtra(Home.EXTRA_TARGET_UNIV)) {
            case "University of Alabama":
                image = R.drawable.university_of_alabama_logo;
                break;
            case "Auburn University":
                image = R.drawable.auburn_university_logo;
                break;
        }
        if (image != 0) {
            logo.setImageResource(image);
        }
        String tabs = "";
        for (int i = 0; i < 38; i++) {
            tabs += "\t";
        }

        for (int i = 0; i < 5; i++) {
            recommendedSubject0.add("English " + (i+1) + "                " + tabs + "Credits: 5");
            recommendedSubject1.add("Social Science " + (i+1) + "  " + tabs + "Credits: 5");
            recommendedSubject2.add("Mathematics " + (i+1) + "     " + tabs + "Credits: 5");
            recommendedSubject3.add("Natural Science " + (i+1) + " " + tabs + "Credits: 5");
            recommendedSubject4.add("Foreign Language " + (i+1) + "" + tabs + "Credits: 5");
        }
    }

    public void showTuscaloosaCourseRecs(View v) {
        ArrayList<String> classList = null;
        LinearLayout layout = (LinearLayout) v.getParent();
        int index = layout.indexOfChild(v);

        switch (v.getId()) {
            case R.id.tuscaloosaCourseRec0:
                classList = recommendedSubject0;
                break;
            case R.id.tuscaloosaCourseRec1:
                classList = recommendedSubject1;
                break;
            case R.id.tuscaloosaCourseRec2:
                classList = recommendedSubject2;
                break;
            case R.id.tuscaloosaCourseRec3:
                classList = recommendedSubject3;
                break;
            case R.id.tuscaloosaCourseRec4:
                classList = recommendedSubject4;
                break;
        }


        if (index >= layout.getChildCount() - 1 || !(layout.getChildAt(index + 1) instanceof TextView)) {
            for (int i = 0; i < classList.size(); i++) {
                TextView text = new TextView(this);
                text.setText(classList.get(i));
                text.setTextColor(textColor);
                layout.addView(text, index + i + 1);
            }
        } else {
            for (int i = 0; i < classList.size(); i++) {
                layout.removeViewAt(index + 1);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void changeColors(String school) {
        ImageView logo = (ImageView) findViewById(R.id.schoolLogoRecommend);
        int backgroundImage = 0;

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
                titleColor = getResources().getColor(R.color.titleColorAuburn);
                backgroundColor = getResources().getColor(R.color.backgroundColorAuburn);
                buttonColor = getResources().getColor(R.color.buttonBackgroundColorAuburn);
                buttonTextColor =getResources().getColor(R.color.buttonTextColorAuburn);
                submitColor = getResources().getColor(R.color.buttonSubmitBackgroundColorAuburn);
                submitTextColor = getResources().getColor(R.color.buttonSubmitTextColorAuburn);
                break;
            default:
                return;
        }

        logo.setImageResource(backgroundImage);
        ((TextView) findViewById(R.id.tuscaloosaRecommendBackground)).setBackgroundColor(backgroundColor);

        for (int i = 0; i < courseButtons.length; i++) {
            courseButtons[i].setBackgroundColor(buttonColor);
            courseButtons[i].setTextColor(buttonTextColor);
        }

    }
}