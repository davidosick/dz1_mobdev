import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    // Ввод имени пользователя
    print("Введите ваше имя: ")
    val name = scanner.nextLine()

    // Ввод даты рождения или знака зодиака
    println("Выберите вариант:")
    println("1. Ввести знак зодиака")
    println("2. Ввести дату рождения")
    val choice = scanner.nextLine()

    var zodiacSign: String? = null
    var birthDate: LocalDate? = null
    var zodiacChoice: Number = 0

    if (choice == "1") {
        // Ввод знака зодиака
        println("Выберите ваш знак зодиака: ")
        println("1. Овен")
        println("2. Телец")
        println("3. Близнецы")
        println("4. Рак")
        println("5. Лев")
        println("6. Дева")
        println("7. Весы")
        println("8. Скорпион")
        println("9. Стрелец")
        println("10. Козерог")
        println("11. Водолей")
        println("12. Рыбы")
        zodiacChoice = scanner.nextLine().toInt()

        
    } else if (choice == "2") {
        print("Введите вашу дату рождения (дд.мм.гггг): ")
        val birthDateString = scanner.nextLine()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        birthDate = LocalDate.parse(birthDateString, formatter)
        
        val dayOfYear = birthDate.dayOfYear  // Получаем день в году

        zodiacChoice = when (dayOfYear) {
            in LocalDate.of(2000, 3, 21).dayOfYear..LocalDate.of(2000, 4, 19).dayOfYear -> 1
            in LocalDate.of(2000, 4, 20).dayOfYear..LocalDate.of(2000, 5, 20).dayOfYear -> 2
            in LocalDate.of(2000, 5, 21).dayOfYear..LocalDate.of(2000, 6, 20).dayOfYear -> 3
            in LocalDate.of(2000, 6, 21).dayOfYear..LocalDate.of(2000, 7, 22).dayOfYear -> 4
            in LocalDate.of(2000, 7, 23).dayOfYear..LocalDate.of(2000, 8, 22).dayOfYear -> 5
            in LocalDate.of(2000, 8, 23).dayOfYear..LocalDate.of(2000, 9, 22).dayOfYear -> 6
            in LocalDate.of(2000, 9, 23).dayOfYear..LocalDate.of(2000, 10, 22).dayOfYear -> 7
            in LocalDate.of(2000, 10, 23).dayOfYear..LocalDate.of(2000, 11, 21).dayOfYear -> 8
            in LocalDate.of(2000, 11, 22).dayOfYear..LocalDate.of(2000, 12, 21).dayOfYear -> 9
            in LocalDate.of(2000, 12, 22).dayOfYear..LocalDate.of(2001, 1, 19).dayOfYear -> 10
            in LocalDate.of(2000, 1, 20).dayOfYear..LocalDate.of(2000, 2, 18).dayOfYear -> 11
            in LocalDate.of(2000, 2, 19).dayOfYear..LocalDate.of(2000, 3, 20).dayOfYear -> 12
            else -> 0
        }
        
    }

    zodiacSign = when (zodiacChoice) {
        1 -> "Овен"
        2 -> "Телец"
        3 -> "Близнецы"
        4 -> "Рак"
        5 -> "Лев"
        6 -> "Дева"
        7 -> "Весы"
        8 -> "Скорпион"
        9 -> "Стрелец"
        10 -> "Козерог"
        11 -> "Водолей"
        12 -> "Рыбы"
        else -> "Неизвестный знак зодиака"
    }

    println("Выберите дату для гороскопа:")
    println("1. Сегодня")
    println("2. Завтра")
    println("3. Следующая неделя")
    println("4. Ввести конкретную дату")
    val dateChoice = scanner.nextLine()

    val horoscopeDate: LocalDate = when (dateChoice) {
        "1" -> LocalDate.now()
        "2" -> LocalDate.now().plusDays(1)
        "3" -> LocalDate.now().plusWeeks(1)
        "4" -> {
            print("Введите дату для гороскопа (дд.мм.гггг): ")
            val specificDateString = scanner.nextLine()
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            LocalDate.parse(specificDateString, formatter)
        }
        else -> LocalDate.now()
    }

    // Вывод информации
    println("\nИмя: $name")
    if (zodiacSign != null) {
        println("Знак зодиака: $zodiacSign")
    } else if (birthDate != null) {
        println("Дата рождения: ${birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
    }
    println("Дата для гороскопа: ${horoscopeDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
}
