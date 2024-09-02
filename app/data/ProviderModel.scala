package data

import scala.math.Ordered.orderingToOrdered

case class WeightRange[Int](min: Int, max: Int) {

  def contains(v: Int)(implicit ordering: Ordering[Int]): Boolean = {
    min <= v && max >= v
  }
}

case class PricesRange(prices: Map[WeightRange[Int], Int])