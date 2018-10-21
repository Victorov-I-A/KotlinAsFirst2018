@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.minDivisor
import java.lang.Math.pow
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = sqrt(v.fold(0.0) { previousResult, element ->
    previousResult + sqr(element)
})

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0 else list.sum() / list.size

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val length = list.size
    val average = list.sum() / length
    for (i in 0 until length) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var c = 0.0
    for (i in 0 until max(a.size, b.size)) {
        c += a[i] * b[i]
    }
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    var sumP = 0.0
    for (i in 0 until p.size) {
        sumP += p[i] * pow(x, i.toDouble())
    }
    return sumP
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    var previousSum = 0.0
    var element: Double
    for (i in 0 until list.size) {
        element = list[i]
        list[i] = element + previousSum
        previousSum = list[i]
    }
    return list
}


/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var number = n
    while (number > 1) {
        val divisor = minDivisor(number)
        list.add(divisor)
        number /= divisor
    }
    return list
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var number = n
    val list = mutableListOf<Int>()
    do {
        list.add(number % base)
        number /= base
    } while (number > 0)
    return list.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    val mass = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z')
    var number = n
    var str = ""
    do {
        val balance = number % base
        str += if (balance > 9)
            mass[balance - 10]
        else
            balance
        number /= base
    } while (number > 0)
    return str.reversed()
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = digits.reversed().foldIndexed(0) { i, previousResult, element ->
    previousResult + element * pow(base.toDouble(), i.toDouble()).toInt()
}


/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun charToInt(a: Char): Int {
    if (a in '0'..'9') return a - '0'
    val mass = arrayOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
            'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z')
    var b = 10
    var i = 0
    while (a != mass[i]) {
        b++
        i++
    }
    return b
}

fun decimalFromString(str: String, base: Int): Int = decimal(str.map { charToInt(it) }, base)

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var str = ""
    var numOfM = n / 1000
    var numOfC = n / 100 % 10
    var numOfX = n / 10 % 10
    var numOfI = n % 10
    while (numOfM > 0) {
        str += 'M'
        numOfM--
    }
    while (numOfC > 0) {
        str += 'C'
        if (str.filter { it == 'C' || it == 'D' } == "CCCC")
            str = str.filter { it != 'C' } + "CD"
        if (str.filter { it == 'C' || it == 'D' } == "CDC")
            str = str.filter { it != 'C' && it != 'D' } + 'D'
        if (str.filter { it == 'C' || it == 'D' }.length == 5)
            str = str.filter { it != 'C' && it != 'D' } + "CM"
        numOfC--
    }
    while (numOfX > 0) {
        str += 'X'
        if (str.filter { it == 'X' || it == 'L' } == "XXXX")
            str = str.filter { it != 'X' } + "XL"
        if (str.filter { it == 'X' || it == 'L' } == "XLX")
            str = str.filter { it != 'X' && it != 'L' } + 'L'
        if (str.filter { it == 'X' || it == 'L' }.length == 5)
            str = str.filter { it != 'X' && it != 'L' } + "XC"
        numOfX--
    }
    while (numOfI > 0) {
        str += 'I'
        if (str.filter { it == 'I' || it == 'V' } == "IIII")
            str = str.filter { it != 'I' } + "IV"
        if (str.filter { it == 'I' || it == 'V' } == "IVI")
            str = str.filter { it != 'I' && it != 'V' } + 'V'
        if (str.filter { it == 'I' || it == 'V' }.length == 5)
            str = str.filter { it != 'I' && it != 'V' } + "IX"
        numOfI--
    }
    return str
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
val MASS = arrayOf("", "один", "два", "три", "четыр", "пят", "шест", "сем", "восем", "девят")

fun firstDigit(n: Int): String =
        when (n / 100) {
            0 -> MASS[0]
            1 -> "сто "
            2 -> "двести "
            3 -> "триста "
            4 -> "четыреста "
            else -> MASS[n / 100] + "ьсот"
        }

fun secondDigit(n: Int): String =
        when (n % 100 / 10) {
            0 -> MASS[0]
            1 -> ""
            2, 3 -> MASS[n % 100 / 10] + "дцать "
            4 -> "сорок "
            9 -> "девяносто "
            else -> MASS[n % 100 / 10] + "ьдесят "
        }

fun secondAndThirdDigit(n: Int): String =
        when {
            n % 10 == 0 -> MASS[0]
            n % 100 == 10 -> "десять "
            n % 100 == 12 -> "двенадцать "
            n % 100 in 11..19 -> MASS[n % 10] + "надцать "
            n % 10 in 1..3 -> MASS[n % 10] + ' '
            n % 10 == 4 -> "четыре "
            else -> MASS[n % 10] + "ь "

        }

fun russian(n: Int): String {
    val firstPart = firstDigit(n / 1000) + secondDigit(n / 1000)
    val secondPart =
            firstDigit(n % 1000) + secondDigit(n % 1000) + secondAndThirdDigit(n % 1000)
    var thousand = secondAndThirdDigit(n / 1000)
    when (thousand) {
        "один " -> thousand = "одна тысяча "
        "два " -> thousand = "две тысячи "
        "три ", "четыре " -> thousand += "тысячи "
        else -> thousand += "тысяч "
    }
    return if (firstPart + thousand != "тысяч ")
        (firstPart + thousand + secondPart).trim()
    else secondPart.trim()

}