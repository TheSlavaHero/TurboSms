package ua.kiev.prog.turbosms;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static ua.kiev.prog.turbosms.Message.send;


public class SendThread implements Runnable{
    @Autowired
    private ContactService contactService;
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-ddTHH:mm");
        long time;
        while (true) {
            try {
            List<Group> groups = contactService.listGroups();
            for (int i = 0; i < groups.size(); i++) {
                Group group = contactService.findGroup(i);
                List<Contact> contacts = group.getContacts();
                for (int j = 0; j < contacts.size(); j++) {
                    Contact contact = contacts.get(j);
                    String dateString = contact.getEmail();
                    Date date2 = simpleDateFormat.parse(dateString);
                    time = date2.getTime();
                    if (System.currentTimeMillis() > time) {
                        send(contact.getPhone(), contact.getSurname());
                    }
                }

            }

                thread.sleep(60000);
            } catch (InterruptedException | ParseException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
