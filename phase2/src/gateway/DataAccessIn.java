package gateway;

public interface DataAccessIn {//this interface connects the gateway class and database
    // without violating the clean architecture

    void updateToDBAttendee(int id, String pwd, Boolean status, String msgsent, String msgrecv,
                            String friends, String events, String requests, String add_reuqeust,
                            String msgunread, String msgdeleted, String msgarch);

    void insertToDBAttendee(int id, String pwd, String username, Boolean status, String msgsent, String msgrecv,
                            String friends, String events, String requests, String add_reuqeust,
                            String msgunread, String msgdeleted, String msgarch);

    void updateToDBOrganizer(int id, String pwd, String msgsent, String events);

    void insertToDBOrganizer(int id, String pwd, String username, int attendeeId, String msgsent, String events);

    void updateToDBSpeaker(int id, String pwd, String msgsent, String events, String msgrecv,
                           String msgunread, String msgdeleted, String msgarch);

    void insertToDBSpeaker(int id, String pwd, String username, String msgsent, String events, String msgrecv,
                           String msgunread, String msgdeleted, String msgarch);

    void updateToDBMessage(int id, String content, int sender, int receiver, String time, String reply);

    void insertToDBMessage(int id, String content, int sender, int receiver, String time, String reply);

    void updateToDBDiscussion(int id, String time, String title, int capacity, boolean status,
                              int room, String speaker, boolean cancel, String att);

    void insertToDBDiscussion(int id, String time, String title, int capacity, boolean status,
                              int room, String speaker, boolean cancel, String att);

    void updateToDBParty(int id, String time, String title, int capacity, boolean status,
                         int room, boolean cancel, String att);

    void insertToDBParty(int id, String time, String title, int capacity, boolean status,
                         int room, boolean cancel, String att);

    void updateToDBTalk(int id, String time, String title, int capacity, boolean status,
                    int room, int speaker, boolean cancel, String att);



    void insertToDBTalk(int id, String time, String title, int capacity, boolean status,
                        int room, int speaker, boolean cancel, String att);
}
