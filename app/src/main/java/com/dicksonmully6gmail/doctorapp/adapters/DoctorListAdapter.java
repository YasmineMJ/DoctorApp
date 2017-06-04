package com.dicksonmully6gmail.doctorapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicksonmully6gmail.doctorapp.R;
import com.dicksonmully6gmail.doctorapp.models.Doctor;
import com.dicksonmully6gmail.doctorapp.ui.DoctorDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dickson on 6/2/17.
 */


public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private static final int MAX_WIDTH = 150;
    private static final int MAX_HEIGHT = 150;
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }
//    doctor view holder class
    public class DoctorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
        @Bind(R.id.doctorNameTextView) TextView mNameTextView;
        @Bind(R.id.specialtyTextView) TextView mSpeciltyTextView;
        @Bind(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        //        creates an intent to navigate to our RestaurantDetailActivity
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, DoctorDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("doctors", Parcels.wrap(mDoctors));
            mContext.startActivity(intent);
        }

        public void bindDoctor(Doctor restaurant) {
            Picasso.with(mContext)
                    .load(restaurant.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mDoctorImageView);
            mNameTextView.setText(restaurant.getName());
            mSpeciltyTextView.setText(restaurant.getSpecialties().get(0));
            mRatingTextView.setText("Rating: " + restaurant.getRating() + "/5");
        }


    }
}