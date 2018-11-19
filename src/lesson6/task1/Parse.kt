@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

import lesson2.task2.daysInMonth
import java.lang.Math.floor

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        } else {
            println("Прошло секунд с начала суток: $seconds")
        }
    } else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    if (!str.matches(Regex("""\d{1,2}\s[а-яА-ЯёЁ]+\s(\d+)"""))) return ""
    val arrayOfMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря")
    val inputData = str.split(" ")
    val day = inputData[0].toInt()
    val year = inputData[2].toInt()
    if (!arrayOfMonths.contains(inputData[1])) return ""
    val month = arrayOfMonths.indexOfFirst { it == inputData[1] } + 1
    if (day !in 1..daysInMonth(month, year)) return ""
    return String.format("%02d.%02d.%d", day, month, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    if (!digital.matches(Regex("""\d\d\.\d\d\.(\d+)"""))) return ""
    val arrayOfMonths = arrayOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря")
    val inputData = digital.split(".")
    val day = inputData[0].toInt()
    val month = inputData[1].toInt()
    val year = inputData[2].toInt()
    if (day !in 1..daysInMonth(month, year) || month !in 1..12) return ""
    return String.format("%d ${arrayOfMonths[month - 1]} %d", day, year)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String {
    val resultStr = StringBuilder()
    if (!phone.matches(Regex("""(\+)?(\d+\s+)?(\(\d+\))?(\d|-|\s)*"""))) return ""
    phone.forEach { if (it in '0'..'9' || it == '+') resultStr.append(it) }
    return "$resultStr"
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    if (!jumps.matches(Regex("""[\s%\-]*(\d+[\s%\-]*)+"""))) return -1
    return jumps.trim { symbol -> symbol !in '0'..'9' }
            .split(Regex("""[\s%+\-]+"""))
            .maxBy { it.toInt() }!!.toInt()
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    if (!jumps.split(" ").all {
                it.toIntOrNull() != null ||
                        !it.matches(Regex("""%+""")) ||
                        !it.matches(Regex("""%*[+-]"""))
            }) return -1
    var resultNumber = -1
    for (element in Regex("""\d+\s%*\+""").findAll(jumps)) {
        val number = element.value.filter { it in '0'..'9' }.toInt()
        if (resultNumber < number) resultNumber = number
    }
    return resultNumber
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!expression.matches(Regex("""(\d+(\s[-+]\s)?)+"""))) throw IllegalArgumentException()
    return Regex("""(?<=-)\s(?=\d+)|(\+\s)""").replace(expression, "")
            .split(" ")
            .sumBy { it.toInt() }
}


/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    if (!str.matches(Regex("""((\S+\s)|((?<=\s)\S+)){2,}"""))) return -1
    var index = 0
    val list = str.split(" ")
    for (i in 1 until list.size) {
        if (list[i].toLowerCase() == list[i - 1].toLowerCase()) break
        else index += list[i - 1].length + 1
    }
    return if (index == str.length - 1) -1
    else index
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String {
    if (!description.matches(Regex("""(\S+\s\d+(\.\d+)?(;\s)?)+"""))) return ""
    val list = description.split(Regex("""(;\s)|\s"""))
    val number = list.filter { it.toDoubleOrNull() != null }.maxBy { it.toDouble() }
    for (i in 1 until list.size step 2) {
        if (list[i] == number) return list[i - 1]
    }
    return ""
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int {
    if (roman == "" || !roman.matches(Regex("""M*(CM)?D*(CD)?C*(XC)?L*(XL)?X*(IX)?V*(IV)*I*"""))) return -1
    val list = listOf("CM" to 900, "CD" to 400, "XC" to 90, "XL" to 40, "IX" to 9, "IV" to 4)
    var resultNumber = 0
    var mutRoman = roman
    list.forEach {
        if (roman.contains(it.first)) {
            mutRoman = mutRoman.replace(it.first, "")
            resultNumber += it.second
        }
    }
    for (i in 0 until mutRoman.length) {
        resultNumber += romanToArab(mutRoman[i])
    }
    return resultNumber
}

fun romanToArab(c: Char): Int =
        when (c) {
            'M' -> 1000
            'D' -> 500
            'C' -> 100
            'L' -> 50
            'X' -> 10
            'V' -> 5
            else -> 1
        }

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> {
    if (!commands.matches(Regex("""[<>+\-\s\[\]]*"""))) throw IllegalArgumentException()
    var g = 0
    commands.forEach {
        if (it == '[') g++                                              //Поверяем циклы скобок на закрытость
        if (it == ']') g--
        if (g < 0) throw IllegalArgumentException()
    }
    if (g != 0) throw IllegalArgumentException()
    val resultList = mutableListOf<Int>()
    for (i in 0 until cells) {                                     //Создаём список с ячейками
        resultList.add(0)
    }
    if (commands.isEmpty()) return resultList                           //Если команд нет, возващаем пустой список
    var varLimit = limit                                           //Динамический лимит операций
    var i = floor(cells.toDouble() / 2).toInt()                    //Начинаем со срединной ячейки
    var j = 0                                                           //Индекс текущей операции
    while (varLimit > 0) {
        when (commands[j]) {
            '>' -> i++
            '<' -> i -= 1
            '+' -> resultList[i]++
            '-' -> resultList[i] -= 1
            '[' -> if (resultList[i] == 0)
                j = forBracketFirst(commands, j)
            ']' -> if (resultList[i] != 0)
                j = forBracketSecond(commands, j)
        }
        if (i == cells || i < 0) throw IllegalStateException()          //Поверяем выход за пределы списка с ячейками
        varLimit -= 1
        j++
        if (j == commands.length) break                                 //Преываем, если выполнены все команды
    }

    return resultList
}

fun forBracketFirst(str: String, index: Int): Int {                     //ищем индекс парной скобки для [
    var j = 1
    var i = index
    do {
        i++
        if (str[i] == ']') j -= 1
        if (str[i] == '[') j++
    } while (j != 0)
    return i
}

fun forBracketSecond(str: String, index: Int): Int {                    //ищем индекс парной скобки для ]
    var j = -1
    var i = index
    do {
        i -= 1
        if (str[i] == ']') j -= 1
        if (str[i] == '[') j++
    } while (j != 0)
    return i
}