package cz.etn.scw4

object Language extends Enumeration {
  type Language = Value
  val Java, Scala, Php = Value
}

object SkillLevel extends Enumeration {
  type SkillLevel = Value
  val Noob, Intermediate, Expert, Guru = Value
}

import Language._
import SkillLevel._

case class Programmer(name: String, age: Int, languages: Map[Language, SkillLevel])

case class Item(pr: Programmer, next: Option[Item])

class Queue private (start: Option[Item], end: Option[Item]) {
  
  def this() = this(None, None)
  def size: Int = if (start.isDefined) 1 else 0
  def enqueue(pr: Programmer) : Queue = {
    val newItem = Item(pr, None)
    new Queue(Some(newItem), Some(newItem))
  }
  
}