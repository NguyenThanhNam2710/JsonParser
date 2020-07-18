package com.example.lab3_jsonparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab3_jsonparser.loader.GetLoader;
import com.example.lab3_jsonparser.model.Item;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Item> myDataset;

    private TextView textView;
    private RecyclerView rvList;
    private EditText edtSearch;
    private ImageView btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        rvList = (RecyclerView) findViewById(R.id.rvList);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String search = edtSearch.getText().toString().trim();
        if (search.isEmpty()) {
            edtSearch.setError("Chưa có từ khóa để tìm kiếm !");
        } else {
            String link = "http://dotplays.com/wp-json/wp/v2/search?search=" + edtSearch.getText().toString().trim() + "&_embed";
            GetLoader getLoader = new GetLoader(rvList, MainActivity.this);
            getLoader.execute(link);
        }
    }
}