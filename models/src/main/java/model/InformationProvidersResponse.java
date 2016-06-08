package model;

import java.util.List;

public class InformationProvidersResponse {
    private List<Identifier> provider;

    public InformationProvidersResponse() {}


    public InformationProvidersResponse(List<Identifier> provider) {
        this.provider = provider;
    }

    public List<Identifier> getProvider() {
        return provider;
    }

    public void setProvider(List<Identifier> provider) {
        this.provider = provider;
    }
}
