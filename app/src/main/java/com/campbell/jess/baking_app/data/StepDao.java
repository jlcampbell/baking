package com.campbell.jess.baking_app.data;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.campbell.jess.baking_app.data.model.Step;

import java.util.List;

/**
 * Created by jlcampbell on 8/20/2018.
 */

public interface StepDao {
    @Query("SELECT * FROM step_table")
    List<Step> getAllSteps();

    @Insert
    void insert(Step step);

    @Query("DELETE FROM step_table")
    void deleteAll();
}
