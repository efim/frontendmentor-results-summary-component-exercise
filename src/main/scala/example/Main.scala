package example

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import Data.CategoryResult

@main
def resultsSummaryComponent(): Unit =
  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    Main.resultsSummary(Data.hardcoded)
  )

object Main {
  def resultsSummary(results: List[CategoryResult]): Element = {
    val totalScore = results.map(_.score).sum / results.size
    div(
      className := "flex flex-col items-center h-screen",
      div(
        className := "flex flex-col items-center w-full rounded-b-[40px]",
        className := "bg-gradient-to-b from-[#7643FF] to-[#2E2CE9]",
        p(
          "Your Result",
          className := "text-[#D4CAFF] text-2xl font-semibold p-8 "
        ),
        div(
          className := "rounded-full bg-blue-900 h-56 w-56 flex flex-col justify-center items-center p-14",
          className := "bg-gradient-to-b from-[#4A23CC] to-[#4734f0]",
          p(s"$totalScore", className := "text-7xl font-bold text-white"),
          p("of 100", className := "text-xl font-semibold text-[#8a80ff] p-2")
        ),
        div(
          className := "flex flex-col items-center",
          p("Great", className := "p-6 text-white text-4xl font-semibold"),
          p(
            "You scored higher than 65% of the people who have taken these tests.",
            className := "text-[#B4B6FF] text-lg font-semibold text-center pb-16 px-32 "
          )
        )
      ),
      div(
        className := "flex flex-col items-center pt-10",
        p("Summary"),
        results.map(renderCategoryScore(_))
      ),
      button("Continue")
    )
  }

  def renderCategoryScore(result: CategoryResult): Element = {
    div(
      className := "flex flex-row",
      p(result.name),
      p(s"${result.score} / 100")
    )
  }
}
