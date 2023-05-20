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
      className := "h-full w-screen h-screen flex flex-row items-center justify-center ",
      div(
        className := "flex flex-col items-center h-screen",
        className := "lg:flex-row lg:h-[750px] lg:w-[1080px] lg:place-self-center lg:shadow-custom rounded-[40px]  ",
        renderTotal(totalScore),
        renderSummary(results)
      )
    )
  }

  def renderTotal(totalScore: Int) = {
    div(
      className := "flex flex-col items-center w-full rounded-b-[40px] h-2/5 ",
      className := "lg:rounded-[40px] lg:h-full lg:w-1/2 lg:justify-center ",
      className := "bg-gradient-to-b from-[#7643FF] to-[#2E2CE9]",
      p(
        "Your Result",
        className := "text-[#D4CAFF] text-lg font-semibold p-5 ",
        className := "lg:text-4xl lg:pb-14"
      ),
      div(
        className := "rounded-full bg-blue-900 h-36 w-36 flex flex-col justify-center items-center py-10",
        className := "lg:h-[300px] lg:w-[300px] lg:p-1",
        className := "bg-gradient-to-b from-[#4A23CC] to-[#4734f0]",
        p(
          s"$totalScore",
          className := "text-6xl font-extrabold text-white ",
          className := "lg:text-8xl"
        ),
        p("of 100",
          className := "text-base font-bold text-[#8a80ff] block px-10",
          className := "lg:font-bold"
        )
      ),
      div(
        className := "flex flex-col items-center",
        p("Great",
          className := "text-white text-2xl font-bold pt-5 pb-2",
          className := "lg:text-5xl lg:p-8"
        ),
        p(
          "You scored higher than 65% of the people who have taken these tests.",
          className := "text-[#B4B6FF] text-base font-semibold text-center pb-16 px-16 ",
          className := "lg:px-20 lg:text-[28px] lg:font-normal"
        )
      )
    )
  }

  def renderSummary(results: List[CategoryResult]) = {
    div(
      className := "flex flex-col w-full px-7 lg:px-14",
      className := " lg:w-1/2 lg:h-full lg:rounded-r-[40px] lg:relative",
      p("Summary", className := "font-bold text-xl py-6 lg:pt-12 lg:text-4xl"),
      results.map(renderCategoryScore(_)),
      div(
        className := "py-4 w-full h-full",
        className := "lg:pt-14",
        button(
          "Continue",
          className := "text-white text-2xl font-bold bg-[#303B59] h-20 w-full rounded-full",
          className := "active:bg-[#4535F0] duration-75"
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
          .amend(svg.className := "justify-self-center w-6 h-6")
      )
      .startWith(loadingDiv)

    div(
      className := "py-2 w-full",
      div(
        className := "grid grid-cols-7 w-full h-14 rounded-xl items-center ",
        styleAttr := s"--custom-bg: ${result.bgColor}; --custom-highlight: ${result.highlightColor}",
        className := "bg-[--custom-bg]",
        className := "lg:rounded-2xl",
        child <-- svgIcon,
        p(
          className := "col-span-4 text-lg text-[--custom-highlight] font-bold ",
          result.name
        ),
        div(
          className := "col-span-2 justify-self-center text-lg flex flex-row lg:pr-6",
          p(s"${result.score}", className := "font-semibold pr-2 "),
          p(" / 100", className := "text-gray-400 font-semibold")
        )
      )
    )
  }
}
