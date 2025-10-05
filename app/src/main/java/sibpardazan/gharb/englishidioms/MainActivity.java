package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listViewIdioms;
    private ArrayList<Idiom> idioms;
    private ArrayAdapter<Idiom> adapter;

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
        listViewIdioms = findViewById(R.id.listViewIdioms);

        // Load idioms data
        idioms = IdiomData.getIdioms();

        // Create adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, idioms);
        listViewIdioms.setAdapter(adapter);

        // Set item click listener
        listViewIdioms.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Idiom selectedIdiom = idioms.get(position);
                Intent intent = new Intent(MainActivity.this, IdiomDetailActivity.class);
                intent.putExtra("idiom", selectedIdiom);
                startActivity(intent);
            }
        });
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
        // Refresh the list to show bookmark updates
        adapter.notifyDataSetChanged();
    }
}