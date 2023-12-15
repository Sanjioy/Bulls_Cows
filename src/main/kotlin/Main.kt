import kotlin.random.Random

fun generateSecretNumber(): String {
    return (0..9).shuffled().take(4).joinToString("")
}

fun evaluateGuess(secret: String, guess: String): Pair<Int, Int> {
    val bulls = secret.zip(guess).count { it.first == it.second }
    val secretDigits = secret.toSet()
    //val cows = secret.toSet() intersect guess.toSet()
    //return bulls to cows.size
    val cows = guess.filter { it in secretDigits }.count() - bulls
    return bulls to cows
}

fun main() {
    println("Добро пожаловать в игру 'Быки и коровы'!")

    val secretNumber = generateSecretNumber()
    var attempts = 0

    println("Компьютер загадал тайное число. Попробуйте угадать!")

    while (true) {
        print("Введите 4-значное число: ")
        val guess = readLine()!!

        if (guess.length != 4 || !guess.all { it.isDigit() } || guess.toSet().size != 4) {
            println("Пожалуйста, введите корректное 4-значное число с уникальными цифрами.")
            continue
        }

        attempts++

        val (bulls, cows) = evaluateGuess(secretNumber, guess)

        println("Результат попытки $attempts: $bulls бык(ов), $cows корова(ы)")

        if (bulls == 4) {
            println("Поздравляем! Вы угадали число $secretNumber за $attempts попыток.")
            break
        }
    }

    println("Игра завершена.")
}