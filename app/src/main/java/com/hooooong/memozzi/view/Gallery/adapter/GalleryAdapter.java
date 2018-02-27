package com.hooooong.memozzi.view.Gallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hooooong.memozzi.R;
import com.hooooong.memozzi.data.photo.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2018-02-26.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.Holder> {

    private Context context;
    private List<Photo> photoList = new ArrayList<>();
    private ArrayList<Photo> selectPhotoList;
    private GalleryListener galleryListener;
    private boolean isMulti;
    private int maxCount;

    /**
     * 생성자
     *
     * @param context
     */
    public GalleryAdapter(Context context, boolean isMulti, int maxCount) {
        this.context = context;
        this.maxCount = maxCount;
        this.isMulti = isMulti;
        this.selectPhotoList = new ArrayList<>();
        this.galleryListener = (GalleryListener) context;
    }

    public void setPhotoList(List<Photo> photoList) {
        this.photoList = photoList;
        notifyDataSetChanged();
    }

    /**
     * PhotoList 반환
     *
     * @return
     */
    public List<Photo> getPhotoList() {
        return photoList;
    }

    /**
     * 선택된 PhotoList 반환
     *
     * @return
     */
    public ArrayList<Photo> getSelectPhotoList() {
        return selectPhotoList;
    }

    /**
     * 선택한 Photo 추가하기
     *
     * @param photo
     */
    public void addSelectPhotoList(Photo photo) {
        if (isMulti) {
            // 10 보다 작을 때
            if (selectPhotoList.size() < maxCount) {
                selectPhotoList.add(photo);
                notifyItemChanged(photoList.indexOf(photo));
                galleryListener.changeView(selectPhotoList.size());
            } else {
                galleryListener.selectError();
            }
        } else {
            if (selectPhotoList.size() < 1) {
                selectPhotoList.add(photo);
                notifyItemChanged(photoList.indexOf(photo));
            } else {
                Photo removePhoto = selectPhotoList.get(0);
                selectPhotoList.remove(removePhoto);
                notifyItemChanged(photoList.indexOf(removePhoto));
                selectPhotoList.add(photo);
                notifyItemChanged(photoList.indexOf(photo));
            }
            galleryListener.changeView(selectPhotoList.size());
        }
    }

    /**
     * 선택한 Photo 지우기
     *
     * @param photo
     */
    public void removeSelectPhotoList(Photo photo) {
        selectPhotoList.remove(photo);
        notifyItemChanged(photoList.indexOf(photo));
        for (Photo item : selectPhotoList) {
            notifyItemChanged(photoList.indexOf(item));
        }
        galleryListener.changeView(selectPhotoList.size());
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery, parent, false);
        return new Holder(view, isMulti);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Photo photo = photoList.get(position);
        holder.setImageView(photo.getImagePath());
        holder.setPosition(position);
        if (selectPhotoList.contains(photo)) {
            holder.setLayout(View.VISIBLE);
            if (isMulti) {
                holder.setTextNumber(selectPhotoList.indexOf(photo) + 1);
            }
        } else {
            holder.setLayout(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private int position;
        private ImageView imgPhoto;
        private FrameLayout layoutSelect;
        private TextView textNumber;

        Holder(View itemView, boolean isMulti) {
            super(itemView);

            imgPhoto = itemView.findViewById(R.id.iv_photo);
            layoutSelect = itemView.findViewById(R.id.layout_selected);
            textNumber = itemView.findViewById(R.id.tv_number);

            if (!isMulti) {
                textNumber.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (galleryListener != null) {
                        galleryListener.PhotoClick(position);
                    }
                }
            });
        }

        void setTextNumber(int number) {
            textNumber.setText(number + "");
        }

        void setImageView(String path) {
            Glide.with(context)
                    .load(path)
                    .centerCrop()
                    .into(imgPhoto);
        }

        void setLayout(int layout) {
            if (layout == View.VISIBLE) {
                layoutSelect.setVisibility(View.VISIBLE);
            } else {
                layoutSelect.setVisibility(View.INVISIBLE);
            }
        }

        void setPosition(int position) {
            this.position = position;
        }
    }

    public interface GalleryListener {
        /**
         * ImageView 클릭 시 발생하는 Listener
         *
         * @param position
         */
        void PhotoClick(int position);

        /**
         * ImageView 클릭 시 GalleryActivity View 변환하는 Listener
         */
        void changeView(int count);

        /**
         * ImageView 클릭 시 10개가 넘어갔을 때 Error 를 보여주는 Listener
         */
        void selectError();
    }

}
