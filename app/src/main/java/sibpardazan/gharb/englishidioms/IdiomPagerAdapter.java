package sibpardazan.gharb.englishidioms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class IdiomPagerAdapter extends RecyclerView.Adapter<IdiomPagerAdapter.IdiomViewHolder> {

    private ArrayList<Idiom> idioms;
    private Context context;

    public IdiomPagerAdapter(Context context, ArrayList<Idiom> idioms) {
        this.context = context;
        this.idioms = idioms;
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

        // Load image with error handling
        loadImage(holder.ivIdiomImage, currentIdiom.getImageName());
    }

    private void loadImage(ImageView imageView, String imageName) {
        if (imageName == null || imageName.trim().isEmpty()) {
            // Hide image view if no image name provided
            imageView.setVisibility(View.GONE);
            return;
        }

        imageView.setVisibility(View.VISIBLE);

        try {
            // Get resource ID for the image name
            int resourceId = context.getResources().getIdentifier(
                imageName,
                "drawable",
                context.getPackageName()
            );

            if (resourceId != 0) {
                // Resource found, load the image
                imageView.setImageResource(resourceId);
                imageView.setBackgroundColor(0x00000000); // Transparent background
            } else {
                // Resource not found, hide the image view
                imageView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            // Any error loading image, hide the image view
            imageView.setVisibility(View.GONE);
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
        ImageView ivIdiomImage;

        public IdiomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEnglishPhrase = itemView.findViewById(R.id.tvEnglishPhrase);
            tvExample = itemView.findViewById(R.id.tvExample);
            tvPersianTranslation = itemView.findViewById(R.id.tvPersianTranslation);
            tvPersianDescription = itemView.findViewById(R.id.tvPersianDescription);
            ivIdiomImage = itemView.findViewById(R.id.ivIdiomImage);
        }
    }
}