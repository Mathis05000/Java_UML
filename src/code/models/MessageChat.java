package code.models;

import java.util.Date;

public class MessageChat extends Message {

    private Date date;

    public MessageChat(String data) {
        super(data);
        this.date = new Date();
    }

    public Date getDate() {
        return this.date;
    }

}
