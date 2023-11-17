import kotlin.math.pow
import kotlin.math.sqrt

// Задача 4.

// Треугольник в окружности (обязательно использование класса Точка и класса Треугольник.
// Класс Окружность и другие классы - по желанию). Треугольник расположен на координатной плоскости
// и описан координатами своих вершин.
// Написать программу вычисляющую координаты центра описанной вокруг треугольника окружности и ее радиус.

fun main(args: Array<String>) {
    println("Введите координаты вершины A:")
    print("x = ")
    val ax = readln().toDoubleOrNull() ?: 0.0
    print("y = ")
    val ay = readln().toDoubleOrNull() ?: 0.0

    println("Введите координаты вершины B:")
    print("x = ")
    val bx = readln().toDoubleOrNull() ?: 0.0
    print("y = ")
    val by = readln().toDoubleOrNull() ?: 0.0

    println("Введите координаты вершины C:")
    print("x = ")
    val cx = readln().toDoubleOrNull() ?: 0.0
    print("y = ")
    val cy = readln().toDoubleOrNull() ?: 0.0

    val triangle = Triangle(Point(ax, ay), Point(bx, by), Point(cx, cy))

    val circumcenter = triangle.calculateCircumCenter()
    val circumradius = triangle.calculateCircumRadius(circumcenter)

    println("Центр описанной окружности: (${circumcenter.x}, ${circumcenter.y})")
    println("Радиус описанной окружности: $circumradius")
}

class Point(val x: Double, val y: Double)

class Triangle(private val a: Point, private val b: Point, private val c: Point) {
    fun calculateCircumCenter(): Point {
        // Центр окружности - это место пересечения перпендикулярных, делящих пополам прямых треугольника.
        // Находятся координаты середин 2х сторон
        val x4 = (a.x + b.x) / 2
        val y4 = (a.y + b.y) / 2
        val x5 = (a.x + c.x) / 2
        val y5 = (a.y + c.y) / 2

        // Вычисляются коэффициенты уравнений срединных перпендикуляров
        val a1 = b.x - a.x
        val b1 = b.y - a.y
        val a2 = c.x - a.x
        val b2 = c.y - a.y

        // Находятся коэффициенты уравнений срединных перпендикуляров
        val c1 = x4 * a1 + y4 * b1
        val c2 = x5 * a2 + y5 * b2

        // Вычисляются координаты центра окружности
        val xr = (c1 * b2 - c2 * b1) / (a1 * b2 - a2 * b1)
        val yr = (a1 * c2 - a2 * c1) / (a1 * b2 - a2 * b1)

        return Point(xr, yr)
    }

    fun calculateCircumRadius(center: Point): Double {
        val radius = sqrt((a.x - center.x).pow(2) + (a.y - center.y).pow(2))
        return radius
    }
}

// Для проверки
// (1.0, 1.0)
// (3.0, 2.0)
// (1.0, 3.0)