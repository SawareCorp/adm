package com.company.adm.web.ticket;

import com.company.adm.entity.Ticket;
import com.company.adm.service.TicketService;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.data.GroupDatasource;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import com.haulmont.reports.gui.actions.TablePrintFormAction;

import javax.inject.Inject;
import java.io.File;
import java.text.ParseException;
import java.util.*;

public class TicketBrowse extends AbstractLookup {
    @Inject
    private FileUploadField importPhysics;
    @Inject
    private FileUploadField importJuridical;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private GroupTable<Ticket> ticketsTable;
    @Inject
    private GroupDatasource<Ticket, UUID> ticketsDs;
    @Inject
    private Button reportsButton;
    @Inject
    private CheckBox showClosedCheckBox;
    @Inject
    private TicketService ticketService;

    /**
     * Called by the framework after creation of all components and before showing the screen.
     * <br> Override this method and put initialization logic here.
     *
     * @param params parameters passed from caller's code, usually from
     *               {@link #openWindow(String, WindowManager.OpenType)} and similar methods, or set in
     *               {@code screens.xml} for this registered screen
     */
    @Override
    public void init(Map<String, Object> params) {
        importJuridical.addFileUploadSucceedListener(e -> {
            File file = fileUploadingAPI.getFile(importJuridical.getFileId());
            try {
                ticketService.importJuridical(file);
                ticketsDs.refresh();
            } catch (ParseException err) {
                showNotification("<p>ОШИБКА!" +
                        String.format("<p><b>%s</b>", err.getMessage()) +
                        "<p>Правила оформления входного файла:</p> " +
                        "<p>1. В первой строке таблицы перечислены названия параметров" +
                        "<p>2. Начиная со второй строки в соответствующих колонках перечисляются параметры. <b>ВАЖНО!</b> Первым параметром должно быть название организации, вторым - контактный телефон(желательно без скобок), третьим - наименование источника из справочника источников" +
                        "<p>3. Сохранить таблицу как CSV (разделители - запятые)" +
                        "<p>4. Открыть полученный CSV файл блокнотом и пересохранить его в кодировке UTF8", NotificationType.HUMANIZED_HTML);
            }
        });

        importPhysics.addFileUploadSucceedListener(e -> {
            File file = fileUploadingAPI.getFile(importPhysics.getFileId());
            try {
                ticketService.importPhysical(file);
                ticketsDs.refresh();
            } catch (ParseException err) {
                showNotification("<p>ОШИБКА!" +
                        String.format("<p><b>%s</b>", err.getMessage()) +
                        "<p>Правила оформления входного файла:</p> " +
                        "<p>1. В первой строке таблицы перечислены названия параметров" +
                        "<p>2. Начиная со второй строки в соответствующих колонках перечисляются параметры. <b>ВАЖНО!</b> Первым параметром должно быть ФИО, вторым - контактный телефон(желательно без скобок), третьим - наименование источника из справочника источников" +
                        "<p>3. Сохранить таблицу как CSV (разделители - запятые)" +
                        "<p>4. Открыть полученный CSV файл блокнотом и пересохранить его в кодировке UTF8", NotificationType.HUMANIZED_HTML);
            }
        });

        TablePrintFormAction action = new TablePrintFormAction("report", ticketsTable);
        ticketsTable.addAction(action);
        reportsButton.setAction(action);

        showClosedCheckBox.setValue(true);
        showClosedCheckBox.addValueChangeListener(e -> {
            if (e.getValue() == null)
                return;
            if ((Boolean) e.getValue())
                ticketsDs.setQuery("select e from adm$Ticket e order by e.createTs desc");
            else ticketsDs.setQuery("select e from adm$Ticket e where e.status.closed = false order by e.createTs desc");
            ticketsDs.refresh();
        });

    }
}