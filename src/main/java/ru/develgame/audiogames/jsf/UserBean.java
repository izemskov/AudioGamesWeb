package ru.develgame.audiogames.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("userBean")
public class UserBean implements Serializable {
    public String getUsername() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }
}
