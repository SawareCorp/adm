package com.company.adm.service;

import com.company.adm.entity.*;
import com.company.adm.entity.contracts.Comment;
import com.google.common.io.Files;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.User;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;
import java.util.regex.Pattern;

@Service(TicketService.NAME)
public class TicketServiceBean implements TicketService {
    @Inject
    private Logger log;
    @Inject
    private Persistence persistence;
    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private Messages messages;
    @Inject
    private EmailerAPI emailerAPI;
    @Inject
    private Resources resources;
    @Inject
    private AdmConfig admConfig;
    @Inject
    private Metadata metadata;

    @Override
    public User getUserByLogin(String name) {
        assert name != null;
        User user = null;
        try (Transaction tx = persistence.createTransaction()) {
            user = persistence.getEntityManager()
                    .createQuery("select u from sec$User u where u.login = ?1", User.class)
                    .setParameter(1, name)
                    .getSingleResult();
            tx.close();
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        } finally {
            return user;
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void importJuridical(File file) throws ParseException {
        Transaction tx = persistence.createTransaction();
        EntityManager entityManager = persistence.getEntityManager();

        try {
            List<String> newTickets = Files.readLines(file, Charset.forName("UTF-8"));

            String headersLine = newTickets.get(0);
            newTickets.remove(0);

            List<Ticket> ticketsToSave = new ArrayList<>(newTickets.size());
            String[] headers = headersLine.split(";");

            boolean hasEmptyString = false;

            for (String line : newTickets) {
                String[] params = line.split(";");
                if (params.length < 2 || params[0] == null || params[1] == null) {
                    if (hasEmptyString)
                        throw new ParseException(messages.getMessage(getClass(), "emptyLinesErrorMsg"), 0);
                    else {
                        hasEmptyString = true;
                        continue;
                    }
                }
                Ticket newTicket = new Ticket();
                newTicket.init();
                newTicket.setFace(Face.entity);
                newTicket.setSource(Source.Incoming);

                List<Comment> comments = new ArrayList<>();
                newTicket.setHistory(comments);

                for (int i = 0; i < headers.length; i++) {
                    if (i == 0) {
                        String name = params[i];
                        name = name.replaceAll("^\"|\"$", "");
                        name = name.replaceAll("\"\"", "\"");
                        newTicket.setName(name);
                    } else if (i == 1) {
                        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{6,10}");
                        if (!pattern.matcher(params[i]).matches())
                            throw new ParseException(messages.getMessage(getClass(), "badPhoneErrorMsg") + " " + params[i], 0);
                        else newTicket.setPhone(params[i]);
                    } else if (i == 2) {
                        List<SourceDescription> sources = entityManager.createQuery("select sd from adm$SourceDescription sd where lower(sd.name) = ?1", SourceDescription.class)
                                .setParameter(1, params[i].toLowerCase())
                                .getResultList();
                        if (sources.size() == 0)
                            throw new ParseException(String.format("%s: \"%s\"",
                                    messages.getMessage(getClass(), "badSourceErrorMsg"),
                                    params[i]), 0);
                        else if (sources.size() > 1)
                            throw new ParseException(String.format("%s \"%s\"",
                                    messages.getMessage(getClass(), "multipleSourcesErrorMsg"),
                                    params[i]), 0);
                        else newTicket.setSourceDescription(sources.get(0));
                    } else if (i < params.length) {
                        Comment comment = new Comment();
                        comment.setDateTime(new Date());
                        comment.setTicket(newTicket);
                        comment.setUser(userSessionSource.getUserSession().getUser());
                        comment.setComment(String.format("%s : %s", headers[i], params[i]));
                        comments.add(comment);
                    }
                }

                ticketsToSave.add(newTicket);
            }
            for (Ticket ticket : ticketsToSave) {
                entityManager.persist(ticket);
                for (Comment comment : ticket.getHistory())
                    entityManager.persist(comment);
            }
        } catch (IOException e) {
            throw new RuntimeException(messages.getMessage(getClass(), "readingFileErrorMsg"), e);
        } catch (Exception e) {
            if (e instanceof ParseException)
                throw e;
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            tx.commit();
            tx.close();
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void importPhysical(File file) throws ParseException {
        Transaction tx = persistence.createTransaction();
        EntityManager entityManager = persistence.getEntityManager();

        try {
            List<String> newTickets = Files.readLines(file, Charset.forName("UTF-8"));

            String headersLine = newTickets.get(0);
            newTickets.remove(0);

            List<Ticket> ticketsToSave = new ArrayList<>(newTickets.size());
            String[] headers = headersLine.split(";");

            boolean hasEmptyString = false;

            for (String line : newTickets) {
                String[] params = line.split(";");
                if (params.length < 2 || params[0] == null || params[1] == null) {
                    if (hasEmptyString)
                        throw new ParseException(messages.getMessage(getClass(), "emptyLinesErrorMsg"), 0);
                    else {
                        hasEmptyString = true;
                        continue;
                    }
                }
                Ticket newTicket = new Ticket();
                newTicket.init();
                newTicket.setFace(Face.individual);
                newTicket.setSource(Source.Incoming);

                List<Comment> comments = new ArrayList<>();
                newTicket.setHistory(comments);

                for (int i = 0; i < headers.length; i++) {
                    if (i == 0) {
                        String name = params[i];
                        name = name.replaceAll("^\"|\"$", "");
                        name = name.replaceAll("\"\"", "\"");
                        newTicket.setName(name);
                    } else if (i == 1) {
                        Pattern pattern = Pattern.compile("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{6,10}");
                        if (!pattern.matcher(params[i]).matches())
                            throw new ParseException(messages.getMessage(getClass(), "badPhoneErrorMsg") + " " + params[i], 0);
                        else newTicket.setPhone(params[i]);
                    } else if (i == 2) {
                        List<SourceDescription> sources = null;
                        try {
                            sources = entityManager.createQuery("select sd from adm$SourceDescription sd where lower(sd.name) = ?1", SourceDescription.class)
                                    .setParameter(1, params[i].toLowerCase())
                                    .getResultList();
                        } catch (Exception e) {
                            continue;
                        }
                        if (sources.size() == 0)
                            throw new ParseException(String.format("%s: \"%s\"",
                                    messages.getMessage(getClass(), "badSourceErrorMsg"),
                                    params[i]), 0);
                        else if (sources.size() > 1)
                            throw new ParseException(String.format("%s \"%s\"",
                                    messages.getMessage(getClass(), "multipleSourcesErrorMsg"),
                                    params[i]), 0);
                        else newTicket.setSourceDescription(sources.get(0));
                    } else if (i < params.length) {
                        Comment comment = new Comment();
                        comment.setDateTime(new Date());
                        comment.setTicket(newTicket);
                        comment.setUser(userSessionSource.getUserSession().getUser());
                        comment.setComment(String.format("%s : %s", headers[i], params[i]));
                        comments.add(comment);
                    }
                }

                ticketsToSave.add(newTicket);
            }
            for (Ticket ticket : ticketsToSave) {
                entityManager.persist(ticket);
                for (Comment comment : ticket.getHistory())
                    entityManager.persist(comment);
            }

        } catch (IOException e) {
            throw new RuntimeException(messages.getMessage(getClass(), "readingFileErrorMsg"), e);
        } catch (Exception e) {
            if (e instanceof ParseException)
                throw e;
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            tx.commit();
            tx.close();
        }
    }

    @Override
    public TicketState getDefaultTicketStatus() {
        TicketState state;
        try (Transaction tx = persistence.createTransaction()) {
            state = persistence.getEntityManager()
                    .createQuery("select s from adm$TicketState s where s.defaultStatus = true", TicketState.class)
                    .getSingleResult();
            tx.close();
            return state;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public TicketState getDefaultClosedStatus() {
        TicketState state;
        try (Transaction tx = persistence.createTransaction()) {
            state = persistence.getEntityManager()
                    .createQuery("select s from adm$TicketState s where s.closed = true", TicketState.class)
                    .getSingleResult();
            tx.close();
            return state;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void setDefaultTicketStatus(TicketState ticketStatus) {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            int updated = em.createQuery("update adm$TicketState s set s.defaultStatus = false", TicketState.class)
                    .executeUpdate();
            updated = em.createQuery("update adm$TicketState s set s.defaultStatus = true where s.id = ?1", TicketState.class)
                    .setParameter(1, ticketStatus.getId())
                    .executeUpdate();
            tx.commit();
        }
    }

    @Override
    public void setClosedTicketStatus(TicketState ticketStatus) {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            int updated = em.createQuery("update adm$TicketState s set s.closed = false", TicketState.class)
                    .executeUpdate();
            updated = em.createQuery("update adm$TicketState s set s.closed = true where s.id = ?1", TicketState.class)
                    .setParameter(1, ticketStatus.getId())
                    .executeUpdate();
            tx.commit();
        }
    }

    /**
     * Отправить e-mail по заявке
     *
     * @param ticket
     * @return
     */
    @Override
    public boolean sendEmail(Ticket ticket) throws IOException {
        InputStream resourceAsStream = resources.getResourceAsStream("com/company/adm/templates/ActiveBusiness.pdf");
        byte[] data = new byte[resourceAsStream.available()];
        resourceAsStream.read(data);
        EmailAttachment admBusiness = new EmailAttachment(data, "ActiveBusiness.pdf");
        resourceAsStream = resources.getResourceAsStream("com/company/adm/templates/ActiveCredit.pdf");
        data = new byte[resourceAsStream.available()];
        resourceAsStream.read(data);
        EmailAttachment admCredit = new EmailAttachment(data, "ActiveCredit.pdf");

        Map<String, Serializable> params = new HashMap<>();
        params.put("content", admConfig.getCommercialOfferText());
        params.put("sign", admConfig.getSignText());


        EmailInfo emailInfo = new EmailInfo(
                ticket.getEMail(),
                "Коммерческое предложение от Группы компаний \"АКТИВ\"",
                null,
                "com/company/adm/templates/commercialOffer.txt",
                params,
                admBusiness,
                admCredit
        );
        try {
            emailerAPI.sendEmail(emailInfo);
        } catch (EmailException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendSms(String phone, String text) {
        phone = phone
                .replaceAll("\\(", "")
                .replaceAll("\\)", "")
                .replaceAll("-", "");
        try {
            Socket pingSocket = new Socket(admConfig.getPhoneIpAddress(), admConfig.getPhonePingPortNumber());
            pingSocket.close();

            Socket socket = new Socket(admConfig.getPhoneIpAddress(), admConfig.getPhoneSmsPortNumber());
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println(String.format("%s&&&%s", phone, text));
            writer.flush();
            writer.close();
            socket.close();
            return true;
        } catch (ConnectException e){
            log.error(String.format("%s %s", "Ошибка при отправке смс", ExceptionUtils.getFullStackTrace(e)));
            return false;
        } catch (IOException e) {
            log.error(String.format("%s %s", "Ошибка неизвестная ошибка", ExceptionUtils.getFullStackTrace(e)));
            return false;
        }
    }

    /**
     * Прикрепить к заявке комментарий об смс
     *
     * @param ticket
     * @param text
     * @param phone
     */
    @Override
    @Transactional
    public void addSmsCommentToTicket(Ticket ticket, String text, String phone) {
        EntityManager em = persistence.getEntityManager();
        ticket = em.merge(ticket);

        Comment comment = metadata.create(Comment.class);
        comment.setTicket(ticket);
        comment.setDateTime(new Date());
        comment.setUser(userSessionSource.getUserSession().getUser());
        comment.setComment(String.format("Отправлено смс на номер %s с текстом: \n \"%s\"", phone, text));
        em.persist(comment);

        ticket.getHistory().add(comment);
    }
}