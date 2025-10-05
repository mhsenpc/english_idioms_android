package sibpardazan.gharb.englishidioms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class IdiomDetailActivity extends AppCompatActivity {

    private TextView tvEnglishPhrase;
    private TextView tvExample;
    private TextView tvPersianTranslation;
    private TextView tvPersianDescription;
    private Button btnBookmark;

    private Idiom currentIdiom;
    private ArrayList<Idiom> allIdioms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom_detail);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Idiom Detail");
        }

        // Initialize views
        tvEnglishPhrase = findViewById(R.id.tvEnglishPhrase);
        tvExample = findViewById(R.id.tvExample);
        tvPersianTranslation = findViewById(R.id.tvPersianTranslation);
        tvPersianDescription = findViewById(R.id.tvPersianDescription);
        btnBookmark = findViewById(R.id.btnBookmark);

        // Get the idiom from intent
        currentIdiom = (Idiom) getIntent().getSerializableExtra("idiom");
        allIdioms = IdiomData.getIdioms();

        if (currentIdiom != null) {
            displayIdiomDetails();
            updateBookmarkButton();
        }

        // Set up bookmark button click listener
        btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleBookmark();
            }
        });
    }

    private void displayIdiomDetails() {
        tvEnglishPhrase.setText(currentIdiom.getEnglishPhrase());
        tvExample.setText(currentIdiom.getExample());
        tvPersianTranslation.setText(currentIdiom.getPersianTranslation());
        tvPersianDescription.setText(currentIdiom.getPersianDescription());
    }

    private void updateBookmarkButton() {
        if (currentIdiom.isBookmarked()) {
            btnBookmark.setText("Remove Bookmark");
            btnBookmark.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
        } else {
            btnBookmark.setText("Add Bookmark");
            btnBookmark.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_dark));
        }
    }

    private void toggleBookmark() {
        // Toggle bookmark status
        currentIdiom.setBookmarked(!currentIdiom.isBookmarked());

        // Update the corresponding idiom in the allIdioms list
        for (Idiom idiom : allIdioms) {
            if (idiom.getId() == currentIdiom.getId()) {
                idiom.setBookmarked(currentIdiom.isBookmarked());
                break;
            }
        }

        // Update button appearance
        updateBookmarkButton();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}