package hello;


import hello.subscription.FrequencyImpl;
import model.MarketSurveysRequest;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class FrequencyImplTest {

    @Test
    public void testFrequencyDaylyImplement(){
        FrequencyImpl day=FrequencyImpl.fromSubscriptionFrequency(MarketSurveysRequest.Subscription.Frequency.Dayly);
        Assert.assertEquals(day, FrequencyImpl.Dayly);
        Date now = new Date();
        Assert.assertFalse(day.hasTimeFromLastDatePassed(now));

        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(1).plusMinutes(3).toDate()));
        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(2).plusMinutes(3).toDate()));
        Assert.assertFalse(day.hasTimeFromLastDatePassed(new DateTime().minusDays(1).plusMinutes(20).toDate()));
    }

    @Test
    public void testFrequencyWeeklymplement(){
        FrequencyImpl day=FrequencyImpl.fromSubscriptionFrequency(MarketSurveysRequest.Subscription.Frequency.Weekly);
        Assert.assertEquals(day, FrequencyImpl.Weekly);
        Date now = new Date();
        Assert.assertFalse(day.hasTimeFromLastDatePassed(now));

        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(7).plusMinutes(3).toDate()));
        Assert.assertFalse(day.hasTimeFromLastDatePassed(new DateTime().minusDays(7).plusMinutes(7).toDate()));
        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(10).toDate()));
    }

    @Test
    public void testFrequencyMonthlymplement(){
        FrequencyImpl day=FrequencyImpl.fromSubscriptionFrequency(MarketSurveysRequest.Subscription.Frequency.Monthly);
        Assert.assertEquals(day, FrequencyImpl.Monthly);
        Date now = new Date();
        Assert.assertFalse(day.hasTimeFromLastDatePassed(now));

        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(30).plusMinutes(3).toDate()));
        Assert.assertFalse(day.hasTimeFromLastDatePassed(new DateTime().minusDays(30).plusMinutes(7).toDate()));
        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(50).toDate()));
    }

    @Test
    public void testFrequencyYearlymplement(){
        FrequencyImpl day=FrequencyImpl.fromSubscriptionFrequency(MarketSurveysRequest.Subscription.Frequency.Yearly);
        Assert.assertEquals(day, FrequencyImpl.Yearly);
        Date now = new Date();
        Assert.assertFalse(day.hasTimeFromLastDatePassed(now));

        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(365).plusMinutes(3).toDate()));
        Assert.assertFalse(day.hasTimeFromLastDatePassed(new DateTime().minusDays(365).plusMinutes(7).toDate()));
        Assert.assertTrue(day.hasTimeFromLastDatePassed(new DateTime().minusDays(400).toDate()));
    }
}
