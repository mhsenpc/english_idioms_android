package sibpardazan.gharb.englishidioms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyrightedContentActivity extends AppCompatActivity {

    private ListView listViewContent;
    private ArrayAdapter<String> adapter;
    private List<String> contentUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyrighted_content);

        // Set up toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("محتوای دارای حق کپی‌رایت");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize views
        listViewContent = findViewById(R.id.listViewContent);

        // Initialize copyrighted content URLs
        contentUrls = new ArrayList<>(Arrays.asList(
            "https://stock.adobe.com/de/images/hit-the-nail-on-the-head-cartoon/62184421",
            "https://gemini.google.com/"
        ));

        // Create adapter
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contentUrls);
        listViewContent.setAdapter(adapter);

        // Set up click listener
        listViewContent.setOnItemClickListener((parent, view, position, id) -> {
            String url = contentUrls.get(position);
            openUrl(url);
        });
    }

    private void openUrl(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "خطا در باز کردن لینک", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}