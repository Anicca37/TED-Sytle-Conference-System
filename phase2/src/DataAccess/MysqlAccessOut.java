package DataAccess;

import gateway.DataAccessOut;

import java.sql.*;
import java.util.ArrayList;

public class MysqlAccessOut implements DataAccessOut {

    public  MysqlAccessOut(){
    }

    /**
     * @return array list with attendee data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getAttendeeData(){
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Attendees";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(Boolean.toString(rs.getBoolean(4)));
                l.add(rs.getString(5));
                l.add(rs.getString(6));
                l.add(rs.getString(7));
                l.add(rs.getString(8));
                l.add(rs.getString(9));
                l.add(rs.getString(10));
                l.add(rs.getString(11));
                l.add(rs.getString(12));
                l.add(rs.getString(13));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }


    /**
     * @return array list with organizer data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getOrganizerData(){
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Organizers";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                "p2db",
                "p2db123456");
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(Integer.toString(rs.getInt(4)));
                l.add(rs.getString(5));
                l.add(rs.getString(6));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }

    /**
     * @return array list with speaker data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getSpeakerData() {
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Speakers";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(rs.getString(4));
                l.add(rs.getString(5));
                l.add(rs.getString(6));
                l.add(rs.getString(7));
                l.add(rs.getString(8));
                l.add(rs.getString(9));
                lst.add(l);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }


    /**
     * @return array list with messager data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getMessageData() {
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Messages";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(Integer.toString(rs.getInt(3)));
                l.add(Integer.toString(rs.getInt(4)));
                l.add(rs.getString(5));
                l.add(rs.getString(6));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lst;
    }

    /**
     * @return array list with discussion data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getDiscussionData() {
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Discussions";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(Integer.toString(rs.getInt(4)));
                l.add(Boolean.toString(rs.getBoolean(5)));
                l.add(Integer.toString(rs.getInt(6)));
                l.add(rs.getString(7));
                l.add(Boolean.toString(rs.getBoolean(8)));
                l.add(rs.getString(9));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  lst;
    }


    /**
     * @return array list with party data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getPartyData() {
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Parties";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(Integer.toString(rs.getInt(4)));
                l.add(Boolean.toString(rs.getBoolean(5)));
                l.add(Integer.toString(rs.getInt(6)));
                l.add(Boolean.toString(rs.getBoolean(7)));
                l.add(rs.getString(8));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  lst;
    }


    /**
     * @return array list with Talk data from database
     */
    @Override
    public ArrayList<ArrayList<String>> getTalkData() {
        ArrayList<ArrayList<String>> lst = new ArrayList<>();
        String sql = "SELECT * FROM Talks";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                ArrayList<String> l = new ArrayList<String>();
                l.add(Integer.toString(rs.getInt(1)));
                l.add(rs.getString(2));
                l.add(rs.getString(3));
                l.add(Integer.toString(rs.getInt(4)));
                l.add(Boolean.toString(rs.getBoolean(5)));
                l.add(Integer.toString(rs.getInt(6)));
                l.add(Integer.toString(rs.getInt(7)));
                l.add(Boolean.toString(rs.getBoolean(8)));
                l.add(rs.getString(9));
                lst.add(l);
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return  lst;
    }
}
