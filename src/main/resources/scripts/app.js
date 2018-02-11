
    var savedRecord;                            //сюда пишется последняя сохраненная запись в формате JSON
    var savedRecordAsObject;                    //сюда пишется последняя сохраненная запись как JS-объект
    var currentRecord;                          //сюда пишется текущее состаяние записи в формате JSON
    var currentRecordAsObject={};               //сюда пишется текущее состаяние записи в виде JS-объекта
    //эта переменная костыль. Нужна для того, чтобы сопоставить текстовому названию id в базе данных в таблице lkmbases. Переделать, чтобы заполнялся програмно
    var lkmbasesMenu={
        "Алкидноуретановые": 1,
        "Полиуретановые": 2,
        "Глифталевые": 3,
        "Фенолоалкидные": 4,
        "Кремнийорганические": 5,
        "Фенольные": 6,
        "Меламиновые": 7,
        "Циклогексаноновые": 8,
        "Мочевинные (карбамидные)": 9,
        "Эпоксидные": 10,
        "Полиэфирные насыщенные": 11,
        "Этрифталевые": 12,
        "Пентафталевые": 13,
        "Эпоксиэфирные": 14,
        "Полиэфирные ненасыщенные": 15,
        "Полиакрилатные": 16,
        "алкидно-акриловые краски": 17,
        "Масляно- и алкидностирольные": 18,
        "Поливинилацетатные": 19,
        "Нефтеполимерные": 20,
        "Поливинилацетальные": 21,
        "Фторопластовые": 22,
        "На основе сополимеров винилацетата": 23,
        "Перхлорвиниловые": 24,
        "Каучуковые": 25,
        "На основе сополимеров винилхлорида": 26,
        "Битумные": 27,
        "Шеллачные": 28,
        "Канифольные": 29,
        "Янтарные": 30,
        "Масляные": 31,
        "Ацетобутиратоцеллюлозные": 32,
        "Нитроцеллюлозные": 33,
        "Ацетилцеллюлозные": 34,
        "Этилцеллюлозные": 35
    }


    //функция, которая определяет id объекта, из которого она была вызвана (событие onclick), отправляет запрос контроллеру для получения подробной информации по объекту,
    // и заполняет детали в форме lkmdata. Входящий аргумент - элемент html-кода, из которого функция была вызвана
    function showDetails(el) {
        //выделяем код в отдельную функцию, чтобы обернуть ее функцией проверки на изменения
        function f(){
            id = el.id;                                             //определяем id вызвовшего функцию элемента
            url = "/demo/findbyid";                                 //адрес, на который будем делать запрос
            $.get(url, {id: id}).done(                              //в случае выполнения функции
                function (data) {
                    printDetail(data);                              //функция, заполняющая поля
                    savedRecord = JSON.stringify(data);             //сохраняем полученный ответ в виде JSON-строки
                    savedRecordAsObject = JSON.parse(savedRecord);  //парсим JSON-строку в JS-объект. Делаем это потому что полученные с сервера данные имеют с JS-объектами
                                                                    //разный формат
                }, "json")
            ;
        }
        if(!savedRecordAsObject){  //если еще не загружали никакие записи, то этот объект не определен
            f();                    //если записи не загружали, просто загружаем новую запись
        }
        else{
            doSomething(f); //если уже что-то загружали, то сначала запускаем обработчик (проверка на изменения и все такое
        }



    }


    //функция, которая хаполняет поля в форме lkmdata. Используется функцией showDetails
    function printDetail(el) {
        //    alert(el.name);
        document.getElementById("lkmid").value=el.id;
        document.getElementById("lkmname").value=el.name;
        document.getElementById("lkmbase").value=el.lkmBases.basesName;
        document.getElementById("shortdescription").value=el.shortDescription;
        document.getElementById("description").value=el.description;
        document.getElementById("destination").value=el.destination;
        document.getElementById("colors").value=el.colors;
        document.getElementById("useinstruction").value=el.useInstruction;
        document.getElementById("layers").value=el.layers;
        document.getElementById("spreadingcapacity").value=el.spreadingCapasity;
        document.getElementById("layerThickness").value=el.layerThickness;
        document.getElementById("ductilityThickness").value=el.ductility_thickness;
        document.getElementById("dryingtime").value=el.dryingtime;
        document.getElementById("hardness").value=el.hardness;
        document.getElementById("charge").value=el.charge;
        document.getElementById("solids").value=el.solids;
    }


    //функция, делегирующая события в списке "lkmlist". Она отслеживает клики в элементах списка.
    function eventDelegation(event) {
        var target=event.target;    //определяем источник события (клика)
        showDetails(target);    //запускаем функцию показа деталей
    }


    //функция, которая последовательно читает все поля в форме lkmdata и присваивает их значения свойствам объекта currentRecordAsObject
    function readCurrentStatement() {
        currentRecordAsObject.id=document.getElementById("lkmid").value;
        currentRecordAsObject.name=document.getElementById("lkmname").value;
        currentRecordAsObject.lkmBases=lkmbasesMenu[document.getElementById("lkmbase").value];
        currentRecordAsObject.shortDescription=document.getElementById("shortdescription").value;
        currentRecordAsObject.description=document.getElementById("description").value;
        currentRecordAsObject.destination=document.getElementById("destination").value;
        currentRecordAsObject.colors=document.getElementById("colors").value;
        currentRecordAsObject.useInstruction=document.getElementById("useinstruction").value;
        currentRecordAsObject.layers=document.getElementById("layers").value;
        currentRecordAsObject.spreadingCapasity=document.getElementById("spreadingcapacity").value;
        currentRecordAsObject.layerThickness=document.getElementById("layerThickness").value;
        currentRecordAsObject.ductility_thickness=document.getElementById("ductilityThickness").value;
        currentRecordAsObject.dryingtime=document.getElementById("dryingtime").value;
        currentRecordAsObject.hardness=document.getElementById("hardness").value;
        currentRecordAsObject.charge=document.getElementById("charge").value;
        currentRecordAsObject.solids=document.getElementById("solids").value;
    }


    //функция, которая сравнивает текущее состояние формы с последними сохраненными данными. true - значения равны
    function compareCurrentStatement(){
        readCurrentStatement();                         //читаем текущее состояние полей в переменную currentRecordAsObject
        for(properties in currentRecordAsObject){       //перебираем все свойства объекта currentRecordAsObject
            if((properties=="lkmBases")){               //если имя свойства равно lkmBases, сравнение не произволим. Это костыль.
                continue;
            }
            else {
                if(savedRecordAsObject[properties]!=currentRecordAsObject[properties]){ //если рассматриваемое поле объекта currentRecordAsObject не равно соответствующему
                    return false;                                                       //полю объекта savedRecordAsObject, возвращаем false
                }
            }
        }
        return true;                        //если цикл не был прерван с возвратом false, возвращаем true, все поля равны
    }


    //функция для обновления значения в списке имен при изменении соответствующего текстового поля в разделе детализации.
    //нужна для обработки события onchange: меняем имя поля в детализации - меняется название ссылки в списке
    function updateListElementById(){
        document.getElementById(document.getElementById("lkmid").value).textContent=document.getElementById("lkmname").value;
        //ищет элемент по id, который равен значению в текстовом поле с id "lkmid", зачем меняет его значение на то, которое было в поле с id "lkmname"
    }


    //Функция копирования данных. Просто ставит id равным new, после чего связь с существующими запясями теряется.
    function createCopy() {
        document.getElementById("lkmid").value="new";   //обрываем привязку id к какой-то конкретной записи в базе
        var newRecord = document.createElement('li');   //создаем новый элемент списка lkmlist
        newRecord.id="new";                             //присваиваем ему соответствующий id
        newRecord.innerHTML="Новая запись";             //определяем его текст
        lkmlist.appendChild(newRecord);                 //добавляем в родительский элемент lkmlist только что определенную запись
        document.getElementById("lkmname").value="Новая запись";        //присваиваем полю с названием записи значение, равное значению в списке
    }


    //Функция создания новой записи.
    function createNewEntity() {
        document.getElementById('lkmdata').reset();             //очищаем все поля в форме lkmdata
        document.getElementById("lkmid").value="new";           //присваиваем полю lkmid ни к чему не привязанное значение
        var newRecord = document.createElement('li');           //создаем новый элемент списка lkmlist (см функцию createCopy)
        newRecord.id="new";
        newRecord.innerHTML="Новая запись";
        lkmlist.appendChild(newRecord);
        document.getElementById("lkmname").value="Новая запись";
    }


    //проверяет наличие изменений в записях
    function checkChanges() {
        if(lkmid.value=="new"){                 //если текущее значение поля равно new, то
            return 1;                           //означает, что запись новая
        }
        else{if(!compareCurrentStatement()){    //если функция сравнения savedRecordAsObject и currentRecordAsObject возвращает false, то
            return 2;                           //означает, что были изменения
        }
        };
        return 0; //означает, что изменений не было
    }


    //функция-обертка для других функций. Проверяет перед выполнением этих функций, были ли изменения в текущей записи. Аргумент - имя любой йункции
    function doSomething(someFunction) {
        if((checkChanges()==1)||(checkChanges()==2)){           //если были обнаружены изменения
            if(confirm("Были внесены изменения. Продолжить?")){ //выводится предупреждение об изменениях. Можно отказаться от дальнейших действий
                if(confirm("Сохранить изменения?")){            //если мы решили продолжить, появляется предложение сохранить изменения
                    save();                                     //если мы подтвердили сохранение, то выполняем сохранение и...
                    someFunction();                             //выполняем функцию-аргумент
                }
                else {                                          //если мы отказались от сохранений
                    if(document.getElementById("new")){         //если есть элемент с id равным new (то есть если была попытка создать новую запись), то
                        lkmlist.removeChild(document.getElementById("new"));    //удаляем из lkmlist элемент с таким id
                        someFunction();                                         //исполняем фуркцию
                    }
                    else{
                        someFunction();                                         //если новой несохраненной записи не было, то просто исполняем фуркцию
                    }
                };
            }
        }
        else{
            someFunction();                                     //если изменений не обнаружено, просто исполняем функцию
        }
    }


    //функция сохранения изменений или новых записей
    function save() {
        readCurrentStatement();                                 //заполняем переменную currentRecordAsObject
        currentRecord=JSON.stringify(currentRecordAsObject);    //заполняем currentRecord, строковое представление currentRecordAsObject в формате JSON
        if(checkChanges()!=0){                       //Проводим сравнение записанного и текущего значений. Если они разные, то...
            if(currentRecordAsObject.id!="new"){
                url = "/demo/saverecord";                           //если запись старая, то есть ее id не равна new,
                                                                    // отправляем на контроллер, обрабатывающий обновление записи в базе
            }
            else {
                url = "/demo/savenewrecord";                        //в ином случае отправляем запрос на контроллер, обрабатывающий добавление новой записи
            }
            //jquery-функция get. Отправляет по указанному адресу данные в формате ключ-значение. В случае выполнения запускается функция
            //аргументом которй являются получаенные данные (в нашей программе контроллер должен вернуть id сохраненной записи)
            $.get(url, {JSONString:currentRecord}).done(
                function (data) {
                    document.getElementById('lkmid').value=data;                   //меняем значение в поле lkmid с new на id, присвоеный  базой (или переписываем старый еще раз)
                    document.getElementById('new').id=data;                        //меняем id нового элемента в списке записей lkmlist на полученный из базы
                    readCurrentStatement();                                        //еще раз читаем текущее состояние для корректного сравнения в методе compareCurrentStatement
                }, "json");

            alert("Изменения сохранены");
            savedRecordAsObject=currentRecordAsObject;                              //приравниваем сохраненное состояние к текущему, чтобы последующие действия проходили
                                                                                    //проверку на изменения
        }
    }


    //функция удаления записи
    function del() {
        if(confirm("Запись будет удалена. Подтвердите удаление")){  //выводим предупреждение об удалении
            url="/demo/deleterecord";                           //данные будут отрпавлены контроллеру по этому адресу
            id = document.getElementById('lkmid').value;        //присваиваем переменной значение в поле lkmid
            document.getElementById('lkmdata').reset();         //очищаем все поля формы lkmdata
            lkmlist.removeChild(document.getElementById(id));   //удаляем из списка lkmlist запись с удяляемым id
            $.get(url, {id: id}).done(                          //отправляем get-запрос на контроллер. При выполнении запроса выводится запись об удалении
                function (data) {
                    alert("Запись удалена");
                }, "json")
            ;
            readCurrentStatement();                             //считываем текущее состояние полей
            savedRecordAsObject=currentRecordAsObject;          //приравниваем сохраненное и текущее состояние
        }
    }