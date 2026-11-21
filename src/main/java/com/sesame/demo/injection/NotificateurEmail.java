package com.sesame.demo.injection;

import com.sesame.demo.commun.Notificateur;
import org.springframework.stereotype.Component;

@Component
public class NotificateurEmail implements Notificateur {

    @Override
    public void envoyer(String destinataire, String message) {
        System.out.println("[EMAIL] vers " + destinataire + " : " + message);
    }
}