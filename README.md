# JavaTestTask

Приложение для подсчета популярности фильмов определенного жанра. 

После запуска приложения в бэкграунде запускается процесс (метод `calculateStatistics` класса `ScheduledTasks`), который идет на сервер и производит подсчет статистики. Доступные опции:

- Показать все доступные id фильмов: URI `/showGenresIds`
- Остановить подсчет: URI `/stopScheduler`
- Показать число обработанных страниц сервера: URI `/showCurrentStat`
- Показать среднюю популярность фильмов определенного жанра (переменная {genre_id}): URI `/showStat/{genre_id}`. При этом, если подсчет статистики окончен, будет выведено требуемое значение. В противном случае будет выведено предупреждающее сообщение о том, что общение с сервером продолжается.


# Remarks

- Можно было бы добавить больше модульности, например, разбить код метода `calculateStatistics` класса `ScheduledTasks` на отдельные методы.
- Впервые столкнулся с реализацией тестирования в проекте. Не знаю, какой вариант более предпочтительный, поэтому попробовал разные походы с сайта spring.io, используя `Mock` и не используя.
