package vu.mif.usecases;

import vu.mif.interceptors.LoggedInvocation;
import vu.mif.services.UndercoverNameGenerator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Named
@SessionScoped
public class GenerateUndercoverName implements Serializable {
    @Inject
    UndercoverNameGenerator undercoverNameGenerator;

    private CompletableFuture<String> generationTask = null;

    @LoggedInvocation
    public String generateNewUndercoverName() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        generationTask = CompletableFuture.supplyAsync(() -> undercoverNameGenerator.generateUndercoverName());
        return  "/soldierDetails.xhtml?faces-redirect=true&soldierId=" + requestParameters.get("soldierId");
    }

    public boolean isGenerationRunning() {
        return generationTask != null && !generationTask.isDone();
    }

    public String getGenerationStatus() throws ExecutionException, InterruptedException {
        if (generationTask == null) {
            return null;
        } else if (isGenerationRunning()) {
            return "Undercover Name generation in progress";
        }
        return "Suggested undercover spy name: " + generationTask.get();
    }
}

