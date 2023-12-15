### Веб приложение для просмотра коротких видео с моментами из фильмов

Курсовая работа по предмету "Информационные системы и базы данных"

Выполнили: Карапетян Эрик, Кустарев Иван

1 Этап:
1. Добавление Dockerfile файла для сборки приложения в Docker Image.
2. Добавление docker-compose.yaml файла с PostgreSQL и Docker образом. лабораторной работы для удобного развертывания на локальных машинах с возможностью запуска и тестирования.
3. Добавление init-databases.sh файла для инициализации базы данных лабораторной работы:
3.1. Создание базы данных ‘lab’
3.2. Создание пользователя ‘lab’
3.3. Выдача прав на базу данных и схему ‘public’ для этого пользователя
4. Подключение файла инициализации базы данных в entrypoint образа PostgreSQL для автоматического выполнения скрипта при построении образа.


2 Этап:
1. Добавлена возможность вностить URL для доступа к ДБ через переменные среды
2. Файл конфигураций переведён в формат .yml
3. Добавлена зависимость средства миграции базы данных flyway
4. Дрбавлена миграция инициализации базы данных, используемая flyway-ем

3 Этап:
1. Удалили лишние сервисы (функционал, связанный с ними не был реализован в проекте)
2. Удалили лишние интерфейсы (функционал, связанный с ними не был реализован в проекте)
3. Провели синтаксический рефакторинг кода
4. Установили корректный диалект для бд в проекте
5. Удаление лишних зависимостей, не используемых в приложении
6. Улучшены импорты в классах
7. Добавлена общая точка обработки ошибок работы с endpoint-ами
8. Проведена частичная (в местах, где это наиболее удобно) перепись проекта на kotlin
