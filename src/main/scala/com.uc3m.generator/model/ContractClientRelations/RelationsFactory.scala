package com.uc3m.generator.model.ContractClientRelations

import com.uc3m.generator.model.Client.Client
import com.uc3m.generator.model.Contracts.{Contract, ContractFactory}
import com.uc3m.generator.model.FieldRandomizer

object RelationsFactory extends FieldRandomizer{

  val currencies = List("EUR", "USD", "GBP")
  val interventionTypes = List("T")
  val relations = List("A", "B", "O")

  def createRelation(client: Client, contract: Contract): Relation = {
    Relation(client.id, contract.contract_id, "T", "0", contract.openDate, contract.state, contract.closeDate)
  }

  def createInterventions(client: Client): List[(Contract, Relation)] = {
    //val ctnum = new Random().ints(1, 5).findAny().getAsInt
    val ctnum = 1
    def go(order: Int, acc: List[(Contract, Relation)], ahorro: Boolean): List[(Contract, Relation)] = {
      order match {
        case 0 => acc
        case _ => {
          val contract = ContractFactory.createRandomContract();
          val relation = createRelation(client, contract)
          if (!ahorro) {
            if (contract.contract_id.substring(8, 11).equals("003")) {
              go(order - 1, (contract, relation) :: acc, true);
            } else {
              go(order - 1, (contract, relation) :: acc, ahorro);
            }
          } else {
            if (contract.contract_id.substring(8, 11).equals("003")) {
              go(order, acc, ahorro);
            } else {
              go(order - 1, (contract, relation) :: acc, ahorro);
            }
          }
        }
      }
    }
    go(ctnum, List(), false)
  }

}
