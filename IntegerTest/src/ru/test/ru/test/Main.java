package ru.test.ru.test;

/* N5
public class Main {
    public static boolean isPolindrom(long a) {
        String num = String.valueOf(a);
        int result[] = new int[num.length()]; //Требуемый массив
        for (int i = 0; i < num.length(); i++) {
            result[i] = new Integer(num.charAt(i) - 48);

        }


        boolean t = true;
        for (int i = 0; i < result.length; i++)
            if (result[i] != result[result.length - i - 1]) t = false;
        return t;
    }

    public static int reverse(int a) {
        String num = String.valueOf(a);
        int result[] = new int[num.length()];
        for (int i = 0; i < num.length(); i++) result[i] = new Integer(num.charAt(i) - 48);
        int result1 = 0;
        for (int j = num.length() - 1; j >= 0; j--) {

            int pos = (int)Math.pow(10,j);

            result1 += result[j] * pos;

        }
        return result1;

    }
    public static void main(String[] args) {
        long sum;
        int count = 0;
        for (int i = 1; i < 12518; i++) {
            sum = 0l;
            for (int j = 1; j < 51; j++) {
                sum+=reverse(i);
                if (isPolindrom(sum)) {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
*/

/* N4
import java.util.HashSet;
import java.util.Set;

public  class Main {
    public static void main(String[] args) {
        int count = 0;
        Set<Integer> a = new HashSet<>();
        for (int r = 0; r < 10; r++) {
            for (int q = 0; q < 10; q++) {
                for (int u = 0; u < 10; u++) {
                    for (int v = 0; v < 10; v++) {
                        for  (int s = 0; s < 10; s++) {
                            if ((100*r + 10*q + r + 1000*r + 100*u + 10*r + r == 1000*r + 100*v + 10*r + s ) ) {
                                a.clear();
                                a.add(r);
                                a.add(q);
                                a.add(u);
                                a.add(v);
                                a.add(s);
                                if (a.size() == 5) {
                                    count++;
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}
*/
public class Main {
    public static void main(String[] args) {

    }
}









/*
package ru.sbt.refactoring.reportnotifier;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Alexander Ushakov on 15.09.2016.
 */
public class MessageSender {
    private static final String DEFAULT_MAIL_SERVER = "mail.google.com";
    private static final boolean HTML_FORMAT = true;
    private static final String DEFAULT_SUBJECT = "Monthly department salary report";

    private final String mailServer;
    private final String subject;

    public MessageSender() {
        this(DEFAULT_MAIL_SERVER, DEFAULT_SUBJECT);
    }

    public MessageSender(String mailServer, String subject) {
        this.mailServer = mailServer;
        this.subject = subject;
    }

    public void sendMessage(String recipients, String htmlMessageText) throws MessagingException {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServer);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipients);
        helper.setText(htmlMessageText, HTML_FORMAT);
        helper.setSubject(subject);

        mailSender.send(message);
    }
}
 */



















         package ru.sbt.refactoring.reportnotifier;

         import java.sql.ResultSet;
         import java.sql.SQLException;

/**
 * Created by Alexander Ushakov on 15.09.2016.
 */
public class ResultSetProcessor {
    private static final String HTML_BEGIN = "<html><body><table><tr><td>Employee</td><td>Salary</td></tr>";
    private static final String ROW_START_TAG = "<tr>";
    private static final String ROW_END_TAG = "</tr>";
    private static final String CELL_START_TAG = "<td>";
    private static final String CELL_END_TAG = "</td>";
    private static final String TOTAL_ROW_BEGIN = "<tr><td>Total</td><td>";
    private static final String HTML_END = "</table></body></html>";

    public static String process(ResultSet results) throws SQLException {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append(HTML_BEGIN);
        double totalSalary = 0;
        while (results.next()) {
            resultingHtml.append(ROW_START_TAG);
            String employeeName = results.getString("emp_name");
            resultingHtml.append(CELL_START_TAG).append(employeeName).append(CELL_END_TAG);
            double employeeSalary = results.getDouble("salary");
            resultingHtml.append(CELL_START_TAG).append(employeeSalary).append(CELL_END_TAG);
            resultingHtml.append(ROW_END_TAG);
            totalSalary += employeeSalary;
        }
        resultingHtml.append(TOTAL_ROW_BEGIN).append(totalSalary).append(CELL_END_TAG).append(ROW_END_TAG);
        resultingHtml.append(HTML_END);
        return resultingHtml.toString();
    }
}
































package ru.sbt.refactoring.reportnotifier;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.time.LocalDate;

/**
 * Created by Alexander Ushakov on 15.09.2016.
 */
public class SOLQueryHandler {
    private static final String DEFAULT_QUERY = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary " +
            "from employee emp left join " +
            "salary_payments sp on emp.id = sp.employee_id " +
            "where emp.department_id = ? and " +
            "sp.date >= ? and sp.date <= ? " +
            "group by emp.id, emp.name";

    private static PreparedStatement prepareStatement(Connection connection, String query) throws SQLException {
        return connection.prepareStatement(query);
    }

    public static ResultSet getResultSet(Connection connection, String departmentId,
                                         LocalDate dateFrom, LocalDate dateTo) throws SQLException {
        PreparedStatement ps = prepareStatement(connection, DEFAULT_QUERY);
        ps.setString(0, departmentId);
        ps.setDate(1, new java.sql.Date(dateFrom.toEpochDay()));
        ps.setDate(2, new java.sql.Date(dateTo.toEpochDay()));

        return ps.executeQuery();
    }
}




















package ru.sbt.refactoring.reportnotifier;

        import javax.mail.MessagingException;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.time.LocalDate;

public class SalaryHtmlReportNotifier {
    private final Connection connection;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.connection = databaseConnection;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, LocalDate dateFrom, LocalDate dateTo, String recipients) {
        try {
            ResultSet results = SOLQueryHandler.getResultSet(connection, departmentId, dateFrom, dateTo);
            String htmlMessageText = ResultSetProcessor.process(results);
            new MessageSender().sendMessage(recipients, htmlMessageText);
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }
}