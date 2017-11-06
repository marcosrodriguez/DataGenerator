package com.uc3m.generator.model.Client

case class Client (id: String,
                   name: String,
                   surname: String,
                   client_type: String,
                  ){

  override def toString() = f"$id;$name;$surname;$client_type"

}
