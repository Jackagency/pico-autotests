# pico autotests

Команды для локального запуска автотестов
```
gradle clean test
```

Дополнительные параметры запуска
```
-DbrowserName={имя браузера}
-DbrowserVersion={версия браузера}
-DbrowserSize={размер окна браузера}
```

Пример запуска тестов с параметрами
```
gradle clean test -DbrowserName=chrome -DbrowserVersion=99.0 -DbrowserSize=1920x1080
```