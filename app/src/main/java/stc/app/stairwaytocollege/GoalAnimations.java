package stc.app.stairwaytocollege;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.StairwayToCollege.R;

public class GoalAnimations extends AppCompatActivity {

    private TextView alabamaTitle, alabamaText, auburnTitle,  auburnText;
    private ImageView alabamaLogo, auburnLogo;
    private ConstraintLayout animationConstraint;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_animations);

        alabamaTitle = findViewById(R.id.alabamaAnimationTitleDemo);
        auburnTitle  = findViewById(R.id.auburnAnimationTitleDemo);

        alabamaText = findViewById(R.id.alabamaAnimationTextDemo);
        auburnText = findViewById(R.id.auburnAnimationTextDemo);

        alabamaLogo = findViewById(R.id.alababamaAnimationLogoDemo);
        auburnLogo = findViewById(R.id.auburnAnimationLogoDemo);

        animationConstraint = findViewById(R.id.successAnimationsConstraint);

        clearTexts(null);
    }

    public void animateAlabama(View v) {
        clearTexts(null);
        animationConstraint.setBackgroundColor(getResources().getColor(R.color.backgroundColorAlabama));
        alabamaTitle.setVisibility(View.VISIBLE);
        alabamaText.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alabamaLogo.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    public void animateAuburn(View v) {
        clearTexts(null);
        animationConstraint.setBackgroundColor(getResources().getColor(R.color.backgroundColorAuburn));
        auburnTitle.setVisibility(View.VISIBLE);
        auburnText.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                auburnLogo.setVisibility(View.VISIBLE);
            }
        }, 500);
    }

    public void clearTexts(View v) {
        animationConstraint.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

        auburnTitle.setVisibility(View.GONE);
        auburnText.setVisibility(View.GONE);

        alabamaTitle.setVisibility(View.GONE);
        alabamaText.setVisibility(View.GONE);

        alabamaLogo.setVisibility(View.GONE);
        auburnLogo.setVisibility(View.GONE);
    }
}