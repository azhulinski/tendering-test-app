package data

import scala.math.Ordered.orderingToOrdered

case class WeightRange[Int](min: Int, max: Int)(implicit ordering: Ordering[Int]) {

  def contains(v: Int): Boolean = {
    min <= v && max >= v
  }
}

case class PricesRange(prices: Map[WeightRange[Int], Int])