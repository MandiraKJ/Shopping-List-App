package uk.ac.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ac.part2.R;

public class MainActivity extends AppCompatActivity
{
    //creating variables
    Button button1, button2;

    EditText input1, input2, input3, input4;

    TextView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        input1 = findViewById(R.id.input1);
        input2 = findViewById(R.id.input2);
        input3 = findViewById(R.id.input3);
        input4 = findViewById(R.id.input4);

        view = findViewById(R.id.table1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              AppDatabase db= Room.databaseBuilder(getApplicationContext(),AppDatabase.class,"part2_db").allowMainThreadQueries().build();
              ProductDao productDao=db.ProductDao();
              Boolean check=productDao.is_exist(Integer.parseInt(input1.getText().toString()));
               if(check==false)
               {
                  int pid=Integer.parseInt(input1.getText().toString());
                  String name= input2.getText().toString();
                  int price=Integer.parseInt(input3.getText().toString());
                  int quantity =Integer.parseInt(input4.getText().toString());
                  productDao.insertProduct(new Product(pid,name,price, quantity));

                  input1.setText("");

                  input2.setText("");

                  input3.setText("");

                  input4.setText("");

                  MainActivity.this.view.setText("Product Inserted Successfully");
               }
               else
               {
                   input1.setText("");

                   input2.setText("");

                   input3.setText("");

                   input4.setText("");

                   MainActivity.this.view.setText("Product Already in Cart");
               }
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ShoppingList.class));

            }
        });
    }
}