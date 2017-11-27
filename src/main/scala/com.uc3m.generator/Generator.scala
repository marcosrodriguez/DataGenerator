package com.uc3m.generator

import com.uc3m.generator.model.Client.ClientFactory
import com.uc3m.generator.model.ContractClientRelations.RelationsFactory
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

object Generator extends App{

  val sparkConf = new SparkConf()
    .setAppName("generator")
    .setMaster("local[1]")
    .set("spark.driver.allowMultipleContexts", "true");

  val sc = new SparkContext(sparkConf)

  val sqlCtx = new SQLContext(sc)


  val int1RDD = sqlCtx.range(0, 1000).rdd
  val clientsRDD = int1RDD.map(i => ClientFactory.createRandomPeople()).persist()
  val contractRelactionsRDD = clientsRDD.flatMap(client => RelationsFactory.createInterventions(client)).persist()
  val contractsRDD = contractRelactionsRDD.map(x => x._1)
  val relationsRDD = contractRelactionsRDD.map(x => x._2)

  val file = "GeneratedData/"

  clientsRDD.map(_.toString()).saveAsTextFile(file.concat("Clientes"))
  contractsRDD.map(_.toString()).saveAsTextFile(file.concat("Contratos"))
  relationsRDD.map(_.toString()).saveAsTextFile(file.concat("Relaciones"))



}
