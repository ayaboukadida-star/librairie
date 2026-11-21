package com.sesame.demo.stereotypes;

import com.sesame.demo.commun.Notificateur;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Primary;
@Component
@Primary
// Badge @Component : composant générique (ni métier, ni données, ni web)
public class NotificateurEmail implements Notificateur {

    @Override
    public void envoyer(String destinataire, String message) {
        System.out.println("[EMAIL] vers " + destinataire + " : " + message);
    }
}