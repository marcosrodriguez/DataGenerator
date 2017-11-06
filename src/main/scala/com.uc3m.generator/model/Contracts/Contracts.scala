package com.uc3m.generator.model.Contracts

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class Contracts (contract_id: String,
                      iban: String,
                      description: String,
                      openDate: DateTime,
                      state: String,
                      closeDate: DateTime
                     ){


  override def toString() = f"$contract_id;$iban;$description;${openDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd"))};$state;${closeDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd"))}"

}
