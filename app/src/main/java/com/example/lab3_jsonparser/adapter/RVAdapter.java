package com.example.lab3_jsonparser.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab3_jsonparser.R;
import com.example.lab3_jsonparser.model.Item;
import com.example.lab3_jsonparser.model.ListData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<ListData> listData;
    private Context context;

    public RVAdapter(List<ListData> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.my_listview, parent, false);

        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ListData item = listData.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvDate.setText(item.getDate());
        holder.tvView.setText(HtmlCompat.fromHtml(item.getExcerpt(), HtmlCompat.FROM_HTML_MODE_LEGACY));

        try {
            String url = item.getContent().split("<img src=\"")[1].split("\"")[0];
            Picasso.get().load(url).into(holder.imageView);
        } catch (Exception e) {

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context,android.R.style.Theme_Light_NoTitleBar_Fullscreen);
                alertDialog.setView(R.layout.dialog_showdetail);
                final AlertDialog dialog = alertDialog.show();

                WebView tvC;
                tvC = (WebView) dialog.findViewById(R.id.tvC);
                tvC.loadUrl(item.getLinkUrl());


            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private ImageView imageView;
        private TextView tvDate;
        private TextView tvView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvView = (TextView) itemView.findViewById(R.id.tvView);
        }
    }
}
