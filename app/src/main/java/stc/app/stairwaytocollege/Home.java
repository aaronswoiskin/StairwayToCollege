package stc.app.stairwaytocollege;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.app.StairwayToCollege.R;

import java.util.Dictionary;
import java.util.Hashtable;

public class Home extends AppCompatActivity {
    private static final String EXTRA_BASE = "stc.stairwaytocollege.";
    public static final String EXTRA_MESSAGE = "stc.stairwaytocollege.MESSAGE";
    public static final String EXTRA_TARGET_UNIV = "stc.stairwaytocollege.TARGET_UNIV";
    public static final String EXTRA_COURSES_TAKEN = "stc.stairwaytocollege.COURSES_TAKEN";
    public static final String EXTRA_SUBJECTS_TAKEN = "stc.stairwaytocollege.SUBJECTS_TAKEN";
    public static final String EXTRA_GRADES_RECEIVED = "stc.stairwaytocollege.GRADES_RECIEVED";
    public static final String SHARED_USER_LIST_KEY = "stc.stairwaytocollege.SHARED_USER_LIST_KEY";

    public static String curUsernameKey = null;

    public static Dictionary LETTER_TO_GPA = new Hashtable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        LETTER_TO_GPA.put("A+", "4.0");
        LETTER_TO_GPA.put("A" , "4.0");
        LETTER_TO_GPA.put("A-", "3.7");
        LETTER_TO_GPA.put("B+", "3.3");
        LETTER_TO_GPA.put("B" , "3.0");
        LETTER_TO_GPA.put("B-", "2.7");
        LETTER_TO_GPA.put("C+", "2.3");
        LETTER_TO_GPA.put("C" , "2.0");
        LETTER_TO_GPA.put("C-", "1.7");
        LETTER_TO_GPA.put("D+", "1.3");
        LETTER_TO_GPA.put("D" , "1.0");
        LETTER_TO_GPA.put("F" , "0.0");
        LETTER_TO_GPA.put(""  , ""   );

        String u = "default", p = "password";

        // add default user
        SharedPreferences userPref = getSharedPreferences(SHARED_USER_LIST_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        editor.putString(EXTRA_BASE + u, p);
        editor.apply();

        // add default user's classes
        SharedPreferences classList = getSharedPreferences(EXTRA_BASE + u, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = classList.edit();
        editor2.putString("bbbb", "C+");
        //editor2.clear();
        editor2.apply();

    }

    public void homeSubmit(View v) {
        EditText usernameText = findViewById(R.id.usernameText);
        String username = usernameText.getText().toString();
        curUsernameKey = EXTRA_BASE + username;

        EditText passwordText = findViewById(R.id.passwordText);
        String password = passwordText.getText().toString();

        SharedPreferences sharedPref = getSharedPreferences(SHARED_USER_LIST_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        Intent intent = new Intent(this, FirstTimeInfo.class);
        startActivity(intent);
        /*
        if (!sharedPref.contains(curUsernameKey)) {
            editor.putString(curUsernameKey, password);
            editor.apply();

            Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + username + "!", Toast.LENGTH_SHORT);
            toast.show();

            Intent intent = new Intent(this, FirstTimeInfo.class);
            startActivity(intent);
        } else {
            String savedPass = sharedPref.getString(curUsernameKey, "!!!!");

            if (savedPass.equals(password)) {
                Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + username + "!", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(this, ChooseSchool.class);
                startActivity(intent);
            } else {
                Toast toast = Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
*/
        //Toast toast = Toast.makeText(getApplicationContext(), "Welcome " + username + "!", Toast.LENGTH_SHORT);
        //toast.show();

        //Intent intent = new Intent(this, FirstTimeInfo.class);
        //startActivity(intent);
    }

    public void tempButton(View v) {
        Intent intent = new Intent(this, notify_college.class);
        startActivity(intent);
    }

    public void tempButton2(View v) {
        Intent intent = new Intent(this, GoalAnimations.class);
        startActivity(intent);
    }


}