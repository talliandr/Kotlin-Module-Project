import java.util.Scanner

class Notes {
    val scanner = Scanner(System.`in`)
    val menu = Menu(scanner)
    val archives = mutableListOf<Archive>()

    var currentScreen = Screen.ARCHIVE_LIST
    var currentArchive: Archive? = null
    var currentNote: Note? = null

    fun start() {
        while (currentScreen != Screen.EXIT) {
            when (currentScreen) {
                Screen.ARCHIVE_LIST -> archiveListScreen()
                Screen.CREATE_ARCHIVE -> createArchiveScreen()
                Screen.NOTE_LIST -> noteListScreen()
                Screen.CREATE_NOTE -> createNoteScreen()
                Screen.NOTE_VIEW -> noteViewScreen()
                Screen.EXIT -> break
            }
        }
    }
    fun archiveListScreen() {
        val options = mutableListOf("Создать архив")
        options.addAll(archives.map { it.name })
        options.add("Выход")

        menu.drawMenu("Список архивов:", options)
        val userChoice = menu.validInput(options.size - 1) ?: return

        when (userChoice) {
            0 -> currentScreen = Screen.CREATE_ARCHIVE
            options.size - 1 -> currentScreen = Screen.EXIT
            else -> {
                currentArchive = archives[userChoice - 1]
                currentScreen = Screen.NOTE_LIST
            }
        }
    }
    fun createArchiveScreen() {
        val name = menu.notEmpty("Введите название архива:")
        archives.add(Archive(name))
        println("Архив создан!")
        currentScreen = Screen.ARCHIVE_LIST
    }
    fun noteListScreen() {
        val archive = currentArchive ?: return
        val options = mutableListOf("Создать заметку")
        options.addAll(archive.notes.map { it.title })
        options.add("Вернуться")

        menu.drawMenu("Архив: ${archive.name}", options)
        val userChoice = menu.validInput(options.size - 1) ?: return

        when (userChoice) {
            0 -> currentScreen = Screen.CREATE_NOTE
            options.size - 1 -> currentScreen = Screen.ARCHIVE_LIST
            else -> {
                currentNote = archive.notes[userChoice - 1]
                currentScreen = Screen.NOTE_VIEW
            }
        }
    }
    fun createNoteScreen() {
        val title = menu.notEmpty("Введите название заметки:")
        val content = menu.notEmpty("Введите текст заметки:")
        currentArchive?.notes?.add(Note(title, content))
        println("Заметка создана!")
        currentScreen = Screen.NOTE_LIST
    }
    fun noteViewScreen() {
        val note = currentNote ?: return
        println("\n Заметка: ${note.title} ")
        println(note.content)
        menu.anyKey()
        currentScreen = Screen.NOTE_LIST
    }
}