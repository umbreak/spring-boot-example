package model;

import java.util.List;


public class MarketSurveysRequest {
    private Pagination.Request page;
    private Identifier provider;
    private Survey.Request survey;
    private Subscription subscription;

    public MarketSurveysRequest() {}

    public MarketSurveysRequest(Builder builder) {
        this.provider=builder.provider;
        this.survey=builder.survey;
        this.subscription=builder.subscription;
        this.page=builder.page;
    }


    public static class Builder {
        // Required parameters
        private final Identifier provider;
        private final Survey.Request survey;

        // Optional parameters - initialized to default values
        private Pagination.Request page;

        private Subscription subscription;

        public Builder(Identifier provider, Survey.Request survey) {
            this.provider = provider;
            this.survey = survey;
        }

        public Builder page(Pagination.Request val){page=val; return this;}
        public Builder subscription(Subscription val){ subscription = val; return this; }
        public MarketSurveysRequest build() {
            return new MarketSurveysRequest(this);
        }
    }


    public Identifier getProvider() {
        return provider;
    }

    public void setProvider(Identifier provider) {
        this.provider = provider;
    }

    public Survey.Request getSurvey() {
        return survey;
    }

    public void setSurvey(Survey.Request survey) {
        this.survey = survey;
    }

    public Pagination.Request getPage() {
        return page;
    }

    public void setPage(Pagination.Request page) {
        this.page = page;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public static class Subscription{
        public Subscription() {
        }

        public Subscription(Frequency frequency, Channel channel) {
            this.frequency = frequency;
            this.channel = channel;
        }

        public enum Frequency{Dayly, Weekly, Monthly, Yearly}
        public enum Channel{Mail, FTP, API}

        private Frequency frequency;
        private Channel channel;

        public Frequency getFrequency() {
            return frequency;
        }

        public void setFrequency(Frequency frequency) {
            this.frequency = frequency;
        }

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }
    }
}
