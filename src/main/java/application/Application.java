package application;

import application.data.FlightsRepository;
import application.model.Flight;
import application.model.FlightEvent;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
@RestController @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Application {

    private final FlightsService flightsService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private Date parseDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            return new Date(0);
        }
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() {
        flightsService.save(new Flight("IEV-ALC", "Київ Жуляни", "Іспанія. Аліканте", FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BCN","Київ Жуляни","Іспанія. Барселона Ель Прат", FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-MAD","Київ Жуляни","Іспанія. Мадрид",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-AGP","Київ Жуляни","Іспанія. Малага",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-PMI","Київ Жуляни","Іспанія. Пальма де Мальорка",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-VCE","Київ Жуляни","Італія. Аеропорт Венеції ім.Марко Поло",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BLG","Київ Жуляни","Італія. Болон'я",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-TSF","Київ Жуляни","Італія. Венеція Тревізо",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-CTA","Київ Жуляни","Італія. Катанія (Сицилія)",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-MXP","Київ Жуляни","Італія. Мілан Мальпенса",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-NAP","Київ Жуляни","Італія. Неаполь",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-FCO","Київ Жуляни","Італія. Рим Ф'юмічіно",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-VIE","Київ Жуляни","Австрія. Відень",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-SZG","Київ Жуляни","Австрія. Зальцбург",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-CRL","Київ Жуляни","Бельгія. Брюссель Шарлеруа",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BOJ","Київ Жуляни","Болгарія. Бурґас",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-ATH","Київ Жуляни","Греція. Афіни",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-CHQ","Київ Жуляни","Греція. Крит",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-SKG","Київ Жуляни","Греція. Салоніки",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BLL","Київ Жуляни","Данія. Біллунн",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-CPH","Київ Жуляни","Данія. Копенгаґен",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-TLL","Київ Жуляни","Естонія. Таллінн",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-LCA","Київ Жуляни","Кіпр. Ларнака",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-RIX","Київ Жуляни","Латвія. Рига",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-VNO","Київ Жуляни","Литва. Вільнюс",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-PLQ","Київ Жуляни","Литва. Паланга – Клайпеда",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-EIN","Київ Жуляни","Нідерланди. Ейндговен",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BER","Київ Жуляни","Німеччина. Аеропорт Берлін-Бранденбург -  термінал 2",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-HAM","Київ Жуляни","Німеччина. Гамбург",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-DTM","Київ Жуляни","Німеччина. Дортмунд",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-CGN","Київ Жуляни","Німеччина. Кельн",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-FMM","Київ Жуляни","Німеччина. Меммінґен/Мюнхен Вест",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-NUE","Київ Жуляни","Німеччина. Нюрнберг",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-LTN","Київ Жуляни","Великобританія. Лондон Лутон",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-AUH","Київ Жуляни","Об'єднані Арабські Емірати. Абу-Дабі",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-WAW","Київ Жуляни","Польща. Варшава Шопен",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-WRO","Київ Жуляни","Польща. Вроцлав",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-GDN","Київ Жуляни","Польща. Гданськ",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-KTW","Київ Жуляни","Польща. Катовіце",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-KRK","Київ Жуляни","Польща. Краків",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-POZ","Київ Жуляни","Польща. Познань",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-LIS","Київ Жуляни","Португалія. Лісабон",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-OPO","Київ Жуляни","Португалія. Порту",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BTS","Київ Жуляни","Словаччина. Братислава",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BUD","Київ Жуляни","Угорщина. Будапешт",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-DEB","Київ Жуляни","Угорщина. Дебрецен",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-MLH","Київ Жуляни","Франція. Базель-Мюльхаус-Фрайбург",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-NCE","Київ Жуляни","Франція. Ніцца",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BVA","Київ Жуляни","Франція. Париж Бове",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-PED","Київ Жуляни","Чеська Республіка. Пардубиці",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-BSL","Київ Жуляни","Швейцарія. Базель-Мюльхаус-Фрайбург",FlightEvent.randoms(3)));
        flightsService.save(new Flight("IEV-NYO","Київ Жуляни","Швеція. Стокгольм Скавста",FlightEvent.randoms(3)));
    }

}
