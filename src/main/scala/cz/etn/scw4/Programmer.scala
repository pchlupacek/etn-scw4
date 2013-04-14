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

class Person(val name: String, val age: Int)

case class Programmer(override val name: String,
    override val age: Int,
    languages: Map[Language, SkillLevel])
  extends Person(name, age)

case class Item[A](pr: A, next: Option[Item[A]]) {
  def length: Int = 1 + (if (next.isDefined) next.get.length else 0)
}

class Queue[A] private (start: Option[Item[A]], end: Option[Item[A]]) {

  def this() = this(None, None)

  def size: Int = if (start.isDefined) start.get.length else 0

  def enqueue(pr: A): Queue[A] = {
    val newEndItem = Some(Item(pr, None))
    def copy(i: Option[Item[A]]): Option[Item[A]] = i match {
      case None => newEndItem
      case Some(Item(pr, next)) => Some(Item(pr, copy(next)))
    }
    new Queue(copy(start), newEndItem)
  }

  def dequeue: (Queue[A], A) = (new Queue(start.get.next, end), start.get.pr)
}

object NameExtractor {
  def extractNames(queue: Queue[Person]): List[String] = if (queue.size > 0) {
    val (rest, name) = queue.dequeue
    name.name :: extractNames(rest)
  } else {
	  Nil
  }
}