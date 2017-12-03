package com.company.adm.service;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.DefaultInteger;
import com.haulmont.cuba.core.config.defaults.DefaultString;

@Source(type = SourceType.DATABASE)
public interface AdmConfig extends Config {

    @Property("adm.defaultTicketResponsibleUserLogin")
    @DefaultString("default")
    String getDefaultTicketResponsibleUserLogin();

    @Property("adm.defaultServiceSerialNumber")
    @DefaultInteger(0)
    Integer getDefaultServiceStatusSerialNumber();

    @Property("adm.commercialOffer.text")
    @DefaultString("")
    String getCommercialOfferText();

    @Property("adm.commercialOffer.webSiteLink")
    @DefaultString("http://credit40.ru")
    String getSiteLink();

    @Property("adm.commercialOffer.signText")
    @DefaultString("—\n" +
            "С уважением к Вам и Вашему бизнесу!\n" +
            "\n" +
            "Группа компаний \"Актив\" - Полный перечень Финансовых, Бухгалтерских и Юридических услуг\n" +
            "\n" +
            "http://adm40.ru\n" +
            "http://credit40.ru\n" +
            "\n" +
            "+7 (4842) 75-33- 77, +7 (962) 174-44- 74\n" +
            "\n" +
            "г. Калуга, ул. Гагарина 4, офис 514-5")
    String getSignText();

    @Property("adm.phoneIpAddress")
    String getPhoneIpAddress();

    @Property("adm.phoneSmsPortNumber")
    Integer getPhoneSmsPortNumber();

    @Property("adm.phonePingPortNumber")
    Integer getPhonePingPortNumber();
}
