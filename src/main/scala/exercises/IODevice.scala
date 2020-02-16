package exercises


trait IODevice {
  def printLine(text: String): Unit
  def readLine(): String
}

