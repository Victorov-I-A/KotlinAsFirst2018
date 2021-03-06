@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.pow
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digits = 0
    var number = n
    do {
        digits++
        number /= 10
    } while (number != 0)
    return digits
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var penult = 1
    var previous = 1
    var result = 0
    var variable: Int
    if (n in 1..2) return 1
    else for (i in 3..n) {
        result = penult + previous
        variable = previous
        previous = result
        penult = variable
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val compositionMN = m * n
    var divisor = 0
    for (i in min(m, n) downTo 1) {
        divisor = i
        if ((m % i == 0) && (n % i == 0)) break
    }
    return compositionMN / divisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDivisor = 1
    do {
        minDivisor++
    } while (n % minDivisor != 0)
    return minDivisor
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var minDivisor = min(m, n)
    for (divisor in min(m, n) downTo 1) {
        minDivisor--
        if (m % divisor == 0 && n % divisor == 0) break
        else continue
    }
    return minDivisor == 0
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = ceil(sqrt(m.toDouble())) <= floor(sqrt(n.toDouble()))

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var steps = 0
    var variableX = x
    while (variableX != 1) {
        if (variableX % 2 == 0)
            variableX /= 2
        else
            variableX = 3 * variableX + 1
        steps++
    }
    return steps
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var divisor = 3
    var sign = 2
    val newX = abs(x % (PI * 2))
    var sinus = newX
    var element = pow(newX, 3.0) / factorial(3)
    while (element >= eps) {
        if (sign % 2 == 0)
            sinus -= element
        else
            sinus += element
        divisor += 2
        element *= sqr(newX) / (divisor * (divisor - 1))
        sign++
    }
    return if (x > 0) sinus else -sinus
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var divisor = 2
    var sign = 2
    var cosinus = 1.0
    val newX = abs(x % (PI * 2))
    var element = pow(newX, 2.0) / factorial(2)
    while (element >= eps) {
        if (sign % 2 == 0)
            cosinus -= element
        else
            cosinus += element
        divisor += 2
        element *= sqr(newX) / (divisor * (divisor - 1))
        sign++
    }
    return cosinus
}


/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var oldNumber = n
    var newNumber = 0
    var lastDigit: Int
    while (oldNumber > 0) {
        lastDigit = oldNumber % 10
        newNumber = newNumber * 10 + lastDigit
        oldNumber /= 10
    }
    return newNumber
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var number = n / 10
    val previousDigit = n % 10
    while (number != 0) {
        val presentDigit = number % 10
        if (presentDigit != previousDigit) return true
        number /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var lenght = 0
    var numberForSqr = 1
    var sqrOfNumber = 0
    while (lenght < n) {
        sqrOfNumber = sqr(numberForSqr)
        lenght += digitNumber(sqrOfNumber)
        numberForSqr++
    }
    return (sqrOfNumber / pow(10.0, (lenght - n).toDouble()) % 10).toInt()
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var lenght = 0
    var numberForFib = 1
    var fibOfNumber = 0
    while (lenght < n) {
        fibOfNumber = fib(numberForFib)
        lenght += digitNumber(fibOfNumber)
        numberForFib++
    }
    return (fibOfNumber / pow(10.0, (lenght - n).toDouble()) % 10).toInt()
}
