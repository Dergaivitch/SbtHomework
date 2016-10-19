package ru.sbt;

import java.sql.SQLException;
import java.util.List;

public interface StudentDao {
    void insertStudent(Student student) throws SQLException;

    Student findById(long id);

    List<Student> findByName(String name);
}
