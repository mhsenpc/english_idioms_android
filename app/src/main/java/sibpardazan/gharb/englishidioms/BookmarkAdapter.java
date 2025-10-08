package sibpardazan.gharb.englishidioms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BookmarkAdapter extends ArrayAdapter<Idiom> {

    private Context context;
    private ArrayList<Idiom> bookmarkedIdioms;
    private LayoutInflater inflater;

    public BookmarkAdapter(@NonNull Context context, @NonNull ArrayList<Idiom> bookmarkedIdioms) {
        super(context, 0, bookmarkedIdioms);
        this.context = context;
        this.bookmarkedIdioms = bookmarkedIdioms;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_bookmark, parent, false);
            holder = new ViewHolder();
            holder.ivIdiomIcon = convertView.findViewById(R.id.ivIdiomIcon);
            holder.tvIdiomText = convertView.findViewById(R.id.tvIdiomText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Idiom currentIdiom = bookmarkedIdioms.get(position);

        // Set the idiom text
        holder.tvIdiomText.setText(currentIdiom.getEnglishPhrase());

        // Set the idiom image
        String imageName = currentIdiom.getImageName();
        if (imageName != null && !imageName.isEmpty()) {
            // Get the resource ID for the image
            int imageResourceId = context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName()
            );

            if (imageResourceId != 0) {
                holder.ivIdiomIcon.setImageResource(imageResourceId);
            } else {
                // Fallback to a default icon if the image is not found
                holder.ivIdiomIcon.setImageResource(android.R.drawable.ic_menu_gallery);
            }
        } else {
            // Fallback to a default icon if no image name is provided
            holder.ivIdiomIcon.setImageResource(android.R.drawable.ic_menu_gallery);
        }

        return convertView;
    }

    private static class ViewHolder {
        ImageView ivIdiomIcon;
        TextView tvIdiomText;
    }
}