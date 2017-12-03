package com.company.adm.web.contractor;

import com.company.adm.entity.Contact;
import com.company.adm.entity.Face;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.components.*;
import com.company.adm.entity.contracts.Contractor;
import com.haulmont.cuba.gui.data.CollectionDatasource;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;
import java.util.UUID;

public class ContractorEdit extends AbstractEditor<Contractor> {
    @Named("fieldGroup.name")
    private ResizableTextArea nameField;
    @Named("fieldGroup.face")
    private LookupField faceField;
    @Named("contractorFieldGroup.address")
    private ResizableTextArea addressField;
    @Named("contractorFieldGroup.divisionCode")
    private TextField divisionCodeField;
    @Named("contractorFieldGroup.issueDate")
    private DateField issueDateField;
    @Named("contractorFieldGroup.issuingAuthority")
    private TextField issuingAuthorityField;
    @Named("contractorFieldGroup.juridicalAddress")
    private ResizableTextArea juridicalAddressField;
    @Named("contractorFieldGroup.kpp")
    private TextField kppField;
    @Named("contractorFieldGroup.ogrn")
    private TextField ogrnField;
    @Named("contractorFieldGroup.passport")
    private TextField passportField;
    @Named("contractorFieldGroup.postAddress")
    private ResizableTextArea postAddressField;
    @Named("contractorFieldGroup.snails")
    private TextField snailsField;
    @Named("contractorFieldGroup.tin")
    private TextField tinField;
    @Named("contractorFieldGroup.dateBorn")
    private DateField dateBornField;
    @Inject
    private FileMultiUploadField contractorMultiUpload;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private CollectionDatasource<FileDescriptor, UUID> filesDs;

    @Override
    public void init(Map<String, Object> params) {
        nameField.setResizable(true);

        contractorMultiUpload.addQueueUploadCompleteListener(() -> {
            for (Map.Entry<UUID, String> entry : contractorMultiUpload.getUploadsMap().entrySet()) {
                UUID fileId = entry.getKey();
                String fileName = entry.getValue();
                FileDescriptor fd = fileUploadingAPI.getFileDescriptor(fileId, fileName);
                // save file to FileStorage
                try {
                    fileUploadingAPI.putFileIntoStorage(fileId, fd);
                } catch (FileStorageException e) {
                    new RuntimeException("Error saving file to FileStorage", e);
                }
                // save file descriptor to database
                filesDs.addItem(fd);
            }
            showNotification("Добавлен файл: " + contractorMultiUpload.getUploadsMap().values(), NotificationType.HUMANIZED);
            contractorMultiUpload.clearUploads();
        });
        contractorMultiUpload.addFileUploadErrorListener(event ->
                showNotification("File upload error", NotificationType.HUMANIZED));
    }

    @Override
    protected void postInit() {
        if(getItem().isNew())
            getItem().setFace(Face.individual);
        faceField.addValueChangeListener(valueChangeEvent -> personalDataVisibility((Face) valueChangeEvent.getValue()));
        personalDataVisibility(getItem().getFace());
    }

    private void personalDataVisibility(Face face) {
        if(face == Face.individual){
            individualFieldsVisibility(true);
            entityFieldsVisibility(false);
        }else if(face == Face.entity){
            individualFieldsVisibility(false);
            entityFieldsVisibility(true);
        }
    }

    private void entityFieldsVisibility(boolean visibility) {
        tinField.setVisible(visibility);
        kppField.setVisible(visibility);
        ogrnField.setVisible(visibility);
        juridicalAddressField.setVisible(visibility);
        postAddressField.setVisible(visibility);
    }

    private void individualFieldsVisibility(boolean visibility) {
        passportField.setVisible(visibility);
        issueDateField.setVisible(visibility);
        issuingAuthorityField.setVisible(visibility);
        divisionCodeField.setVisible(visibility);
        dateBornField.setVisible(visibility);
        snailsField.setVisible(visibility);
        addressField.setVisible(visibility);
    }

    public void onEdit(Component source) {
        FileDescriptor downloadedFile = filesDs.getItem();
        exportDisplay.show(downloadedFile);
    }
}