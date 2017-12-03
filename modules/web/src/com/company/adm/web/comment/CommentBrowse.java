package com.company.adm.web.comment;

import com.company.adm.entity.contracts.Comment;
import com.haulmont.cuba.gui.WindowManager;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.UUID;

public class CommentBrowse extends AbstractLookup {
    @Inject
    private GroupDatasource<Comment, UUID> commentsDs;

    public void onEdit(Component source) {
        Comment comment = commentsDs.getItem();
        if(comment.getContract() != null)
            openEditor(comment.getContract(), WindowManager.OpenType.THIS_TAB);
        else if(comment.getTicket() != null)
            openEditor(comment.getTicket(), WindowManager.OpenType.THIS_TAB);
    }
}