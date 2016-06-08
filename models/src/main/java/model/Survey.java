package model;

import com.neovisionaries.i18n.CountryCode;

import java.util.Date;
import java.util.List;

public class Survey {
    public static class Request {
        public Request() {
        }

        public Request(Builder builder) {
            this.target=builder.target;
            this.subject=builder.subject;
            this.country=builder.country;
        }

        private Target.Request target;
        private String subject;
        private CountryCode country;


        public static class Builder{
            private final Target.Request target;
            private final String subject;
            //optional fields
            private CountryCode country;

            public Builder(Target.Request target, String subject) {
                this.target = target;
                this.subject = subject;
            }
            public Builder country(CountryCode value){country=value; return this;}
            public Request build(){
                return new Request(this);
            }
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Target.Request getTarget() {
            return target;
        }

        public void setTarget(Target.Request target) {
            this.target = target;
        }

        public CountryCode getCountry() {
            return country;
        }

        public void setCountry(CountryCode country) {
            this.country = country;
        }

    }
    public static class Response{

        private String subject;
        private Date date;
        private List<Target.Response> targets;

        public Response() {
        }

        public Response(String subject, List<Target.Response> targets, Date date) {
            this.subject = subject;
            this.targets = targets;
            this.date = date;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public List<Target.Response> getTargets() {
            return targets;
        }

        public void setTargets(List<Target.Response> targets) {
            this.targets = targets;
        }
    }
}
