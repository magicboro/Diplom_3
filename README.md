# Diplom_3

# Запуск в Chrome

```bash
mvn test
```

# Запуск с Firefox

```bash
mvn clean test -Dbrowser=firefox
```

если mvn test падает с ошибкой 500, и не находит бинарник, то запускаем с параметром

```bash
mvn clean test -Dbrowser=firefox -Dwebdriver.firefox.bin=/usr/bin/firefox
```

# Запуск с Я.Браузер

Указать свой путь до Я.Браузера

```bash
mvn clean test -Dbrowser=yandex -Ddriver.version=126.0.6478.182 -Dwebdriver.yandex.bin=C:\Users\User\AppData\Local\Yandex\YandexBrowser\Application\browser.exe
```