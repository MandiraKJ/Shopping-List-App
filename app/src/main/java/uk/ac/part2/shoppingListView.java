package uk.ac.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.ac.part2.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class shoppingListView extends RecyclerView.Adapter<shoppingListView.myviewholder>
{
    List<Product> products;
    TextView textView;
    public shoppingListView(List<Product> products, TextView textView) {
        this.products = products;
        this.textView = textView;
    }

    @NonNull
    @NotNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign,parent,false);
        return new myviewholder(view);
    }


    //Mapping user input into shopping list db



    @Override
    public void onBindViewHolder(@NonNull @NotNull shoppingListView.myviewholder holder, int position) {

       holder.recycle_id.setText(String.valueOf(products.get(position).getProductID()));

       holder.recycle_name.setText(products.get(position).getName());

       holder.recycle_price.setText(String.valueOf(products.get(position).getPrice()));

       holder.recycle_quantity.setText(String.valueOf(products.get(position).getQuantity()));

       holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AppDatabase db = Room.databaseBuilder(holder.recycle_id.getContext(),
               AppDatabase.class, "part2_db").allowMainThreadQueries().build();
               ProductDao productDao = db.ProductDao();

               productDao.deleteById(products.get(position).getProductID());
               products.remove(position);
               notifyItemRemoved(position);
               finalprice();
           }
       });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView recycle_id, recycle_name, recycle_quantity, recycle_price;
        ImageButton deleteBtn;
        public myviewholder(@NonNull @NotNull View itemView) {
            super(itemView);

            recycle_id =itemView.findViewById(R.id.recycle_id);
            recycle_name =itemView.findViewById(R.id.recycle_name);
            recycle_price =itemView.findViewById(R.id.recycle_price);
            recycle_quantity =itemView.findViewById(R.id.recycle_quantity);
            deleteBtn =itemView.findViewById(R.id.deleteBtn);
        }
    }


    //Calculate final total of all products

    public void finalprice()
    {
        int sum = 0,i;
        for(i=0;i< products.size();i++)
            sum = sum+(products.get(i).getPrice()*products.get(i).getQuantity());

        textView.setText("Total: "+sum);
    }

}
