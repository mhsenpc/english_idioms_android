package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPagerIdioms;
    private Button btnPrevious;
    private Button btnNext;
    private EditText searchEditText;
    private ArrayList<Idiom> allIdioms;
    private ArrayList<Idiom> filteredIdioms;
    private IdiomPagerAdapter adapter;
    private MenuItem bookmarkMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize IdiomData for SharedPreferences
        IdiomData.init(this);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("English Idioms");
        }

        // Initialize views
        viewPagerIdioms = findViewById(R.id.viewPagerIdioms);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);
        searchEditText = findViewById(R.id.searchEditText);

        // Load idioms data
        allIdioms = IdiomData.getIdioms();
        filteredIdioms = new ArrayList<>(allIdioms);

        // Create adapter
        adapter = new IdiomPagerAdapter(this, filteredIdioms);
        viewPagerIdioms.setAdapter(adapter);

        // Set up search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterIdioms(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Set up page change callback to update button states and bookmark icon
        viewPagerIdioms.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateNavigationButtons(position);
                updateBookmarkIcon(position);
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
        updateNavigationButtons(scrollToPosition);
        updateBookmarkIcon(scrollToPosition);
    }

    private void filterIdioms(String query) {
        filteredIdioms.clear();
        
        if (query.isEmpty()) {
            filteredIdioms.addAll(allIdioms);
        } else {
            String lowerCaseQuery = query.toLowerCase();
            for (Idiom idiom : allIdioms) {
                if (idiom.getName().toLowerCase().contains(lowerCaseQuery) ||
                    idiom.getMeaning().toLowerCase().contains(lowerCaseQuery)) {
                    filteredIdioms.add(idiom);
                }
            }
        }
        
        adapter.notifyDataSetChanged();
        
        // Reset to first item after filtering
        if (!filteredIdioms.isEmpty()) {
            viewPagerIdioms.setCurrentItem(0);
            updateNavigationButtons(0);
            updateBookmarkIcon(0);
        }
    }

    private void navigatePrevious() {
        int currentItem = viewPagerIdioms.getCurrentItem();
        if (currentItem > 0) {
            viewPagerIdioms.setCurrentItem(currentItem - 1);
        }
    }

    private void navigateNext() {
        int currentItem = viewPagerIdioms.getCurrentItem();
        if (currentItem < filteredIdioms.size() - 1) {
            viewPagerIdioms.setCurrentItem(currentItem + 1);
        }
    }

    private void updateNavigationButtons(int position) {
        boolean isAtFirst = position <= 0;
        boolean isAtLast = position >= filteredIdioms.size() - 1;

        btnPrevious.setEnabled(!isAtFirst);
        btnNext.setEnabled(!isAtLast);

        // Update button appearance to better indicate disabled state
        btnPrevious.setAlpha(isAtFirst ? 0.5f : 1.0f);
        btnNext.setAlpha(isAtLast ? 0.5f : 1.0f);
    }

    private void toggleBookmark(int position) {
        if (position >= 0 && position < filteredIdioms.size()) {
            Idiom currentIdiom = filteredIdioms.get(position);
            boolean newState = !currentIdiom.isBookmarked();
            currentIdiom.setBookmarked(newState);
            IdiomData.setBookmarked(currentIdiom.getId(), newState);
            updateBookmarkIcon(position);
        }
    }

    private void updateBookmarkIcon(int position) {
        if (bookmarkMenuItem != null && position >= 0 && position < filteredIdioms.size()) {
            Idiom currentIdiom = filteredIdioms.get(position);
            if (currentIdiom.isBookmarked()) {
                bookmarkMenuItem.setIcon(android.R.drawable.star_on);
                bookmarkMenuItem.setTitle("Remove Bookmark");
            } else {
                bookmarkMenuItem.setIcon(android.R.drawable.star_off);
                bookmarkMenuItem.setTitle("Bookmark");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        bookmarkMenuItem = menu.findItem(R.id.action_bookmark_current);
        updateBookmarkIcon(viewPagerIdioms.getCurrentItem());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_bookmark_current) {
            toggleBookmark(viewPagerIdioms.getCurrentItem());
            return true;
        } else if (id == R.id.action_bookmarks) {
            Intent intent = new Intent(this, BookmarksActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_copyrighted_content) {
            Intent intent = new Intent(this, CopyrightedContentActivity.class);
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
