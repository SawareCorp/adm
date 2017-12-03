package com.company.adm.listener;

import com.company.adm.entity.Contact;
import com.company.adm.entity.contracts.Comment;
import org.springframework.stereotype.Component;
import com.haulmont.cuba.core.listener.BeforeDeleteEntityListener;
import com.haulmont.cuba.core.EntityManager;
import com.company.adm.entity.Ticket;

@Component("adm_TicketEntityListener")
public class TicketEntityListener implements BeforeDeleteEntityListener<Ticket> {


    @Override
    public void onBeforeDelete(Ticket entity, EntityManager entityManager) {
        for (Comment comment : entity.getHistory())
            entityManager.remove(comment);

        for (Contact contact : entity.getContacts())
            entityManager.remove(contact);
    }


}