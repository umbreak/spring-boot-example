package hello.subscription;


import model.MarketSurveysRequest;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public enum FrequencyImpl {

    Dayly{
        @Override
        public boolean hasTimeFromLastDatePassed(Date lastSent) {
            if(lastSent == null) return true;
            return getDateDiff(lastSent, getDate5MinInFuture(),TimeUnit.DAYS) >= 1;
        }

    }, Weekly{
        @Override
        public boolean hasTimeFromLastDatePassed(Date lastSent) {
            if(lastSent == null) return true;
            return getDateDiff(lastSent, getDate5MinInFuture(),TimeUnit.DAYS) >= 7;
        }

    }, Monthly{
        @Override
        public boolean hasTimeFromLastDatePassed(Date lastSent) {
            if(lastSent == null) return true;
            //Note: this is not totally accurate, but average case
            return getDateDiff(lastSent, getDate5MinInFuture(),TimeUnit.DAYS) >= 30;
        }

    }, Yearly{
        @Override
        public boolean hasTimeFromLastDatePassed(Date lastSent) {
            if(lastSent == null) return true;
            //Note: this is not totally accurate, but average case
            return getDateDiff(lastSent, getDate5MinInFuture(),TimeUnit.DAYS) >= 365;
        }
    };

    public abstract boolean hasTimeFromLastDatePassed(Date lastSent);

    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }


    Date getDate5MinInFuture(){
        return new DateTime().plusMinutes(5).toDate();
    }
    public static FrequencyImpl fromSubscriptionFrequency(MarketSurveysRequest.Subscription.Frequency freq){
        return FrequencyImpl.valueOf(freq.name());
    }
}
