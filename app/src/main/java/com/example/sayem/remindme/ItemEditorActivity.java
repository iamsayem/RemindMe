package com.example.sayem.remindme;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class ItemEditorActivity extends Activity {

    EditText itemEditorEditText;
    Button addDataButton;
    String catchGroupPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_editor);
        this.setFinishOnTouchOutside(false);
        itemEditorEditText = (EditText) findViewById(R.id.itemEditorEditText);
        addDataButton = (Button) findViewById(R.id.addDataButton);
        final String group_position = getIntent().getStringExtra("GROUP_POSITION");
        catchGroupPosition = group_position;
        setTitle(group_position);
        addDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = true;

                ItemListDatabase itemListDatabase = new ItemListDatabase(ItemEditorActivity.this);

                String itemEditorString = itemEditorEditText.getText().toString();
                if(itemEditorString.matches("")){
                    Toast.makeText(getApplicationContext(), "Item cannot be empty!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    ItemNameClass itemNameClass = new ItemNameClass(itemEditorString);
                    try {
                        if (group_position.equals("Pharmacy")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Super Shop")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Stationery Shop")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Market")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Hotel")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Hardware Shop")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Computer Accessories Shop")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                            itemEditorEditText.setText("");
                        } else if (group_position.equals("Others")) {
                            itemListDatabase.insertData(itemNameClass, group_position);
                        }
                    }catch (SQLiteException e){
                        flag = false;
                        String error = e.toString();
                        Toast.makeText(ItemEditorActivity.this, error, Toast.LENGTH_SHORT).show();
                    }finally {
                        if (flag){
                            Toast.makeText(getApplicationContext(), "Added to " + group_position, Toast.LENGTH_SHORT).show();
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onBackPressed() {

        if (catchGroupPosition != null){
            Intent intent = new Intent();
            intent.putExtra("GROUP_NAME", catchGroupPosition);
            setResult(RESULT_OK, intent);
            finish();
        }

        else{
            Intent intent = new Intent();
            intent.putExtra("GROUP_NAME", "");
            setResult(RESULT_OK, intent);
            finish();
        }
        super.onBackPressed();
    }

}
