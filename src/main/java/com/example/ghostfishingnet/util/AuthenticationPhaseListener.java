package com.example.ghostfishingnet.domain.auth.controller;

import com.example.ghostfishingnet.beans.AuthenticationBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AuthenticationPhaseListener implements PhaseListener {

    @Inject
    private AuthenticationBean authenticationBean;

    @Override
    public void afterPhase(PhaseEvent event) {
        FacesContext facesContext = event.getFacesContext();
        String viewId = facesContext.getViewRoot().getViewId();

        if ("/dashboard.xhtml".equals(viewId)) {
            if (!authenticationBean.isLoggedIn()) {
                try {
                    String contextPath = facesContext.getExternalContext().getRequestContextPath();
                    ((HttpServletResponse) facesContext.getExternalContext().getResponse()).sendRedirect(contextPath + "/login.xhtml");
                    facesContext.responseComplete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        // No action required before phase
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
