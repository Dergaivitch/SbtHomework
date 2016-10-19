package ru.sbt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = getConnection("jdbc:h2:~/DBPATH/test", "admin", "secret")) {
            //createTable(connection);
//----------------------------------------------------------------------------------------
            StudentDao studentDao = new StudentDaoImpl(connection);
            LessonDao lessonDao = new LessonDaoImpl(connection);
            CheckManager.connection = connection;

//            studentDao.insertStudent(new Student(1l,"alex"));
//            studentDao.insertStudent(new Student(2l,"bob"));
//            studentDao.insertStudent(new Student(3l,"alice"));
//
//
//            lessonDao.insertLesson(new Lesson(1l,"10.1.16"));
//            lessonDao.insertLesson(new Lesson(2l,"17.1.16"));
//            lessonDao.insertLesson(new Lesson(3l,"24.1.16"));
//
//            CheckManager.check(studentDao.findById(1l),lessonDao.findById(1L));
//            CheckManager.check(studentDao.findById(1l),lessonDao.findById(2L));
//            CheckManager.check(studentDao.findById(2l),lessonDao.findById(1L));
//            CheckManager.check(studentDao.findById(2l),lessonDao.findById(3L));
//            CheckManager.check(studentDao.findById(3l),lessonDao.findById(3L));

            //CheckManager.getListByStudentId(1L);
            System.out.println("______________________________________________");
            CheckManager.getListByLessonId(3L);


        }

    }

    private static void createTable(Connection connection) throws SQLException {
        connection.createStatement().executeUpdate("CREATE TABLE Students" +
                "(" +
                "studentid int," +
                "studentname varchar(255)" +
                ");");

        connection.createStatement().executeUpdate("CREATE TABLE Lessons" +
                "(" +
                "lessonid int," +
                "lessondate varchar(255)" +
                ");");

        connection.createStatement().executeUpdate("CREATE TABLE Checks" +
                "(" +
                "stuid int," +
                "lesid int" +
                ");");
    }
}
