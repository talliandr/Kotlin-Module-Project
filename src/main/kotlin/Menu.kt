import java.util.Scanner

class Menu(val scanner: Scanner) {
    fun drawMenu(title: String, options: List<String>) {
        println("\n $title ")
        options.forEachIndexed { index, option ->
            println("$index - $option")
        }
        println("Выберите пункт:")
    }
    fun validInput(maxIndex: Int): Int? {
        val input = scanner.nextLine()
        val index = input.toIntOrNull()
        if (index == null) {
            println("Введите цифру!")
            return null
        }
        if (index !in 0..maxIndex) {
            println("Такой цифры нет. Введите корректную!")
            return null
        }
        return index
    }
    fun notEmpty(txt: String): String {
        while (true) {
            println(txt)
            val text = scanner.nextLine()
            if (text.isNotBlank()) return text
            println("Поле не может быть пустым!")
        }
    }
    fun anyKey() {
        println("\nНажмите любую клавишу... ")
        scanner.nextLine()
    }
}