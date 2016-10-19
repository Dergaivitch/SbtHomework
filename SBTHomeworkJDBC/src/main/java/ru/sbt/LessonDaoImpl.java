package ru.sbt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dergai on 18.10.2016.
 */
public class LessonDaoImpl implements LessonDao {

    private final Connection connection;

    public LessonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void insertLesson(Lesson lesson) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("insert into Lessons (lessonid, lessondate) values(?, ?)");

        statement.setLong(1, lesson.getId());
        statement.setString(2, lesson.getDate());
        statement.executeUpdate();

    }

    public Lesson findById(long id) {
        return selectOne(execute("select lessonid, lessondate from Lessons" +
                " where lessonid = ?", s -> s.setLong(1, id)));
    }

    public Lesson findByDate(String date) {
        return selectOne(execute("select lessonid, lessondate from Lessons" +
                " where lessondate = ?", s -> s.setString(1, date)));
    }


    private Lesson selectOne(List<Lesson> lessons) {
        return lessons.isEmpty() ? null : lessons.get(0);
    }

    private List<Lesson> execute(String sql, Consumer<PreparedStatement> consumer) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            consumer.accept(statement);
            ResultSet resultSet = statement.executeQuery();

            List<Lesson> lessons = new ArrayList<>();
            while (resultSet.next()) {
                long lessonId = resultSet.getLong("lessonid");
                String lessonDate = resultSet.getString("lessondate");
                lessons.add(new Lesson(lessonId, lessonDate));
            }
            return lessons;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
