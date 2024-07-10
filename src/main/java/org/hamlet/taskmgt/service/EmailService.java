package org.hamlet.taskmgt.service;

import org.hamlet.taskmgt.payload.request.EmailDetails;

public interface EmailService {

    void sendEmailAlert(EmailDetails emailDetails);
}
