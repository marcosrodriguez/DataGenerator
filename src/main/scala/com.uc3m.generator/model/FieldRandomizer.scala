package com.uc3m.generator.model

import java.sql.Timestamp

import org.joda.time.DateTime

import scala.util.Random

trait FieldRandomizer {

  def pickAtRandom[A](values: List[A]): A = {
    val rand = Random
    val random_index = rand.nextInt(values.length)
    values(random_index)
  }

  def rollD100(threshold: Int): Boolean = {
    val n = Random.nextInt(100)
    if (n < threshold) false
    else true
  }

  def getDifferentValue[A] (values: List[A], value: A) : A = {
    val newValue = pickAtRandom(values)
    if (newValue == value) getDifferentValue(values, value)
    else newValue
  }

  def getDifferentWeightedValue[A] (values: List[A], weights: List[Int], value: A) : A = {
    val newValue = pickAtRandomWeighted(values, weights)
    if (newValue == value) getDifferentWeightedValue(values, weights, value)
    else newValue
  }

  def getRandomTwoValues(start: Int, end: Int) : Int = {
    val rnd = new scala.util.Random
    start + rnd.nextInt(end - start)
  }

  def pickAtRandomWeighted[A](values: List[A], weights: List[Int]) : A = {
    if (values.length != weights.length) throw new ArrayIndexOutOfBoundsException("Values and weights must have the same " +
      "lenght")
    val weightedSeq = (for (i <- 0 to values.length-1) yield (values(i), weights(i))).sortBy(_._2)
    val cumulativeWeightedSeq = {
      def accumulate(seq: Seq[(A, Int)], acc: Int) : Seq[(A, Int)] = {
        if (seq.isEmpty) Seq()
        else {
          val newItem = (seq.head._1, seq.head._2 + acc)
          newItem +: accumulate(seq.tail, acc + seq.head._2)
        }
      }
      accumulate(weightedSeq, 0)
    }
    var weightSum = 0
    weightedSeq.foreach(pair => weightSum += pair._2)
    val rand = Random.nextInt(weightSum)
    cumulativeWeightedSeq.find(pair => pair._2 > rand).get._1
  }

  def generateDate(init_date: String): DateTime ={
    val rangebegin: Long = Timestamp.valueOf(init_date).getTime()
    val rangeend: Long = new DateTime().getMillis
    val diff: Long = rangeend - rangebegin + 1;
    val rand: Long = rangebegin + (Math.random()*diff).toLong
    new DateTime(rand)
  }

}
