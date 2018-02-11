package dbwriter.controller;


import dbwriter.model.Lkm;
//import dbwriter.model.LkmBases;
import dbwriter.repository.LkmBaseRepository;
import dbwriter.repository.LkmRepository;
import dbwriter.repository.LkmprefixRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


//аннотация, которая указывает Spring Boot, что это контроллер
@Controller
@RequestMapping(path="/demo")                                           //первая часть адреса, который будет обрабатываться контроллером
public class MainController {
    @Autowired                                                          //аннотация, которая автоматически связывает бины
    private LkmBaseRepository lkmBaseRepository;

    @Autowired
    private LkmRepository lkmRepository;

    @Autowired
    private LkmprefixRepository lkmPrefixRepository;


    //главный контроллер, который обрабатывает основную страницу
    @GetMapping(path="/index")                                          //часть адреса, указывает, что его будет обрабатывать этот контроллер
    public ModelAndView getAllLkm(){
        System.out.println("Page loaded");
        Iterable<Lkm> a = lkmRepository.findAll();                      //получаем массив всех записей из базы
        return new ModelAndView("index","lkm",a);   //помещаем полученные из базы записи в модель, которая будет называться lkm (под этим именем
        //ее будет обрабатывать страница, index - это представление, которое будет обрабатывать нашу модель
    }


    //контроллер, который возвращает одну запись по id
    @GetMapping(path="/findbyid")                                           //часть адреса, указывает, что его будет обрабатывать этот контроллер
    public @ResponseBody Lkm getById(@RequestParam String id) {             //@ResponseBody - ответ, который контроллер возвращает по запросу в формате JSON
                                                                            //@RequestParam - запрос, который приходит контроллеру. Это просто id запрашиваемой записи
        System.out.println("Page read "+lkmRepository.findOne(Integer.parseInt(id)).getName());
        return lkmRepository.findOne(Integer.parseInt(id));                 //возвращаем запись
    }


    //контроллер, который обрабатывает запрос на сохранение старой записи
    @GetMapping(path="/saverecord")                                         //часть адреса, указывает, что его будет обрабатывать этот контроллер
    public @ResponseBody String saveChangedEntity(@RequestParam String JSONString) {    //@RequestParam - запрос, который приходит контроллеру. Это измененная запись в JSON-формате
        JSONObject a= new JSONObject(JSONString);                           //преобразуем полученную строку в JSON-формат
        //создаем сущность конструктором
        Lkm savedLkm=new Lkm(
                a.get("id").toString(),
                a.get("name").toString(),
                lkmBaseRepository.findOne(Integer.parseInt(a.get("lkmBases").toString())),
                lkmPrefixRepository.findOne(1),
                a.get("shortDescription").toString(),
                a.get("description").toString(),
                a.get("destination").toString(),
                a.get("colors").toString(),
                a.get("useInstruction").toString(),
                a.get("layers").toString(),
                a.get("spreadingCapasity").toString(),
                a.get("layerThickness").toString(),
                a.get("ductility_thickness").toString(),
                a.get("dryingtime").toString(),
                a.get("hardness").toString(),
                a.get("charge").toString(),
                a.get("solids").toString()
        );
        lkmRepository.save(savedLkm);                       //сохраняем сущность в базе
        System.out.println(savedLkm.getName()+" saved");
        return savedLkm.getId().toString();                 //возвращаем сообщение о записи представлению
    }


    //контроллер, который обрабатывает запрос на сохранение новой записи работает аналогично контроллеру saveChangedEntity
    @GetMapping(path="/savenewrecord")
    public @ResponseBody String saveNewEntity(@RequestParam String JSONString) {
        JSONObject a= new JSONObject(JSONString);
        Lkm newLkm=new Lkm(
                a.get("name").toString(),
                lkmBaseRepository.findOne(Integer.parseInt(a.get("lkmBases").toString())),
                lkmPrefixRepository.findOne(1),
                a.get("shortDescription").toString(),
                a.get("description").toString(),
                a.get("destination").toString(),
                a.get("colors").toString(),
                a.get("useInstruction").toString(),
                a.get("layers").toString(),
                a.get("spreadingCapasity").toString(),
                a.get("layerThickness").toString(),
                a.get("ductility_thickness").toString(),
                a.get("dryingtime").toString(),
                a.get("hardness").toString(),
                a.get("charge").toString(),
                a.get("solids").toString()
        );
        lkmRepository.save(newLkm);
        System.out.println("New entity "+newLkm.getName()+" saved");
        return newLkm.getId().toString();
    }

    //контроллер для обработки запроса на удаление записи
    @GetMapping(path="/deleterecord")                                   //часть адреса, указывает, что его будет обрабатывать этот контроллер
    public @ResponseBody String deleteEntity(@RequestParam String id) { //возвращат сообщение об удалении. Входящий параметр - id удаляемой записи
        lkmRepository.delete(Integer.parseInt(id));                     //удаляем запись
        System.out.println("lkm with id "+id+" deleted");
        return "record deleted";
    }

    //контроллер для удаленного завершения работы приложения
    @GetMapping(path="/exit")                   //адрес, который будет обрабатывать контроллер
    public @ResponseBody void endAplication() {
        System.exit(100);                //завершить приложение с кодом 100
    }

    /*
    @GetMapping(path="/getlkmbases")
    public @ResponseBody Iterable<LkmBases> getAllLkmBases(){
        System.out.println("controller at work");
        return lkmBaseRepository.findAll();
    }
*/



}
