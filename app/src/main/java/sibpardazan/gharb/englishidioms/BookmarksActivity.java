package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BookmarksActivity extends AppCompatActivity {

    private ListView listViewBookmarks;
    private TextView tvNoBookmarks;
    private ArrayList<Idiom> bookmarkedIdioms;
    private ArrayAdapter<Idiom> adapter;

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
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, bookmarkedIdioms);
        listViewBookmarks.setAdapter(adapter);

        // Set item click listener
        listViewBookmarks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Idiom selectedIdiom = bookmarkedIdioms.get(position);
                Intent intent = new Intent(BookmarksActivity.this, IdiomDetailActivity.class);
                intent.putExtra("idiom", selectedIdiom);
                startActivity(intent);
            }
        });

        // Show appropriate view based on bookmarks
        updateViewVisibility();
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
        adapter.clear();
        adapter.addAll(bookmarkedIdioms);
        adapter.notifyDataSetChanged();
        updateViewVisibility();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}