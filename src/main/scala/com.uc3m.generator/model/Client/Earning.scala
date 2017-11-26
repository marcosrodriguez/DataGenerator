package com.uc3m.generator.model.Client

case class Earning (haveSalary: Boolean,
                    haveTransfer: Boolean,
                    isInactive: Boolean,
                    amount: Double,
                    valueDate: Int,
                    concept: String,
                    periodicity: String,
                    children: Int,
                    haveMortgate: Boolean,
                    haveRenting: Boolean,
                    haveCar: Boolean,
                    haveSchool: Boolean){

  override def toString() = f"$haveSalary;$haveTransfer;$isInactive;$amount;$valueDate;$concept;$periodicity;$children;$haveMortgate;$haveRenting;$haveCar"

}
