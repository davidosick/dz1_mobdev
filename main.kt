import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.random.Random

val predictions = listOf(
    "Это ваш день! Удача на вашей стороне.",
    "Ожидайте приятных сюрпризов.",
    "Настало время для больших изменений.",
    "Остерегайтесь поспешных решений.",
    "Вы получите важные новости.",
    "Вас ждет новая встреча, которая изменит вашу жизнь.",
    "Этот день — идеальный для новых начинаний.",
    "Обратите внимание на советы близких.",
    "Сейчас время для отдыха и восстановления сил.",
    "Не откладывайте дела на потом."
)

fun generateRandomPrediction(): String {
    return predictions[Random.nextInt(predictions.size)]
}


class User { // wow...
    var name: String = ""
    var zodiacNumber: Int = 0
    var birthDate: LocalDate? = null

    fun getUserName(): String {
        return name
    }

    fun setUserName(newName: String) {
        name = newName
    }
}

object ZodiacCalculator {
    fun calculateZodiacSign(birthDate: LocalDate): Int {
        val dayOfYear = birthDate.dayOfYear
        return when (dayOfYear) {
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

    val zodiacSigns = arrayOf(
        "",           
        "Овен",        
        "Телец",       
        "Близнецы",  
        "Рак",      
        "Лев",    
        "Дева",      
        "Весы",      
        "Скорпион", 
        "Стрелец",  
        "Козерог",   
        "Водолей",    
        "Рыбы"       
    )
}

fun main() {
    val scanner = Scanner(System.`in`)
    val user = User()

    print("Введите ваше имя: ")
    user.setUserName(scanner.nextLine())

    println("Выберите вариант:")
    println("1. Ввести знак зодиака")
    println("2. Ввести дату рождения")
    val choice = scanner.nextLine()

    if (choice == "1") {
        println("Выберите ваш знак зодиака: ")
        for (i in 1 until ZodiacCalculator.zodiacSigns.size) {
            println("$i. ${ZodiacCalculator.zodiacSigns[i]}")
        }
        user.zodiacNumber = scanner.nextLine().toInt()
    } else if (choice == "2") {
        print("Введите вашу дату рождения (дд.мм.гггг): ")
        val birthDateString = scanner.nextLine()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val birthDate = LocalDate.parse(birthDateString, formatter)
        
        user.birthDate = birthDate
        user.zodiacNumber = ZodiacCalculator.calculateZodiacSign(birthDate)
    }

    val zodiacSign = if (user.zodiacNumber in 1..12) ZodiacCalculator.zodiacSigns[user.zodiacNumber] else "Неизвестный знак зодиака"

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

    println("\nИмя: ${user.getUserName()}")
    println("Знак зодиака: $zodiacSign")
    user.birthDate?.let { 
        println("Дата рождения: ${it.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}") 
    }
    println("Дата для гороскопа: ${horoscopeDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))}")
    println("___________________")
    println("${user.name}, здравствуйте! Ваш знак зодиака: ${ZodiacCalculator.zodiacSigns[user.zodiacNumber]}. Персональный гороскоп на $horoscopeDate: ${generateRandomPrediction()}")
}
