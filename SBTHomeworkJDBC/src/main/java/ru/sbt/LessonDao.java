package ru.sbt;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dergai on 18.10.2016.
 */
public interface LessonDao {
    void insertLesson(Lesson lesson) throws SQLException;

    Lesson findById(long id);

    Lesson findByDate(String date);
}
