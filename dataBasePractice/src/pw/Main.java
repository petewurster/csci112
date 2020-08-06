/**
 * Main.java
 * 2020-08-06 pwurster
 *
 * This programs demonstrates SQL database connections using Java.
 */

package pw;
import java.io.File;
import java.io.PrintWriter;
import java.sql.*;


public class Main {

    //database constants
    private static final String PROTOCOL = "jdbc:mysql://";
    private static final String HOST = "68.178.217.12";
    private static final String DB_LOC = "/CWHDemo";
    private static final String USER = "CWHDemo";
    private static final String PASSWORD = "Cwhdemo%123";

    //output file
    private static final String OUTFILE = "courseData.scv";

    public static void main(String[] args) throws SQLException {

        //init connection objects
        Connection cnx = DriverManager.getConnection(PROTOCOL + HOST + DB_LOC, USER, PASSWORD);
        Statement stmt = cnx.createStatement();

        //1st required method
        printRequiredQueryDataToFile(stmt);

        //2nd required method
        answerMyQuestion(stmt);

    }



    //SELECT crn, subject, course, section, days and time for all CSCI courses, in order according to the course number
    private static void printRequiredQueryDataToFile(Statement stmt) {

        try {

            ResultSet rs = stmt.executeQuery("SELECT crn, subject, course, section, days, time FROM fall2014 ORDER BY crn");


            //if query succeeds continue to write file
            try (PrintWriter out = new PrintWriter(new File(OUTFILE))) {
                while (rs.next()) {
                    out.printf("%s, %s, %s, %s, %s, %s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                    out.println();
                }
            } catch (Exception e) {
                System.out.println("Something went wrong writing to \"" + OUTFILE + "\"");
            }


        //query failed - print trace for debugging
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    //Answers this question: "What 4-credit CIS courses were running in Fall 2014?"
    private static void answerMyQuestion(Statement stmt) {

        try {

            ResultSet rs = stmt.executeQuery("SELECT crn, subject, course, section, days, time FROM fall2014 WHERE subject = \"CIS\" AND credits = 4 ORDER BY course");

            //if query succeeds print to console
            System.out.println("What 4-credit CIS courses were running in Fall 2014?");
            System.out.printf("%n%-6s %-6s %-6s %-6s %-6s %s","CRN", "SUBJ", "COURSE", "SECT", "DAYS", "TIMES");
            System.out.println();

            //loop through results
            while (rs.next()) {
                System.out.printf("%-6s %-6s %-6s %-6s %-6s %s", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                System.out.println();
            }

        //query failed - print trace for debugging
        } catch (Exception e) {
            e.printStackTrace();
        }

    }





}
