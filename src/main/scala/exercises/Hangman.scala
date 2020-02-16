package exercises

import java.util.{Random, Scanner}


// Используя функции io.readLine и io.printLine напишите игру "Виселица"
// Пример ввода и тест можно найти в файле src/test/scala/fintech/homework01/HangmanTest.scala
// Тест можно запустить через в IDE или через sbt (написав в консоли sbt test)

// Правила игры "Виселица"
// 1) Загадывается слово
// 2) Игрок угадывает букву
// 3) Если такая буква есть в слове - они открывается
// 4) Если нет - рисуется следующий элемент висельника
// 5) Последней рисуется "веревка". Это означает что игрок проиграл
// 6) Если игрок все еще жив - перейти к пункту 2

// Пример игры:

// Word: _____
// Guess a letter:
// a
// Word: __a_a
// Guess a letter:
// b
// +----
// |
// |
// |
// |
// |

// и т.д.

class Hangman(io: IODevice) {
  def play(word: String): Unit = {

    var currentWord = "_" * word.length
    var previous = "_" * word.length

    var game = true
    var win = false

    var listIndex = 0

    io.printLine("Word: " + currentWord)

    while (game){
      io.printLine("Guess a letter:")

      var success = false

      val line = io.readLine()
      if(!line.equals("")){

        val letter = line.toCharArray()(0);

        if(word.contains(letter)){
          success = true
          var i = 0
          val sb = new StringBuilder()


          while (i < word.length){
            if(word(i) == letter){
              sb.append(letter)
            } else {
              sb.append(previous(i))
            }
            i = i + 1
          }
          currentWord = sb.toString()
        }
        else
          listIndex += 1

        if(listIndex == stages.length - 1) {
          win = false
          game = false
        }

        if(!currentWord.contains("_")){
          win = true
          game = false
        }

        previous = currentWord
      }
      if(success)
        io.printLine("Word: " + currentWord)
      else
        io.printLine(stages(listIndex))
    }

    if(win)
      io.printLine("GG")
    else
      io.printLine("You are dead")
  }

  val stages = List(
    """+----
      ||
      ||
      ||
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||  /
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||   |
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||  /|
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||
      ||   O
      ||  /|\
      ||  / \
      ||
      |""".stripMargin,
    """+----
      ||   |
      ||   O
      ||  /|\
      ||  / \
      ||
      |""".stripMargin
  )

  def getRandomNumber(min : Int, max : Int): Int ={
    val r = new Random()
    r.nextInt((max - min) + 1) + min
  }

}

