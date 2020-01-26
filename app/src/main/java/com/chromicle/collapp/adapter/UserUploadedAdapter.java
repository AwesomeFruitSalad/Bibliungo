package com.chromicle.collapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chromicle.collapp.R;
import com.chromicle.collapp.utils.UserUploaded;
import com.google.android.gms.common.data.DataHolder;

import java.util.List;

/**
 * @author by Chromicle (ajayprabhakar369@gmail.com)
 * @since 1/26/2020
 */
public class UserUploadedAdapter extends RecyclerView.Adapter<UserUploadedAdapter.UserUplodedViewHolder> {

    private Context context;
    private List<UserUploaded> userUploadedList;

    public UserUploadedAdapter(Context context, List<UserUploaded> userUploadedList) {
        this.context = context;
        this.userUploadedList = userUploadedList;
    }

    @NonNull
    @Override
    public UserUplodedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserUplodedViewHolder(
                LayoutInflater.from(context).inflate(R.layout.individual_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserUplodedViewHolder holder, int position) {
        UserUploaded userUploaded = userUploadedList.get(position);

        holder.bookName.setText(userUploaded.getBookName());
        holder.bookDes.setText(userUploaded.getBookDes());
        holder.bookAmount.setText(userUploaded.getBookAmount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNumber = "9515253369";
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL); // Action for what intent called for
                intent.setData(Uri.parse("tel: " + mobileNumber)); // Data with intent respective action on intent
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userUploadedList.size();
    }

    public void updateList(List<UserUploaded> list){
        userUploadedList = list;
        notifyDataSetChanged();
    }

    class UserUplodedViewHolder extends RecyclerView.ViewHolder {

        TextView bookName;
        TextView bookDes;
        TextView bookAmount;

        UserUplodedViewHolder(@NonNull View itemView) {
            super(itemView);

            bookName = itemView.findViewById(R.id.listBookName);
            bookDes = itemView.findViewById(R.id.listBookDes);
            bookAmount = itemView.findViewById(R.id.listBookAmount);
        }
    }
}