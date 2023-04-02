package com.example.picselector;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    EditText pictureName,rating,comment;
    Button insert, update, delete, view;

    DBAdapter DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        pictureName = findViewById(R.id.editText_name);
        rating = findViewById(R.id.editText_rating);
        comment = findViewById(R.id.editText_comment);


        insert = findViewById(R.id.btn_add);
        update = findViewById(R.id.btn_update);
        delete = findViewById(R.id.btn_delete);
        view = findViewById(R.id.btn_view);

        DB = new DBAdapter(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = pictureName.getText().toString();
                String ratingTXT = rating.getText().toString();
                String commentTXT = comment.getText().toString();

                if (nameTXT.matches("") || ratingTXT.matches("")) {
                    Toast.makeText(RatingActivity.this, "You did not enter picture details", Toast.LENGTH_SHORT).show();
                } else {

                    Boolean checkInsertData = DB.insertRating(nameTXT, ratingTXT, commentTXT);
                    if (checkInsertData == true)
                        Toast.makeText(RatingActivity.this, "New Rating Inserted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(RatingActivity.this, "New Rating Not Inserted", Toast.LENGTH_SHORT).show();

                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = pictureName.getText().toString();
                String ratingTXT = rating.getText().toString();
                String commentTXT = comment.getText().toString();

                Boolean checkUpdateData = DB.updateRating(nameTXT,ratingTXT,commentTXT);
                if(checkUpdateData==true)
                    Toast.makeText(RatingActivity.this, "Rating Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RatingActivity.this, "Rating Not Updated", Toast.LENGTH_SHORT).show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = pictureName.getText().toString();

                Boolean checkDeleteData = DB.deleteRating(nameTXT);
                if(checkDeleteData==true)
                    Toast.makeText(RatingActivity.this, "Rating Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(RatingActivity.this, "Rating Not Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getRatingData();
                if(res.getCount()==0){
                    Toast.makeText(RatingActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Picture Name: "+res.getString(0)+"\n");
                    buffer.append("Rating: "+res.getString(1)+" stars"+"\n");
                    buffer.append("Comment: "+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(RatingActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Picture Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}
