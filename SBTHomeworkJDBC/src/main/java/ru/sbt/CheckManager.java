package ru.sbt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dergai on 18.10.2016.
 */
public class CheckManager {

    public static Connection connection;
    private Map<String, Object> queryParams = new HashMap<>();//название параметра - обьект чтобы подставлять в запрос

    public static void init(Connection connection) {
        CheckManager.connection = connection;
    }

    static ResultSet execute(String sql, Consumer<PreparedStatement> consumer) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            consumer.accept(statement);
            ResultSet resultSet = statement.executeQuery();
            return statement.executeQuery();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static List<Lesson> getListByStudentId(long studentId) {

        ResultSet resultSet = execute("select lessonid, lessondate from Checks, Students, lessons" +
                " where lessonid in (select lesid where stuid = ? )  AND lesid = lessonid AND stuid = studentid", s -> s.setLong(1, studentId));//не явный join
        List<Lesson> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                long lessonId = resultSet.getLong("lessonid");
                String lessonDate = resultSet.getString("lessondate");
                Lesson curLesson = new Lesson(lessonId, lessonDate);
                result.add(curLesson);
                System.out.println(curLesson);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;


    }

    static List<Student> getListByLessonId(long lessonId) {

        ResultSet resultSet = execute("select studentid, studentname from Checks, Students, lessons" +
                " where studentid in (select stuid where lesid = ? ) AND lesid = lessonid AND stuid = studentid", s -> s.setLong(1, lessonId));//не явный join
        List<Student> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                long studentId = resultSet.getLong("studentid");
                String studentName = resultSet.getString("studentname");
                Student curStudent = new Student(studentId, studentName);
                result.add(curStudent);
                System.out.println(curStudent);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;

    }

    static void check(Student student, Lesson lesson) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("insert into Checks (stuid, lesid) values(?, ?)");

        statement.setLong(1, student.getId());
        statement.setLong(2, lesson.getId());
        statement.executeUpdate();

    }


}
