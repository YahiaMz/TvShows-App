package com.aplication.moviesapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.aplication.moviesapp.R;
import com.aplication.moviesapp.databinding.ImageSliderItemContainerBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {

   private LayoutInflater layoutInflater;
   private  ArrayList<String> imageUrls ;

    public ImageSliderAdapter(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public ArrayList<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(ArrayList<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) this.layoutInflater = LayoutInflater.from(parent.getContext());
        ImageSliderItemContainerBinding imageSliderItemContainerBinding = DataBindingUtil.inflate(this.layoutInflater , R.layout.image_slider_item_container , parent , false);
        return new ImageSliderViewHolder(imageSliderItemContainerBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
         holder.bind(this.imageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return this.imageUrls.size();
    }

    public class ImageSliderViewHolder extends RecyclerView.ViewHolder {

        private ImageSliderItemContainerBinding imageSliderItemContainerBinding;
        public ImageSliderViewHolder(@NonNull ImageSliderItemContainerBinding itemView) {
            super(itemView.getRoot());
            this.imageSliderItemContainerBinding = itemView;
        }

        public  void bind (String ImageURl  ) {
           Picasso.get().load(ImageURl).into( imageSliderItemContainerBinding.imageViewOfImageSlier );
        }

    }

}
