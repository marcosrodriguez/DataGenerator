package com.uc3m.generator.model.ContractClientRelations

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

case class Relation(client_id: String,
                    contract_id: String,
                    interventionType: String,
                    interventionOrder: String,
                    openDate: DateTime,
                    relationState: String,
                    closeDate: DateTime){

  override def toString()= f"$client_id;$contract_id;$interventionType;$interventionOrder;${openDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd"))};$relationState;${closeDate.toString(DateTimeFormat.forPattern("yyyy-MM-dd"))}"

}
