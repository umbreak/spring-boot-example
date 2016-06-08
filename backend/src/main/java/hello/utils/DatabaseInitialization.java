package hello.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.i18n.CountryCode;
import com.neovisionaries.i18n.CurrencyCode;
import hello.jpa.dao.*;
import hello.jpa.model.*;
import model.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

/**
 * Created by didac on 18.05.16.
 */
public class DatabaseInitialization {
    private final SurveyDAO surveyRepository;
    private final TargetDAO targetRepository;
    private final InformationProviderDAO informationProviderRepository;
    private final InformationRequesterDAO userRepository;

    private final RoleDAO roleRepository;


    public DatabaseInitialization(SurveyDAO surveyRepository, TargetDAO targetRepository, InformationProviderDAO informationProviderRepository, InformationRequesterDAO userRepository, RoleDAO roleRepository) {
        this.surveyRepository = surveyRepository;
        this.targetRepository = targetRepository;
        this.informationProviderRepository = informationProviderRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void init() throws JsonProcessingException {
        //Create Roles
/*        Map<String,RoleEntity> roles=new HashMap<>();
        for (String roleString : Arrays.asList("USER", "SUBSCRIBER", "UNLIMITED")) {
            RoleEntity role=new RoleEntity();
            role.setName(roleString);
            roles.put(roleString,role);
        }

        //Create InformationProviders
        Map<String,InformationProviderEntity> providers=new HashMap<>();
        for (String providerString : Arrays.asList("Provider A", "Provider B", "Provider C")) {
            InformationProviderEntity provider=new InformationProviderEntity();
            provider.setName(providerString);
            //Setting roles
            Set<RoleEntity> rolesSet=new HashSet<>(roles.values());
            if(providerString.equals("Provider A")){
                //All roles
                provider.setRoles(rolesSet);
            }else if(providerString.equals("Provider B")){
                //all roles except for USER
                rolesSet.remove(roles.get("USER"));
                provider.setRoles(rolesSet);
            }else{
                //Only UNLIMITED roles
                rolesSet=new HashSet<>();
                rolesSet.add(roles.get("UNLIMITED"));
                provider.setRoles(rolesSet);
            }


            roleRepository.save(new ArrayList(provider.getRoles()));
            informationProviderRepository.save(provider);
            providers.put(providerString,provider);
        }

        //Create users
        createUsersWithRoles(roles);

        //Create target entities
        List<TargetEntity> targets=new ArrayList<>();
        //18
        String names[]=new String[]{"Aaron", "Adam", "Anton", "Juan", "Marcos", "Fran", "Arnau", "Guillem", "Laura", "Sara", "Paula", "Sandra", "Cristina", "Sophie", "Monique", "Anna", "Maria", "Franzi"};
        Random random=new Random();
        for (int i = 0; i < names.length; i++) {
            String name=names[i];
            Gender gender=i < 8 ? Gender.Male : Gender.Female;
            //salary from 1000 to 4000
            int income=1000 + random.nextInt((4000 - 1000) + 1);
            //age from 20 to 60
            int age= 20 + random.nextInt((60 - 20) + 1);
            TargetEntity targetEntity = new TargetEntity(name, gender, CurrencyCode.EUR, income, age);
            targets.add(targetEntity);
        }

        //Create Survey (6) with 6 targets each
        Collections.shuffle(targets);
        Map<String,SurveyEntity> surveys=new HashMap<>();
        String subjects[] =new String[]{"Science studies", "Engineering studies", "Economics studies", "No studies", "Agricultural studies", "Arts studies"};
        int j=0;
        for (int i = 0; i < subjects.length; i++) {
            String subject=subjects[i];
            CountryCode code= random.nextBoolean() ? CountryCode.ES : CountryCode.DE;
            SurveyEntity surveyEntity = new SurveyEntity(subject, code, null);
            if(i < 2){
                surveyEntity.setProvider(providers.get("Provider A"));
            }else if(i < 4){
                surveyEntity.setProvider(providers.get("Provider B"));
            }else{
                surveyEntity.setProvider(providers.get("Provider C"));
            }
            surveyRepository.save(surveyEntity);
            List<TargetEntity> targetEntities = targets.subList(j, j + 3);
            j=j+3;
            for (TargetEntity targetEntity : targetEntities) {
                targetEntity.setSurveyID(surveyEntity.getId());
            }
            targetRepository.save(targetEntities);
            surveys.put(subject,surveyEntity);
        }*/
    }

    private void createUsersWithRoles(Map<String,RoleEntity> roles){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Set<RoleEntity> rolesUser=new HashSet<>();
        RoleEntity roleUser = roles.get("USER");
        rolesUser.add(roleUser);

        //user1 -> secret1 -> USER
        InformationRequesterEntity user=
                new InformationRequesterEntity("user1",encoder.encode("secret1"),"Montero", "umbreak@gmail.com", "http://callback.url", "ftp.test.com", "ftpUser1", "ftpPass1",rolesUser);
        userRepository.save(user);

        //user2 -> secret2 -> USER
        InformationRequesterEntity user2=
                new InformationRequesterEntity("user2",encoder.encode("secret2"),"Papa", "umbreak2@gmail.com", "http://callback.url", "ftp.test.com", "ftpUser2", "ftpPass2",rolesUser);
        userRepository.save(user2);

        //user3 -> secret3 -> SUBSCRIBER
        rolesUser.add(roles.get("SUBSCRIBER"));
        InformationRequesterEntity user3=
                new InformationRequesterEntity("user3",encoder.encode("secret3"),"Papaa", "umbreak3@gmail.com", "http://callback.url", "ftp.test.com", "ftpUser2", "ftpPass2",rolesUser);
        userRepository.save(user3);


        //user4 -> secret4 -> UNLIMITED
        rolesUser.clear();
        rolesUser.add(roles.get("SUBSCRIBER"));
        InformationRequesterEntity user4=
                new InformationRequesterEntity("user4",encoder.encode("secret4"),"Mendez", "umbreak4@gmail.com", "http://callback.url", "ftp.test.com", "ftpUser2", "ftpPass2",rolesUser);
        userRepository.save(user4);
    }


}
