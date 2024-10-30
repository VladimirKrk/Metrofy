# Moscow Metro Route App

Приложение для Android, которое помогает пользователю выбрать маршрут на карте метро Москвы, отслеживает его перемещение, присылает уведомления о пересадках и завершении поездки.

## Explanation

Человек сможет выбрать откуда и куда он едет на карте метро Москвы
Карта построит маршрут основываясь на Яндекс Метро или приложении Метро Москвы 
Дальше приложение будет работать в фоновом режиме отслеживая геопозицию телефона. Приложение будет присылать уведомления о пересадках, и окончании поездки, чтобы человек не пропустил свою станцию. 


## Features

- Одно основное окно с картой станции, с которой можно будет взаимодействовать ( приближать, уменьшать, передвигать, кликать на станции и указывать их в качестве начала и конца пути )
- Внизу экрана должна быть плашка в которой можно выбрать начало и конец пути с помощью слов
- Также должна быть кнопка в виде 3 горизонтальных полос, которая отправляет в настройки
- Выбор типа уведомлений

## Этапы разработки (Лабораторные работы)

### Лабораторная работа 1: Создание интерфейса карты метро
1. Добавить в проект статическое изображение карты метро Москвы.
2. Создать основное окно приложения с элементом `ImageView` для отображения карты метро.
3. Настроить возможность увеличения, уменьшения и перемещения изображения карты.

### Лабораторная работа 2: Интеграция Google Maps API для работы с геолокацией
1. Настроить Google Maps API для определения местоположения пользователя.
2. Скрыть отображение карты города, оставив только слой для получения координат.
3. Добавить наложение карты метро поверх невидимой карты Google Maps.

### Лабораторная работа 3: Выбор маршрута на карте
1. Реализовать выбор начальной и конечной станций маршрута.
2. Добавить панель выбора маршрута в нижней части экрана для отображения информации о маршруте.
3. Настроить обработку нажатий на станции.

### Лабораторная работа 4: Фоновое отслеживание местоположения
1. Настроить работу приложения в фоновом режиме.
2. Реализовать отслеживание перемещения пользователя по маршруту.
3. Настроить логику уведомлений при пересадках и на конечной станции.

### Лабораторная работа 5: Настройка уведомлений
1. Создать меню настроек с возможностью выбора типа уведомлений.
2. Реализовать уведомления о пересадках и завершении поездки.
3. Настроить уведомления в зависимости от настроек пользователя.

### Лабораторная работа 6: Завершение разработки и тестирование
1. Тестирование всех функциональных возможностей приложения.
2. Оптимизация и устранение возможных ошибок.
3. Подготовка приложения к публикации.