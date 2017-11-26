package com.uc3m.generator.model.Client

import org.joda.time.DateTime

case class Expenses(dateFixed: DateTime,
                    isRangeDate: Boolean,
                    startDate: DateTime,
                    endDate: DateTime,
                    concept: String,
                    amountFixed: Int,
                    isRange: Boolean,
                    startAmount: Int,
                    endAmount: Int,
                    periodicity: String,
                    expenseType: String) {

}
