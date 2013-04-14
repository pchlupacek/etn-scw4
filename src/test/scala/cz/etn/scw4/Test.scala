package cz.etn.scw4

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers

import Language._
import SkillLevel._

class Test extends FunSuite with ShouldMatchers {
  
  val zdenek = new Programmer("Zdenek", 20, Map(Java -> Noob, Scala -> Noob))
  
  test("empty queue"){
	val q = new Queue 
	q.size should be(0)
	
	//q.dequeue should be 
  }
  
  
  
  test("queue enq spec"){
    val q: Queue = new Queue
    val q2 = q.enqueue(zdenek)
    q2.size should be (1)
    
    val (q3, pr) = q2.dequeue 
    q3.size should be(0)
    pr should be(zdenek) 
  }

}