package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPagerIdioms;
    private TextView tvIdiomCounter;
    private Button btnPrevious;
    private Button btnNext;
    private ArrayList<Idiom> idioms;
    private IdiomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("English Idioms");
        }

        // Initialize views
        viewPagerIdioms = findViewById(R.id.viewPagerIdioms);
        tvIdiomCounter = findViewById(R.id.tvIdiomCounter);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        // Load idioms data
        idioms = IdiomData.getIdioms();

        // Create adapter
        adapter = new IdiomPagerAdapter(this, idioms);
        viewPagerIdioms.setAdapter(adapter);

        // Set up bookmark click listener
        adapter.setOnBookmarkClickListener(new IdiomPagerAdapter.OnBookmarkClickListener() {
            @Override
            public void onBookmarkClick(int position) {
                toggleBookmark(position);
                adapter.notifyItemChanged(position);
            }
        });

        // Set up page change callback to update counter and button states
        viewPagerIdioms.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateCounter(position);
                updateNavigationButtons(position);
            }
        });

        // Set up button click listeners
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigatePrevious();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateNext();
            }
        });

        // Handle scrolling to specific position if coming from bookmarks
        int scrollToPosition = getIntent().getIntExtra("scroll_to_position", 0);
        viewPagerIdioms.setCurrentItem(scrollToPosition);

        // Initialize UI
        updateCounter(scrollToPosition);
        updateNavigationButtons(scrollToPosition);
    }

    private void navigatePrevious() {
        int currentItem = viewPagerIdioms.getCurrentItem();
        if (currentItem > 0) {
            viewPagerIdioms.setCurrentItem(currentItem - 1);
        }
    }

    private void navigateNext() {
        int currentItem = viewPagerIdioms.getCurrentItem();
        if (currentItem < idioms.size() - 1) {
            viewPagerIdioms.setCurrentItem(currentItem + 1);
        }
    }

    private void updateCounter(int position) {
        tvIdiomCounter.setText((position + 1) + " / " + idioms.size());
    }

    private void updateNavigationButtons(int position) {
        btnPrevious.setEnabled(position > 0);
        btnNext.setEnabled(position < idioms.size() - 1);
    }

    private void toggleBookmark(int position) {
        Idiom currentIdiom = idioms.get(position);
        currentIdiom.setBookmarked(!currentIdiom.isBookmarked());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_bookmarks) {
            Intent intent = new Intent(this, BookmarksActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the adapter to show bookmark updates
        adapter.notifyDataSetChanged();
    }
}