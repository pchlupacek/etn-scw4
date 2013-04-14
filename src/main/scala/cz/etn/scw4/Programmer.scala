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

case class Item(pr: Programmer, next: Option[Item]) {

  def length: Int = 1 + (if (next.isDefined) next.get.length else 0)

}

class Queue private (start: Option[Item], end: Option[Item]) {

  def this() = this(None, None)

  def size: Int = if (start.isDefined) start.get.length else 0

  def enqueue(pr: Programmer): Queue = {
    val newEndItem = Some(Item(pr, None))
    def copy(i: Option[Item]): Option[Item] = i match {
      case None => newEndItem        
      case Some(Item(pr, next)) => Some(Item(pr, copy(next)))
    }    
    new Queue(copy(start), newEndItem)
  }

  def dequeue: (Queue, Programmer) = (new Queue(start.get.next, end), start.get.pr)
}