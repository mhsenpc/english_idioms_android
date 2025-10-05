package sibpardazan.gharb.englishidioms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IdiomPagerAdapter extends RecyclerView.Adapter<IdiomPagerAdapter.IdiomViewHolder> {

    private ArrayList<Idiom> idioms;
    private Context context;
    private OnBookmarkClickListener bookmarkClickListener;

    public interface OnBookmarkClickListener {
        void onBookmarkClick(int position);
    }

    public IdiomPagerAdapter(Context context, ArrayList<Idiom> idioms) {
        this.context = context;
        this.idioms = idioms;
    }

    public void setOnBookmarkClickListener(OnBookmarkClickListener listener) {
        this.bookmarkClickListener = listener;
    }

    @NonNull
    @Override
    public IdiomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_idiom_pager, parent, false);
        return new IdiomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IdiomViewHolder holder, int position) {
        Idiom currentIdiom = idioms.get(position);

    
        holder.tvEnglishPhrase.setText(currentIdiom.getEnglishPhrase());
        holder.tvExample.setText(currentIdiom.getExample());
        holder.tvPersianTranslation.setText(currentIdiom.getPersianTranslation());
        holder.tvPersianDescription.setText(currentIdiom.getPersianDescription());

        // Update bookmark button appearance
        updateBookmarkButton(holder.btnBookmark, currentIdiom.isBookmarked());

        // Set bookmark click listener
        holder.btnBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bookmarkClickListener != null) {
                    bookmarkClickListener.onBookmarkClick(holder.getAdapterPosition());
                }
            }
        });
    }

    private void updateBookmarkButton(Button btnBookmark, boolean isBookmarked) {
        if (isBookmarked) {
            btnBookmark.setText("Remove Bookmark");
            btnBookmark.setBackgroundColor(btnBookmark.getContext().getResources().getColor(android.R.color.holo_red_dark));
        } else {
            btnBookmark.setText("Add Bookmark");
            btnBookmark.setBackgroundColor(btnBookmark.getContext().getResources().getColor(android.R.color.holo_blue_dark));
        }
    }

    @Override
    public int getItemCount() {
        return idioms.size();
    }

    public Idiom getIdiomAt(int position) {
        return idioms.get(position);
    }

    static class IdiomViewHolder extends RecyclerView.ViewHolder {
        TextView tvEnglishPhrase;
        TextView tvExample;
        TextView tvPersianTranslation;
        TextView tvPersianDescription;
        Button btnBookmark;

        public IdiomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEnglishPhrase = itemView.findViewById(R.id.tvEnglishPhrase);
            tvExample = itemView.findViewById(R.id.tvExample);
            tvPersianTranslation = itemView.findViewById(R.id.tvPersianTranslation);
            tvPersianDescription = itemView.findViewById(R.id.tvPersianDescription);
            btnBookmark = itemView.findViewById(R.id.btnBookmark);
        }
    }
}