package application;

import application.data.FlightsRepository;
import application.model.Flight;
import application.services.FlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Application {

    private final FlightsService flightsService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        flightsService.save(new Flight("IEV-ALC","Київ Жуляни","Іспанія. Аліканте"));
        flightsService.save(new Flight("IEV-BCN","Київ Жуляни","Іспанія. Барселона Ель Прат"));
        flightsService.save(new Flight("IEV-MAD","Київ Жуляни","Іспанія. Мадрид"));
        flightsService.save(new Flight("IEV-AGP","Київ Жуляни","Іспанія. Малага"));
        flightsService.save(new Flight("IEV-PMI","Київ Жуляни","Іспанія. Пальма де Мальорка"));
        flightsService.save(new Flight("IEV-VCE","Київ Жуляни","Італія. Аеропорт Венеції ім.Марко Поло"));
        flightsService.save(new Flight("IEV-BLG","Київ Жуляни","Італія. Болон'я"));
        flightsService.save(new Flight("IEV-TSF","Київ Жуляни","Італія. Венеція Тревізо"));
        flightsService.save(new Flight("IEV-CTA","Київ Жуляни","Італія. Катанія (Сицилія)"));
        flightsService.save(new Flight("IEV-MXP","Київ Жуляни","Італія. Мілан Мальпенса"));
        flightsService.save(new Flight("IEV-NAP","Київ Жуляни","Італія. Неаполь"));
        flightsService.save(new Flight("IEV-FCO","Київ Жуляни","Італія. Рим Ф'юмічіно"));
        flightsService.save(new Flight("IEV-VIE","Київ Жуляни","Австрія. Відень"));
        flightsService.save(new Flight("IEV-SZG","Київ Жуляни","Австрія. Зальцбург"));
        flightsService.save(new Flight("IEV-CRL","Київ Жуляни","Бельгія. Брюссель Шарлеруа"));
        flightsService.save(new Flight("IEV-BOJ","Київ Жуляни","Болгарія. Бурґас"));
        flightsService.save(new Flight("IEV-ATH","Київ Жуляни","Греція. Афіни"));
        flightsService.save(new Flight("IEV-CHQ","Київ Жуляни","Греція. Крит"));
        flightsService.save(new Flight("IEV-SKG","Київ Жуляни","Греція. Салоніки"));
        flightsService.save(new Flight("IEV-BLL","Київ Жуляни","Данія. Біллунн"));
        flightsService.save(new Flight("IEV-CPH","Київ Жуляни","Данія. Копенгаґен"));
        flightsService.save(new Flight("IEV-TLL","Київ Жуляни","Естонія. Таллінн"));
        flightsService.save(new Flight("IEV-LCA","Київ Жуляни","Кіпр. Ларнака"));
        flightsService.save(new Flight("IEV-RIX","Київ Жуляни","Латвія. Рига"));
        flightsService.save(new Flight("IEV-VNO","Київ Жуляни","Литва. Вільнюс"));
        flightsService.save(new Flight("IEV-PLQ","Київ Жуляни","Литва. Паланга – Клайпеда"));
        flightsService.save(new Flight("IEV-EIN","Київ Жуляни","Нідерланди. Ейндговен"));
        flightsService.save(new Flight("IEV-BER","Київ Жуляни","Німеччина. Аеропорт Берлін-Бранденбург -  термінал 2"));
        flightsService.save(new Flight("IEV-HAM","Київ Жуляни","Німеччина. Гамбург"));
        flightsService.save(new Flight("IEV-DTM","Київ Жуляни","Німеччина. Дортмунд"));
        flightsService.save(new Flight("IEV-CGN","Київ Жуляни","Німеччина. Кельн"));
        flightsService.save(new Flight("IEV-FMM","Київ Жуляни","Німеччина. Меммінґен/Мюнхен Вест"));
        flightsService.save(new Flight("IEV-NUE","Київ Жуляни","Німеччина. Нюрнберг"));
        flightsService.save(new Flight("IEV-LTN","Київ Жуляни","Великобританія. Лондон Лутон"));
        flightsService.save(new Flight("IEV-AUH","Київ Жуляни","Об'єднані Арабські Емірати. Абу-Дабі"));
        flightsService.save(new Flight("IEV-WAW","Київ Жуляни","Польща. Варшава Шопен"));
        flightsService.save(new Flight("IEV-WRO","Київ Жуляни","Польща. Вроцлав"));
        flightsService.save(new Flight("IEV-GDN","Київ Жуляни","Польща. Гданськ"));
        flightsService.save(new Flight("IEV-KTW","Київ Жуляни","Польща. Катовіце"));
        flightsService.save(new Flight("IEV-KRK","Київ Жуляни","Польща. Краків"));
        flightsService.save(new Flight("IEV-POZ","Київ Жуляни","Польща. Познань"));
        flightsService.save(new Flight("IEV-LIS","Київ Жуляни","Португалія. Лісабон"));
        flightsService.save(new Flight("IEV-OPO","Київ Жуляни","Португалія. Порту"));
        flightsService.save(new Flight("IEV-BTS","Київ Жуляни","Словаччина. Братислава"));
        flightsService.save(new Flight("IEV-BUD","Київ Жуляни","Угорщина. Будапешт"));
        flightsService.save(new Flight("IEV-DEB","Київ Жуляни","Угорщина. Дебрецен"));
        flightsService.save(new Flight("IEV-MLH","Київ Жуляни","Франція. Базель-Мюльхаус-Фрайбург"));
        flightsService.save(new Flight("IEV-NCE","Київ Жуляни","Франція. Ніцца"));
        flightsService.save(new Flight("IEV-BVA","Київ Жуляни","Франція. Париж Бове"));
        flightsService.save(new Flight("IEV-PED","Київ Жуляни","Чеська Республіка. Пардубиці"));
        flightsService.save(new Flight("IEV-BSL","Київ Жуляни","Швейцарія. Базель-Мюльхаус-Фрайбург"));
        flightsService.save(new Flight("IEV-NYO","Київ Жуляни","Швеція. Стокгольм Скавста"));
    }

}
