import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/skillbox";
        String user = "root";
        String pass = "root";
        try {
            Connection connection = DriverManager.getConnection(url, user, pass);

            Statement statement = connection.createStatement();
            Statement statementSales = connection.createStatement();

            ResultSet courses = statement.executeQuery("SELECT * FROM Courses");
            while(courses.next()) {

                String courseName = courses.getString("name");

                ResultSet result = statementSales.executeQuery("SELECT COUNT(*) / (1 + (\n" +
                        "(SELECT MAX(MONTH(pl1.subscription_date)) FROM Purchaselist pl1 WHERE pl1.course_name = \"" + courseName + "\")-" +
                        "(SELECT MIN(MONTH(pl2.subscription_date)) FROM Purchaselist pl2 WHERE pl2.course_name = \"" + courseName + "\")))\n" +
                        "AS average_monthly_sales\n" +
                        "FROM Purchaselist pl " +
                        "WHERE pl.course_name = \"" + courseName + "\" " +
                        "AND pl.subscription_date >= '20180101' AND pl.subscription_date <= '20181231'");
                result.next();
                double averageValue = result.getDouble("average_monthly_sales");
                if(Double.isNaN(averageValue)){
                    averageValue = 0.00;
                }
                String middle = String.format("%.2f", averageValue);

                System.out.println(courseName + " - " + middle);

            }

            courses.close();
            statementSales.close();
            statement.close();
            connection.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}