package com.jonmid.segundoparcial.Views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jonmid.segundoparcial.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textViewname, textViewcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = (ImageView) findViewById(R.id.id_img_item_detail);
        textViewname = (TextView) findViewById(R.id.id_tv_namedetail);
        textViewcode = (TextView) findViewById(R.id.id_tv_codedetail);

        Bundle a = getIntent().getExtras();
        textViewname.setText(getText(a.getInt("name")));
        textViewcode.setText(getText(a.getInt("code")));
        Picasso





    }
}
