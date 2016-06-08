package hello.jpa.model;

import com.neovisionaries.i18n.CurrencyCode;
import model.Gender;

import javax.persistence.*;

@Entity
public class TargetEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(name="survey_id")
    private long surveyID;

    private String name;

    private String surname;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CurrencyCode incomeCurrency;

    private Integer incomeAmount;

    private Integer age;

    public TargetEntity() {
    }

    public TargetEntity(String name, Gender gender, CurrencyCode incomeCurrency, Integer incomeAmount, Integer age) {
        this.name = name;
        this.gender = gender;
        this.incomeCurrency = incomeCurrency;
        this.incomeAmount = incomeAmount;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public CurrencyCode getIncomeCurrency() {
        return incomeCurrency;
    }

    public void setIncomeCurrency(CurrencyCode incomeCurrency) {
        this.incomeCurrency = incomeCurrency;
    }

    public Integer getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Integer incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public long getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(long surveyID) {
        this.surveyID = surveyID;
    }
}
