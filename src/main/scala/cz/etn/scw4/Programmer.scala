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