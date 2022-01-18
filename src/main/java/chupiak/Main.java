package chupiak;

import chupiak.config.AppConfig;
import chupiak.model.Client;
import chupiak.model.NumberPhone;
import chupiak.model.PersonalInfo;
import chupiak.service.ClientService;
import chupiak.service.NumberPhoneService;
import chupiak.service.PersonalInfoService;
import java.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        ClientService clientService = context.getBean(ClientService.class);
        final NumberPhoneService numberPhoneService = context.getBean(NumberPhoneService.class);
        final PersonalInfoService personalInfoService = context.getBean(PersonalInfoService.class);

        Client client = new Client();
        clientService.add(client);

        NumberPhone numberPhone = new NumberPhone();
        numberPhone.setClientId(client);
        numberPhone.setPhoneNumber("+380671112233");
        numberPhone.setPhoneType(1);
        numberPhoneService.add(numberPhone);

        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFio("Ivanov Ivan");
        personalInfo.setPassportData("MO 011599");
        personalInfo.setBirthday(LocalDate.parse("1978-07-21"));
        personalInfo.setClientId(client);
        personalInfoService.add(personalInfo);

        clientService.addClient("Ivanov Ivan", "911");
        System.out.println(clientService.getById(1L));
        System.out.println(numberPhoneService.getById(1L));
        System.out.println(personalInfoService.getById(1L));
        System.out.println(clientService.infoAboutClient(1L));

    }
}
