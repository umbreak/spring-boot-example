package model;

import java.util.List;

public class MarketSurveysResponse {
    private Pagination.Response page;
    private Identifier provider;
    private List<Survey.Response> survey;

    public MarketSurveysResponse() {}

    public MarketSurveysResponse(Pagination.Response page, Identifier provider, List<Survey.Response> survey) {
        this.page = page;
        this.provider = provider;
        this.survey = survey;
    }

    public static MarketSurveysResponse empty(Identifier provider, int pageSize){
        Pagination.Response page=new Pagination.Response(0,pageSize,0,0);
        return new MarketSurveysResponse(page,provider, null);
    }

    public Identifier getProvider() {
        return provider;
    }

    public void setProvider(Identifier provider) {
        this.provider = provider;
    }


    public List<Survey.Response> getSurvey() {
        return survey;
    }

    public void setSurvey(List<Survey.Response> survey) {
        this.survey = survey;
    }

    public Pagination.Response getPage() {
        return page;
    }

    public void setPage(Pagination.Response page) {
        this.page = page;
    }
}
