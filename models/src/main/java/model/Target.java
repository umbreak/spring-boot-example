package model;

import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;

import java.util.Arrays;
import java.util.List;

public class Target {

    public static class Request{

        public Request() {
        }
        public Request(Builder builder) {
            this.age=builder.age;
            this.gender=builder.gender;
            this.income=builder.income;
        }

        private Gender gender;
        //range of ages
        private List<Integer> age;
        //range of income
        private CurrencyRange income;

        public static class Builder{
            private Gender gender;
            private List<Integer> age;
            private CurrencyRange income;

            public Builder gender(Gender value){gender=value; return this;}
            public Builder ageRange(int min, int max){age = Arrays.asList(min,max); return this;}
            public Builder incomeRange(CurrencyCode currency, int min, int max){income=new CurrencyRange(currency,min,max); return this;}

            public Request build(){
                return new Request(this);
            }
        }

        public static class CurrencyRange {
            private CurrencyCode currency;
            private List<Integer> range;

            public CurrencyRange() {
            }

            public CurrencyRange(CurrencyCode currency, int min, int max) {
                this.currency = currency;
                range = Arrays.asList(min,max);
            }

            public CurrencyCode getCurrency() {
                return currency;
            }

            public void setCurrency(CurrencyCode currency) {
                this.currency = currency;
            }

            public List<Integer> getRange() {
                return range;
            }

            public void setRange(List<Integer> range) {
                this.range = range;
            }
        }


        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public List<Integer> getAge() {
            return age;
        }

        public void setAge(List<Integer> age) {
            this.age = age;
        }

        public CurrencyRange getIncome() {
            return income;
        }

        public void setIncome(CurrencyRange income) {
            this.income = income;
        }
    }

    public static class Response{
        private String name;
        private String surname;
        private Gender gender;
        private Integer age;
        private Currency income;

        public Response() {
        }

        public Response(Builder builder) {
            this.name=builder.name;
            this.surname=builder.surname;
            this.gender=builder.gender;
            this.age=builder.age;
            this.income=builder.income;
        }


        public static class Builder{
            private String name;
            private String surname;
            private Gender gender;
            private Integer age;
            private Currency income;

            public Builder name(String value){name=value; return this;}
            public Builder surname(String value){surname=value; return this;}
            public Builder gender(Gender value){gender=value; return this;}
            public Builder age(Integer value){age=value; return this;}
            public Builder income(Currency value){income=value; return this;}

            public Response build(){
                return new Response(this);
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public Currency getIncome() {
            return income;
        }

        public void setIncome(Currency income) {
            this.income = income;
        }
    }



}
