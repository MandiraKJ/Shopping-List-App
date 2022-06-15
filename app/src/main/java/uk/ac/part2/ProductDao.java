package uk.ac.part2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao
{
    @Insert
    void insertProduct(Product product);

    // Stops addition of duplicate items
    @Query("SELECT EXISTS(SELECT * FROM Product WHERE productID = :productid)")
    Boolean is_exist(int productid);

    @Query("DELETE FROM Product WHERE productID = :id")
    void deleteById(int id);

    @Query("SELECT * FROM Product")
    List<Product> getallproduct();


}
