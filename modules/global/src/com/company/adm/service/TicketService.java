package com.company.adm.service;


import com.company.adm.entity.Bank;
import com.company.adm.entity.Ticket;
import com.company.adm.entity.TicketState;
import com.haulmont.cuba.security.entity.User;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface TicketService {
    String NAME = "adm_TicketService";

    User getUserByLogin(String name);

    void importJuridical(File file) throws ParseException;

    void importPhysical(File file) throws ParseException;

    TicketState getDefaultTicketStatus();

    TicketState getDefaultClosedStatus();

    void setDefaultTicketStatus(TicketState ticketStatus);

    void setClosedTicketStatus(TicketState ticketStatus);

    /**
     * Отправить e-mail по заявке
     *
     * @param ticket
     * @return
     */
    boolean sendEmail(Ticket ticket) throws IOException;

    boolean sendSms(String phone, String text);

    /**
     * Прикрепить к заявке комментарий об смс
     * @param ticket
     * @param text
     * @param phone
     */
    void addSmsCommentToTicket(Ticket ticket, String text, String phone);
}