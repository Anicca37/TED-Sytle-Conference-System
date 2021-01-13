package DataAccess;

import gateway.DataAccessIn;

import java.sql.*;

public class MysqlAccessIn implements DataAccessIn {

    /**
     * this class contains all the sql code that writes into database in this program
     */
    public MysqlAccessIn(){
    }

    /**
     * update attendee account in database with
     * @param id id of attendee
     * @param pwd password
     * @param status is vip
     * @param msgsent list of message id converted into string
     * @param msgrecv list of message id converted into string
     * @param friends list of attendee id converted into string
     * @param events list of event id converted into string
     * @param requests list of friend request converted into string
     * @param add_request hash map of additional request converted into string
     */
    @Override
    public void updateToDBAttendee(int id, String pwd, Boolean status, String msgsent, String msgrecv,
                                   String friends, String events, String requests, String add_request,
                                   String msgunread, String msgdeleted, String msgarch) {
        String sql = "SELECT * FROM Attendees";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, pwd);
                    rs.updateBoolean(4, status);
                    rs.updateString(5, msgsent);
                    rs.updateString(6, msgrecv);
                    rs.updateString(7, friends);
                    rs.updateString(8, events);
                    rs.updateString(9, requests);
                    rs.updateString(10, add_request);
                    rs.updateString(11, msgunread);
                    rs.updateString(12, msgdeleted);
                    rs.updateString(13, msgarch);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * create new attendee in the database
     * update attendee account in database with
     * @param id id of attendee
     * @param pwd password
     * @param username username
     * @param status is vip
     * @param msgsent list of message id converted into string
     * @param msgrecv list of message id converted into string
     * @param friends list of attendee id converted into string
     * @param events list of event id converted into string
     * @param requests list of friend request converted into string
     * @param add_request hash map of additional request converted into string
     */
    @Override
    public void insertToDBAttendee(int id, String pwd, String username, Boolean status, String msgsent, String msgrecv,
                                   String friends, String events, String requests, String add_request,
                                   String msgunread, String msgdeleted, String msgarch) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Attendees VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, username);
            pstmt.setBoolean(4, status);
            pstmt.setString(5, msgsent);
            pstmt.setString(6, msgrecv);
            pstmt.setString(7, friends);
            pstmt.setString(8, events);
            pstmt.setString(9, requests);
            pstmt.setString(10, add_request);
            pstmt.setString(11, msgunread);
            pstmt.setString(12, msgdeleted);
            pstmt.setString(13, msgarch);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * update organizer with organizer id
     * @param id and update
     * @param pwd password
     * @param msgsent message sent
     * @param events events host
     */
    @Override
    public void updateToDBOrganizer(int id, String pwd, String msgsent, String events){
        String sql = "SELECT * FROM Organizers";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, pwd);
                    rs.updateString(5,msgsent);
                    rs.updateString(6, events);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }



    /**
     * insert organizer with organizer id
     * @param id and update
     * @param pwd password
     * @param msgsent message sent
     * @param events events host
     */
    @Override
    public void insertToDBOrganizer(int id, String pwd, String username, int attendeeId, String msgsent, String events) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Organizers VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, username);
            pstmt.setInt(4, attendeeId);
            pstmt.setString(5, msgsent);
            pstmt.setString(6, events);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param id speaker id
     * @param pwd password
     * @param msgsent message sent
     * @param events events
     * @param msgrecv messsage received
     * @param msgunread unread message list
     * @param msgdeleted deleted message list
     * @param msgarch message archive list
     */
    @Override
    public void updateToDBSpeaker(int id, String pwd, String msgsent, String events, String msgrecv,
                                  String msgunread, String msgdeleted, String msgarch) {
        String sql = "SELECT * FROM Speakers";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, pwd);
                    rs.updateString(4, msgsent);
                    rs.updateString(5, events);
                    rs.updateString(6, msgrecv);
                    rs.updateString(7, msgunread);
                    rs.updateString(8, msgdeleted);
                    rs.updateString(9, msgarch);

                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertToDBSpeaker(int id, String pwd, String username, String msgsent, String events, String msgrecv,
                                  String msgunread, String msgdeleted, String msgarch) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Speakers VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, username);
            pstmt.setString(4, msgsent);
            pstmt.setString(5, events);
            pstmt.setString(6, msgrecv);
            pstmt.setString(7, msgunread);
            pstmt.setString(8, msgdeleted);
            pstmt.setString(9, msgarch);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateToDBMessage(int id, String content, int sender, int receiver, String time, String reply) {
        String sql = "SELECT * FROM Messages";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, content);
                    rs.updateInt(3, sender);
                    rs.updateInt(4, receiver);
                    rs.updateString(5, time);
                    rs.updateString(6, reply);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertToDBMessage(int id, String content, int sender, int receiver, String time, String reply) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Messages VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, content);
            pstmt.setInt(3, sender);
            pstmt.setInt(4, receiver);
            pstmt.setString(5, time);
            pstmt.setString(6, reply);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateToDBDiscussion(int id, String time, String title, int capacity, boolean status,
                                     int room, String speaker, boolean cancel, String att) {
        String sql = "SELECT * FROM Discussions";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, time);
                    rs.updateString(3, title);
                    rs.updateInt(4, capacity);
                    rs.updateBoolean(5, status);
                    rs.updateInt(6, room);
                    rs.updateString(7, speaker);
                    rs.updateBoolean(8, cancel);
                    rs.updateString(9, att);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertToDBDiscussion(int id, String time, String title, int capacity, boolean status,
                                     int room, String speaker, boolean cancel, String att) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Discussions VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, time);
            pstmt.setString(3, title);
            pstmt.setInt(4, capacity);
            pstmt.setBoolean(5, status);
            pstmt.setInt(6, room);
            pstmt.setString(7, speaker);
            pstmt.setBoolean(8, cancel);
            pstmt.setString(9, att);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void updateToDBParty(int id, String time, String title, int capacity, boolean status,
                                int room, boolean cancel, String att) {
        String sql = "SELECT * FROM Parties";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, time);
                    rs.updateString(3, title);
                    rs.updateInt(4, capacity);
                    rs.updateBoolean(5, status);
                    rs.updateInt(6, room);
                    rs.updateBoolean(7, cancel);
                    rs.updateString(8, att);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertToDBParty(int id, String time, String title, int capacity, boolean status,
                                int room, boolean cancel, String att) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Parties VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, time);
            pstmt.setString(3, title);
            pstmt.setInt(4, capacity);
            pstmt.setBoolean(5, status);
            pstmt.setInt(6, room);
            pstmt.setBoolean(7, cancel);
            pstmt.setString(8, att);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateToDBTalk(int id, String time, String title, int capacity, boolean status,
                               int room, int speaker, boolean cancel, String att) {
        String sql = "SELECT * FROM Talks";
        try (
                Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                        "p2db",
                        "p2db123456");
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next()) {
                if (rs.getInt(1) == id){
                    rs.updateString(2, time);
                    rs.updateString(3, title);
                    rs.updateInt(4, capacity);
                    rs.updateBoolean(5, status);
                    rs.updateInt(6, room);
                    rs.updateInt(7, speaker);
                    rs.updateBoolean(8, cancel);
                    rs.updateString(9, att);
                    rs.updateRow();
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void insertToDBTalk(int id, String time, String title, int capacity, boolean status,
                               int room, int speaker, boolean cancel, String att) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://47.75.80.133:33060/p2db",
                    "p2db",
                    "p2db123456");
            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO Talks VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, time);
            pstmt.setString(3, title);
            pstmt.setInt(4, capacity);
            pstmt.setBoolean(5, status);
            pstmt.setInt(6, room);
            pstmt.setInt(7, speaker);
            pstmt.setBoolean(8, cancel);
            pstmt.setString(9, att);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
