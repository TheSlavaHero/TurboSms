package ua.kiev.prog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.kiev.prog.model.Contact;
import ua.kiev.prog.model.Group;
import ua.kiev.prog.services.ContactService;
import ua.kiev.prog.services.ContactServiceImpl;

import javax.annotation.PostConstruct;

@Component
public class TestDataBean {
    @Autowired
    private ContactService contactService;

    @PostConstruct
    public void fillData() {
        Group group = new Group("Test");
        Contact contact;

        contactService.addGroup(group);

        for (int i = 0; i < 10; i++) {
            contact = new Contact(null, "Name" + i, "Notification" + i, "38050123456" + i, "2021-03-1" + i + "T14:20");
            contactService.addContact(contact);
        }
        for (int i = 0; i < 10; i++) {
            contact = new Contact(group, "Other" + i, "SuperNotification" + i, "38050654321" + i, "2030-07-2" + i + "T14:20");
            contactService.addContact(contact);
        }
    }
}
