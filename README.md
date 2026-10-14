# Pulse Visual (Fabric, Minecraft 1.21.11)

HUD-мод: watermark, FPS, XYZ/Yaw/Pitch/HP, keystrokes, список модулей.
Мэппинги: официальные Mojang (Yarn для 1.21.11 последний).

## Сборка на GitHub (без установки Java)
Загрузи проект в репозиторий, во вкладке Actions дождись зелёной галочки
и скачай артефакт pulse-visual-jar (внутри .jar).

## Требования (локальная сборка)
- JDK 21
- Gradle 9.2+ (Loom 1.14 требует новый Gradle). Удобнее всего скопировать
  папку gradle/ и файлы gradlew, gradlew.bat из
  https://github.com/FabricMC/fabric-example-mod (ветка для 1.21.11).

## Сборка
    gradle build        # или ./gradlew build

Jar: build/libs/pulse-visual-1.0.0.jar -> в папку mods
(вместе с Fabric Loader 0.18.1+ и Fabric API для 1.21.11).

## Управление
Настройки -> Управление -> категория "Pulse Visual".
Клавиши по умолчанию не назначены.

## Запуск из среды разработки
    gradle runClient

## Если версии зависимостей не нашлись
Актуальные значения loader/fabric-api для 1.21.11 смотри на
https://fabricmc.net/develop и поправь gradle.properties.
