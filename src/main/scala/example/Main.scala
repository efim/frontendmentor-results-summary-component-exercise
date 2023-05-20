package example

import org.scalajs.dom
import com.raquo.laminar.api.L.{*, given}
import Data.CategoryResult
import com.raquo.airstream.web.AjaxStream
import com.raquo.laminar.DomApi
import io.laminext.fetch._
import concurrent.ExecutionContext.Implicits.global

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
          className := "py-7 w-full h-full",
          button(
            "Continue",
            className := "text-white text-2xl font-bold bg-[#303B59] h-20 w-full rounded-full"
          )
        )
      )
    )
  }

  def renderCategoryScore(result: CategoryResult): Element = {

    val svgRaw = Fetch.get(result.iconPath).text.map(_.data)
    def loadingDiv = div(className := "lds-dual-ring")
    val svgIcon = svgRaw
      .map(str =>
        foreignSvgElement(DomApi.unsafeParseSvgString(str))
          .amend(svg.className := "justify-self-center w-8 h-8")
      )
      .startWith(loadingDiv)

    div(
      className := "py-3 w-full",
      div(
        className := "grid grid-cols-7 w-full h-20 rounded-xl items-center ",
        styleAttr := s"--custom-bg: ${result.bgColor}; --custom-highlight: ${result.highlightColor}",
        className := "bg-[--custom-bg]",
        child <-- svgIcon,
        p(
          className := "col-span-4 text-2xl text-[--custom-highlight] font-bold ",
          result.name
        ),
        div(
          className := "col-span-2 justify-self-center text-2xl flex flex-row",
          p(s"${result.score}", className := "font-semibold pr-2 "),
          p(" / 100", className := "text-gray-400 font-semibold")
        )
      )
    )
  }
}
