package ru.sbt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    private final Connection connection;

    public StudentDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public void insertStudent(Student student) throws SQLException {

            PreparedStatement statement = connection.prepareStatement("insert into Students (studentid, studentname) values(?, ?)");

            statement.setLong(1, student.getId());
            statement.setString(2, student.getName());
            statement.executeUpdate();

    }

    public Student findById(long id) {
        return selectOne(execute("select studentid as id, studentname as name from Students" +
                " where studentid = ?", s -> s.setLong(1, id)));
    }

    public List<Student> findByName(String name) {
        return execute("select studentid as id, studentname as name from Students" +
                " where studentname = ?", s -> s.setString(1, name));
    }

    private Student selectOne(List<Student> persons) {
        return persons.isEmpty() ? null : persons.get(0);
    }

    private List<Student> execute(String sql, Consumer<PreparedStatement> consumer) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            consumer.accept(statement);
            ResultSet resultSet = statement.executeQuery();

            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("studentid");
                String personName = resultSet.getString("studentname");
                students.add(new Student(id, personName));
            }

            return students;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
