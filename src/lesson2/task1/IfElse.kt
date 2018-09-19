@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.*

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val lastDigit = age % 10
    val twoLastDigit = age % 100
    return when {
        lastDigit in 2..4 && (age > 19 || age < 5) -> "$age года"
        lastDigit == 1 && twoLastDigit != 11 -> "$age год"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val firstPart = t1 * v1
    val secondPart = t2 * v2
    val thirdPart = t3 * v3
    val halfWay = (firstPart + secondPart + thirdPart) / 2
    return when {
        halfWay < firstPart -> halfWay / v1
        halfWay < (firstPart + secondPart) -> t1 + (halfWay - firstPart) / v2
        else -> t1 + t2 + (halfWay - firstPart - secondPart) / v3
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val xx1: Boolean = kingX == rookX1
    val xx2: Boolean = kingX == rookX2
    val yy1: Boolean = kingY == rookY1
    val yy2: Boolean = kingY == rookY2
    return when {
        (xx1 || yy1) && !xx2 && !yy2 -> 1
        (xx2 || yy2) && !xx1 && !yy1 -> 2
        (xx1 || yy1) && (xx2 || yy2) -> 3
        else -> 0
    }
}
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val xx: Boolean = kingX == rookX
    val yy: Boolean = kingY == rookY
    return when {
        (xx || yy) && (abs(bishopX - kingX) != abs(bishopY - kingY)) -> 1
        !xx && !yy && (abs(bishopX - kingX) == abs(bishopY - kingY)) -> 2
        (xx || yy) && (abs(bishopX - kingX) == abs(bishopY - kingY)) -> 3
        else -> 0
    }
}


/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun cosinus(a: Double, b: Double, c: Double): Double = (b * b + c * c - a * a) / (2 * b * c)

fun triangleKind(a: Double, b: Double, c: Double): Int = when {
    a + b < c || a + c < b || b + c < a -> -1
    cosinus(a, b, c) == 0.0 || cosinus(b, a, c) == 0.0 || cosinus(c, b, a) == 0.0 -> 1
    cosinus(a, b, c) < 0 || cosinus(b, a, c) < 0 || cosinus(c, b, a) < 0 -> 2
    else -> 0
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when {
    d in a..b && c in a..b -> d - c
    b in c..d && a in c..d -> b - a
    b in c..d -> b - c
    d in a..b -> d - a
    else -> -1
}

