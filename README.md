# UProject - Финальный проект по курсу Java на платформе Ulearn. Вариант - 7

1) Данные с которыми мы будем работать
![image](https://user-images.githubusercontent.com/101979568/211361087-2bc8b4bd-cc49-40e8-840d-4a84e0e65513.png)
<br /><br />
2) Создадим классы в которых будем хранить данные
    1. Player - класс record (неизменяемый) который будет хранить данные об игроке
    ![image](https://user-images.githubusercontent.com/101979568/211361886-85f0397c-0c49-4b0b-a29b-e89495c3b035.png)
    <br />

    2. Team - класс имеет основные неизменяемые поля, но т.к он будет получать и хранить классы типа Player его невозможно сделать record
    ![image](https://user-images.githubusercontent.com/101979568/211361950-35b61454-7d12-41f2-ac0a-6cc1f3094e1a.png)
<br /><br />

3) Парсить данные из csv будем с помощью библиотеке opencsv, создадим класс CSVParse где и будем работать с opencsv
![image](https://user-images.githubusercontent.com/101979568/211362283-0a81ba89-b452-48b2-8f5a-b1180ae553af.png)
<br /><br />

4) Для работы с SQL создадим класс SQLLoader, в котором будем работать со всеми запросами, подключениями и т.д.
![image](https://user-images.githubusercontent.com/101979568/211362486-94250cf3-6c19-4e6a-b819-c4e549a23e81.png)
  Добавление таблиц
  ![image](https://user-images.githubusercontent.com/101979568/211362713-6ee6c14e-7ef1-4d23-8388-3c64bc08cdd7.png)
  ![image](https://user-images.githubusercontent.com/101979568/211362755-e69d1f6d-aed5-49be-9b21-0abde6e7c457.png)
  
<br /><br />
5) Заполнять таблицы мы будем с помощь метода SQLoader addTeam
![image](https://user-images.githubusercontent.com/101979568/211362934-afc61e14-f537-40f3-b0f5-6361818c669d.png)
![image](https://user-images.githubusercontent.com/101979568/211362958-ecb4796a-abb5-4d4e-9742-ea034f950e0f.png)

<br /><br />
6) Выводим все данные из таблицы в консоль
![image](https://user-images.githubusercontent.com/101979568/211365327-bd7ee688-dc6e-4f59-b176-35d1126899cc.png)
<br /><br />

7) Задание 1. Постройте график по среднему возрасту во всех командах.
![image](https://user-images.githubusercontent.com/101979568/211363547-07685206-f4af-49c2-b1df-28ae15b7b1d2.png)
![image](https://user-images.githubusercontent.com/101979568/211363642-9954b3a9-f259-4694-96d5-c406fbd9aefb.png)
![chart](https://user-images.githubusercontent.com/101979568/211363731-6ba7d0d8-66a3-451b-83a0-61f10bb60f49.jpg)
<br /><br />

8) Задание 2 - 3
![image](https://user-images.githubusercontent.com/101979568/211364637-1f6ec1eb-0a9a-49e5-b7fd-f3f93c4a8adb.png)
