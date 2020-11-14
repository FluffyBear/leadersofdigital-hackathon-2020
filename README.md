# Система оценки проектов. Кейс "Росатом"
team: СыкТыкПык
leadersofdigital Хакатон 2020

## Установка

1. Собрать и запустить проект (вам потребуется Java версии не ниже 1.8)
```bash
./gradlew bootRun
```
2. Все!!!

## Работа с проектом

1. Возможность анализировать произвольные проекты:

http://localhost:8080/swagger-ui.html#/operations/controller/analyseUsingPOST

Примеры формата запросов: https://github.com/FluffyBear/leadersofdigital-hackathon-2020/tree/main/src/main/resources/analyse-test

2. Возможность визуализировать проекты:

http://localhost:8080/render.html?num=3 - визуализация всего проекта (num - номер проекта из списка тестовых проектов)

http://localhost:8080/render.html?analyse&num=3 - визуализация частей проектов которые требуют особого внимания

3. Возможность получать сравнивать оригинальный проект и возможные его изменения:

http://localhost:8080/swagger-ui.html#/controller/analyseChangeUsingPOST

Примеры формата запросов: https://github.com/FluffyBear/leadersofdigital-hackathon-2020/tree/main/src/main/resources/compare-test

4. Возможность получать подсказки по поводу сдвигов работ способствующих улучшению проекта:

http://localhost:8080/swagger-ui.html#/operations/controller/improveUsingPOST

Можно использовать примеры отсюда: https://github.com/FluffyBear/leadersofdigital-hackathon-2020/tree/main/src/main/resources/analyse-test
