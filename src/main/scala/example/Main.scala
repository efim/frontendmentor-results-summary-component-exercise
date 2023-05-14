package example

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}

@main
def resultsSummaryComponent(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Main.resultsSummary()
  )

object Main {
  def resultsSummary(): Element = div(
    """  Your Result
  76
  of 100

  Great
  You scored higher than 65% of the people who have taken these tests.

  Summary

  Reaction
  80 / 100

  Memory
  92 / 100

  Verbal
  61 / 100

  Visual
  72 / 100

  Continue

"""
  )
}
