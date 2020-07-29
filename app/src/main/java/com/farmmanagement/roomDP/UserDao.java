package com.farmmanagement.roomDP;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(UserDetail userDetail);

    @Update
    void update(UserDetail userDetail);

    @Query("SELECT * FROM user_table")
    LiveData<List<UserDetail>> getAllUserDetails();

    @Query("SELECT * FROM user_table WHERE  mobilenumber== :phonenumber")
    LiveData<List<UserDetail>> isDataExist(String phonenumber);

    @Query("DELETE FROM user_table")
    void deleteAllUser();

    @Insert
    void insertPond(PondDetails pondDetails);

    @Query("DELETE FROM pond_table")
    void deleteAllPond();

    @Query("SELECT * FROM pond_table WHERE  mobilenumber== :phoneno")
    LiveData<List<PondDetails>> getAllPonds(String phoneno);

    @Update
    void update(PondDetails pondDetails);

    @Delete
    void delete(PondDetails pondDetails);

}
