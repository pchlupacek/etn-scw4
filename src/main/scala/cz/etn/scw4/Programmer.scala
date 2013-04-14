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

trait Interviewer[-A] {
  def interview(p: A): Boolean
}

object DicriminatingInterviewer extends Interviewer[Person] {
  override def interview(p: Person): Boolean = p.age <= 25
}

object ScalaInterviewer extends Interviewer[Programmer] {
  def interview(p: Programmer): Boolean = p.languages.get(Scala).exists(_ == Guru)
}

case class Item[+A](pr: A, next: Option[Item[A]]) {
  def length: Int = 1 + (if (next.isDefined) next.get.length else 0)
}


class Queue[+A] private (start: Option[Item[A]], end: Option[Item[A]]) {

  def this() = this(None, None)

  def size: Int = if (start.isDefined) start.get.length else 0

  def enqueue[B >: A](pr: B): Queue[B] = {
    val newEndItem = Some(Item(pr, None))
    def copy(i: Option[Item[B]]): Option[Item[B]] = i match {
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