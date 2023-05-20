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
          p(s"$totalScore", className := "text-7xl font-extrabold text-white "),
          p("of 100", className := "text-2xl font text-[#8a80ff] p-2")
        ),
        div(
          className := "flex flex-col items-center",
          p("Great", className := "p-6 text-white text-4xl font-bold"),
          p(
            "You scored higher than 65% of the people who have taken these tests.",
            className := "text-[#B4B6FF] text-2xl font-semibold text-center pb-16 px-32 "
          )
        )
      ),
      div(
        className := "flex flex-col items-start w-full px-10",
        p("Summary", className := "font-bold text-2xl py-8"),
        results.map(renderCategoryScore(_)),
        div(
          className := "py-4 w-full h-full",
          button(
            "Continue",
            className := "text-white text-2xl font-bold bg-[#303B59] h-20 w-full rounded-full"
          )
        )
      )
    )
  }

  def renderCategoryScore(result: CategoryResult): Element = {
    div(
      className := "py-2 w-full",
      div(
        className := "grid grid-cols-7 w-full h-20 rounded-xl items-center ",
        className := result.backgroundColorClass,
        p(className := "justify-self-center text-2xl", "ICON"),
        p(className := "col-span-4 text-2xl", result.name),
        div(
          className := "col-span-2 justify-self-center text-2xl flex flex-row",
          p(s"${result.score}", className := "font-bold pr-2"),
          p(" / 100", className := "text-gray-400 font-semibold")
        )
      )
    )
  }
}
