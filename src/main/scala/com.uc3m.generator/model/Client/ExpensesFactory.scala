package com.uc3m.generator.model.Client

import java.util.Random

import com.uc3m.generator.model.FieldRandomizer
import org.joda.time.DateTime

import scala.collection.mutable.ArrayBuffer

object ExpensesFactory extends FieldRandomizer{

  val MONTHLY = "MONTHLY"
  val ANNUAL = "ANNUAL"

  def mortgateExpense(salary: Double, haveMortgate: Boolean): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (haveMortgate) {
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(10).withMonthOfYear(11), false, null, null, "IMPUESTO IBI", getRandomTwoValues(100, (salary / 6).toInt), false, 0, 0, ANNUAL, "IBI"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(3), false, null, null, "RECIBO COM. PROP. BLAZQUILLA 8 AL 16 Nº RECIBO 0073 0100 755 BBZDJNB REF. MANDATO 101A", getRandomTwoValues(40, (salary / 10).toInt), false, 0, 0, MONTHLY, "COMUNIDAD"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(19).withMonthOfYear(1), false, null, null, "SEGUROS SANTANDER DE SEGUROS VIDA,S.A.", getRandomTwoValues(100, (salary / 6).toInt), false, 0, 0, ANNUAL, "SEGURO_VIDA"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(4).withMonthOfYear(1), false, null, null, "RECIBO SANTANDER DE SEGUROS Y REASEGUROS, S.A. Nº RECIBO 0073 0100 755 BBZPMCK REF. MANDATO 269", getRandomTwoValues(100, (salary / 6).toInt), false, 0, 0, ANNUAL, "SEGURO_HOGA"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(10).withMonthOfYear(11), false, null, null, "RECIBO IMPUESTO BASURA", getRandomTwoValues(20, (salary / 15).toInt), false, 0, 0, ANNUAL, "BASURAS"))
    }
    expenseList
  }

  def homeExpense(haveMortgate: Boolean, haveRenting: Boolean): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (haveMortgate || haveRenting) {
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(7), false, null, null, "RECIBO IBERDROLA COMERCIALIZ. ULTIMO RECURSO, S Nº RECIBO 0073 0100 755 BFDPJWW REF. MANDATO 0003541", 0, true, 30, 60, MONTHLY, "LUZ"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(10), false, null, null, "RECIBO CANAL DE ISABEL II GESTION, S. Nº RECIBO 0073 0100 755 BFDVPKK REF. MANDATO 00008143877220091", 0, true, 15, 30, MONTHLY, "AGUA"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(8), false, null, null, "ELECTRICIDAD ENDESA ENERGIA, S.A. FACTURA DE GAS PM", 0, true, 30, 60, MONTHLY, "GAS"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(21), false, null, null, "RECIBO VODAFONE ESPANA S.A.U. Nº RECIBO 0073 0100 755 BCBHHCR REF. MANDATO 0910024644", 0, true, 50, 100, MONTHLY, "TELEPHONE"))
    }
    expenseList
  }

  def otherExpense(haveSalary: Boolean, children: Int, salary: Double): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (rollD100(85)) expenseList.+=(Expenses(new DateTime().withDayOfMonth(1), false, null, null, "ADEUDO RECIBO DREAMFIT VILLAVERDE", 25, false, 0, 0, MONTHLY, "HOBBIES"))
    if (rollD100(70)) expenseList.+=(Expenses(new DateTime().withDayOfMonth(21), false, null, null, "COMPRA TARJETA XXXXXXXXXXXXXXX PAYPAL *SPOTIFY-99999999999", 10, false, 0, 0, MONTHLY, "HOBBIES"))
    if (rollD100(75)) expenseList.+=(Expenses(new DateTime().withDayOfMonth(28), false, null, null, "COMPRA TARJETA XXXXXXXXXXXXXXX NETFLIX-AMSTERDAM", 10, false, 0, 0, MONTHLY, "HOBBIES"))
    if (haveSalary && salary > 2000 && rollD100(75)) {
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(25), false, null, null, "RECIBO ADESLAS MADRID Nº RECIBO 0073 0100 755 BFCRDMS REF. MANDATO 000282212844", getRandomTwoValues(30, 60), false, 0, 0, MONTHLY, "TELEFONO"))
    }
    if (haveSalary) {
      val peopleToBuy = children + 1
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(15), true, new DateTime().withDayOfMonth(12), new DateTime().withDayOfMonth(18), "COMPRA EN AHORRAMAS, CON LA TARJETA : 9999999999999999", getRandomTwoValues(40, 100) * peopleToBuy, false, 0, 0, MONTHLY, "FOOD"))
      if (rollD100(90)) expenseList.+=(Expenses(new DateTime().withDayOfMonth(15), false, null, null, "RECIBO SANTANDER CONSUMER FINANCE, S.A. Nº RECIBO 0073 0100 755 BBYGVCH REF. MANDATO U10931021825", getRandomTwoValues(50, 130), false, 0, 0, MONTHLY, "CREDIT"))
    }
    expenseList
  }

  def carExpense(haveCar: Boolean): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (haveCar) {
      expenseList.+=(Expenses(new DateTime().withDayOfWeek(1), true, new DateTime().withDayOfWeek(1), new DateTime().withDayOfWeek(3), "COMPRA EN E.S. SHELL LOS MANUELOS, CON LA TARJETA : 9999999999999999", 0, true, 30, 70, "WEEKLY", "CAR"))
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(18).withMonthOfYear(12), false, null, null, "RECIBO IMPUESTO MATRICULACION", getRandomTwoValues(30, 70), false, 0, 0, ANNUAL, "CAR"))
    }
    expenseList
  }

  def schoolExpense(haveSchool: Boolean, children: Int): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (haveSchool) {
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(2), false, null, null, "RECIBO COLEGIO XXXXXXXX S.L. Nº RECIBO 0073 0100 755 BFCHGMZ REF. MANDATO ES83AÑLSFJOID15328", getRandomTwoValues(100, 200) * children, false, 0, 0, MONTHLY, "SCHOOL"))
    }
    expenseList
  }

  def rentingExpense(haveRenting: Boolean, salary: Double): ArrayBuffer[Expenses] = {
    var expenseList: ArrayBuffer[Expenses] = new ArrayBuffer[Expenses]()
    if (haveRenting) {
      expenseList.+=(Expenses(new DateTime().withDayOfMonth(1), true, new DateTime().withDayOfMonth(1), new DateTime().withDayOfMonth(5), "Transferencia ALQUILER", getRandomTwoValues(150, (salary / 4).toInt), false, 0, 0, MONTHLY, "RENTING_HOME"))
    }
    expenseList
  }


  def getExpenseAmount(expense: Expenses): Int ={
    expense.isRange match {
      case true => new Random().ints(expense.startAmount, expense.endAmount).findAny().getAsInt
      case false => expense.amountFixed
    }
  }

  def getExpenseDay(expense: Expenses) : Int = {
    expense.isRangeDate match {
      case true => new Random().ints(expense.startDate.getDayOfMonth, expense.endDate.getDayOfMonth).findAny().getAsInt
      case false => expense.dateFixed.getDayOfMonth
    }
  }

}
