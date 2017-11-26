package com.uc3m.generator.model.Contracts

import java.util.Random

import com.uc3m.generator.model.FieldRandomizer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

object ContractFactory extends FieldRandomizer{

  val states = List("A", "B")

  def createRandomContract(): Contract = {

    val state = pickAtRandom(states)
    val internalContract = generateInternalContract()
    val openDate = generateDate("2014-01-01 00:00:00").withDayOfMonth(1)

    Contract(internalContract,
      generateIban(internalContract),
      generateDescription(internalContract),
      openDate,
      state,
      generateCloseDate(state, openDate))
  }


  def createContracts(): List[Contract] = {

    def go(a: Int, acc: List[Contract]): List[Contract] = {
      if (a == 0) acc
      else
        go(a - 1, createRandomContract() :: acc)
    }

    go(1, List())
  }

  def generateInternalContract(): String = {
    val products = List("003", "004")
    val contract = new Random().ints(1000000, 9999999).findAny().getAsInt
    "00010002".concat(pickAtRandom(products)).concat(contract.toString)
  }

  def generateIban(internalContract: String): String = {
    "ES300".concat(internalContract.substring(0, 8)).concat("XX").concat(internalContract.substring(8))
  }

  def generateDescription(internalContract: String): String = {

    "CON-PROD_".concat(internalContract.substring(8, 11)).concat("-TIPO_");

  }

  def generateCloseDate(state: String, openDate: DateTime): DateTime = {

    state match {
      case "B" => generateDate(openDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss")))
      case _ => new DateTime().withYear(9999).withMonthOfYear(12).withDayOfMonth(31)
    }

  }

}
