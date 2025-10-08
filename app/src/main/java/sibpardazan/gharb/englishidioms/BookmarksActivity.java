package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    private ListView listViewBookmarks;
    private TextView tvNoBookmarks;
    private ArrayList<Idiom> bookmarkedIdioms;
    private BookmarkAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Bookmarks");
        }

        // Initialize views
        listViewBookmarks = findViewById(R.id.listViewBookmarks);
        tvNoBookmarks = findViewById(R.id.tvNoBookmarks);

        // Load bookmarked idioms
        loadBookmarkedIdioms();

        // Set up adapter
        adapter = new BookmarkAdapter(this, bookmarkedIdioms);
        listViewBookmarks.setAdapter(adapter);

        // Set item click listener - navigate to main activity at specific position
        listViewBookmarks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Find the position of this idiom in the main list
                Idiom selectedIdiom = bookmarkedIdioms.get(position);
                int positionInMainList = findIdiomPositionInMainList(selectedIdiom);

                Intent intent = new Intent(BookmarksActivity.this, MainActivity.class);
                intent.putExtra("scroll_to_position", positionInMainList);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        // Show appropriate view based on bookmarks
        updateViewVisibility();
    }

    private int findIdiomPositionInMainList(Idiom targetIdiom) {
        ArrayList<Idiom> allIdioms = IdiomData.getIdioms();
        for (int i = 0; i < allIdioms.size(); i++) {
            if (allIdioms.get(i).getId() == targetIdiom.getId()) {
                return i;
            }
        }
        return 0; // Default to first position if not found
    }

    private void loadBookmarkedIdioms() {
        bookmarkedIdioms = new ArrayList<>();
        ArrayList<Idiom> allIdioms = IdiomData.getIdioms();

        for (Idiom idiom : allIdioms) {
            if (idiom.isBookmarked()) {
                bookmarkedIdioms.add(idiom);
            }
        }
    }

    private void updateViewVisibility() {
        if (bookmarkedIdioms.isEmpty()) {
            listViewBookmarks.setVisibility(View.GONE);
            tvNoBookmarks.setVisibility(View.VISIBLE);
        } else {
            listViewBookmarks.setVisibility(View.VISIBLE);
            tvNoBookmarks.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload bookmarks and refresh the list
        loadBookmarkedIdioms();
        adapter.notifyDataSetChanged();
        updateViewVisibility();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}