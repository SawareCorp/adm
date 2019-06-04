package com.company.adm.web.cashwarrant;

import com.haulmont.cuba.gui.components.*;

import javax.inject.Inject;
import java.util.Map;

public class AmountSelector extends AbstractWindow {
    @Inject
    private TextField amountField;
    private Long amount = 0L;

    @Override
    public void init(Map<String, Object> params) {
        amountField.addValueChangeListener(e -> {
            Object value = e.getValue();
            try {
                this.amount = (Long) value;
            } catch (ClassCastException | NullPointerException ex) {
                this.amount = 0L;
                amountField.setValue(null);
            }
        });
        amountField.addValidator(value -> {
            if ((Long) value <= 0)
                throw new ValidationException("Значение должно быть больше 0");
        });
    }

    public void onOkBtnClick() {
        if (!validateAll())
            return;
        close(Window.COMMIT_ACTION_ID);
    }

    public void onCancelBtnClick() {
        close(Window.CLOSE_ACTION_ID);
    }

    public Long getAmount() {
        return amount;
    }
}