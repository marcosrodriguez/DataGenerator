package com.uc3m.generator.model.Client

import scala.collection.mutable.ArrayBuffer

case class Client (id: String,
                   nombre: String,
                   segmento: String,
                   nivel: String,
                   earning: Earning,
                   expenses: ArrayBuffer[Expenses]
                  ) {


  override def toString() = f"$id;$nombre;$segmento;$nivel"

}
