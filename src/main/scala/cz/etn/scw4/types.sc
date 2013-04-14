package cz.etn.scw4

object types {
  trait Payment {
  	def pay: Boolean
  }
  
  trait Logging extends Payment {
  
  	abstract override def pay: Boolean = {
  		println("Paying")
  		super.pay
  	}
  
  }
  
  trait Transactional extends Payment {
  
  	abstract override def pay: Boolean = {
  		println("start tx")
  		val b = super.pay
  		println("commit tx")
  		b
  	}
  
  }
  
  class StandardPayment extends Payment {
  
  	def pay = {
  		println("Std payment")
  		true
  	}
  }
  
  
  val txLogPayment = new StandardPayment with Logging with Transactional
                                                  //> txLogPayment  : cz.etn.scw4.types.StandardPayment with cz.etn.scw4.types.Log
                                                  //| ging with cz.etn.scw4.types.Transactional = cz.etn.scw4.types$$anonfun$main$
                                                  //| 1$$anon$1@699d7f6d
  
  txLogPayment.pay                                //> start tx
                                                  //| Paying
                                                  //| Std payment
                                                  //| commit tx
                                                  //| res0: Boolean = true
}