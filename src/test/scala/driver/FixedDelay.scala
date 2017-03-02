package driver

object FixedDelay {

  import scala.concurrent.duration._

  def apply(duration: Long) {
    val delayFor = Duration(duration, MILLISECONDS)
    val deadline = delayFor.fromNow
    while (deadline.hasTimeLeft()) { /* just wasting some cycles for a fixed period */ }
  }
}
