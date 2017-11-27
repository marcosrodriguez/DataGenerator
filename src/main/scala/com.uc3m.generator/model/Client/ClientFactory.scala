package com.uc3m.generator.model.Client

import java.util.Random

import com.uc3m.generator.model.FieldRandomizer

import scala.collection.mutable.ArrayBuffer

object ClientFactory extends FieldRandomizer{


  val names = List("Roberto", "Laura", "Elena", "Mario", "Ruben")
  val surnames = List("Gutierrez", "Garcia", "Rodriguez", "Fernandez");
  val levels = List("00", "50", "99")
  val segments = List("RT", "SB", "PB")

  val MONTHLY = "MONTHLY"
  val ANNUAL = "ANNUAL"

  def createRandomPeople(): Client = {
    val segment = pickAtRandom(segments)
    val name = getName()
    val level = pickAtRandom(levels)
    val id = generateId()
    val earning = generateEarning()

    Client(
      id, name,
      segment, level, earning,
      generateExpense(earning)
    )
  }

  def generateEarning(): Earning = {

    val haveSalary = rollD100(20)
    val haveTransfer = if (!haveSalary) rollD100(85)
    else false
    val isInactive = if(!haveTransfer && !haveSalary) true else false
    val amount =  if(haveSalary) getRandomTwoValues(700, 4000) else if(haveTransfer) getRandomTwoValues(200, 400) else 0
    val valueDate = getRandomTwoValues(25,28)
    val concept = if(haveSalary) "TRANSFERENCIA DE GRUPO CORPORATIVO GFI INFORMATICA, S.A., CONCEPTO ABONO NOMINA {date}"
    else if (haveTransfer) "TRANSFERENCIA DE XXXXXXXX" else null

    val children = if (haveSalary && rollD100(50)) getRandomTwoValues(0,3) else 0
    val haveMortgage =  if(haveSalary && amount > 1300) rollD100(40) else false
    val haveRenting = if(!haveMortgage && haveSalary) rollD100(40) else false
    val haveCar = if(haveSalary) rollD100(50) else false
    val haveSchool = if(haveSalary && children > 0 && amount > 2500) rollD100(60) else false

    Earning(haveSalary, haveTransfer, isInactive, amount, valueDate, concept, MONTHLY, children, haveMortgage, haveRenting, haveCar, haveSchool)
  }

  def generateExpense(earning: Earning) : ArrayBuffer[Expenses] = {

    var expenseList:ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()

    expenseList.++=(ExpensesFactory.mortgateExpense(earning.amount, earning.haveMortgate))
    expenseList.++=(ExpensesFactory.homeExpense(earning.haveMortgate, earning.haveRenting))
    expenseList.++=(ExpensesFactory.carExpense(earning.haveCar))
    expenseList.++=(ExpensesFactory.schoolExpense(earning.haveSchool, earning.children))
    expenseList.++=(ExpensesFactory.rentingExpense(earning.haveRenting, earning.amount))
    expenseList.++=(ExpensesFactory.otherExpense(earning.haveSalary, earning.children, earning.amount))

    expenseList
  }

  def getName() : String = {
    val name = pickAtRandom(names)
    val surname = pickAtRandom(surnames)
    name.concat(" ").concat(surname)
  }

  def generateId(): String = {
    val id_number = new Random().ints(100000000, 999999999).findAny().getAsInt
    id_number.toString
  }

}
