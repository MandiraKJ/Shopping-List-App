package uk.ac.part2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.ac.part2.R;

import java.util.List;

public class ShoppingList extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartdata);
        getSupportActionBar().hide();

        textView =findViewById(R.id.rateview);
        getroomdata();
    }

    public void getroomdata()
    {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "part2_db").allowMainThreadQueries().build();
        ProductDao productDao = db.ProductDao();

        recyclerView =findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Product> products=productDao.getallproduct();

        shoppingListView adapter=new shoppingListView(products, textView);
        recyclerView.setAdapter(adapter);

        int sum=0,i;
        for(i=0;i< products.size();i++)
            sum=sum+(products.get(i).getPrice()*products.get(i).getQuantity());

        textView.setText("Total  "+sum);
    }
}