package ua.kiev.prog.turbosms;

public class Sms {
    private String sender;
    private String text;

    public Sms(String sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public Sms() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Sms{" +
                "sender='" + sender + '\'' +
                ", text='" + text + '\'' +
                '}';
    }


}
