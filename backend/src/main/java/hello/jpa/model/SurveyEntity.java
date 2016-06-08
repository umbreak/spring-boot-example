package hello.jpa.model;

import com.neovisionaries.i18n.CountryCode;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class SurveyEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String subject;

    private Date date;


    @OneToMany
    @JoinColumn(name="survey_id")
    private List<TargetEntity> target;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="provider_id")
    private InformationProviderEntity provider;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    public SurveyEntity() {
    }

    public SurveyEntity(String subject, CountryCode country, List<TargetEntity> target) {
        this.subject = subject;
        this.target = target;
        this.country = country;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<TargetEntity> getTarget() {
        return target;
    }

    public void setTarget(List<TargetEntity> target) {
        this.target = target;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    public InformationProviderEntity getProvider() {
        return provider;
    }

    public void setProvider(InformationProviderEntity provider) {
        this.provider = provider;
    }
}
