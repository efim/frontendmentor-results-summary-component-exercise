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
      className := " w-full h-full flex flex-col items-center justify-center ",
      div(
        className := "flex flex-col items-center ",
        className := "lg:flex-row lg:h-[500px] lg:w-[720px] lg:place-self-center lg:shadow-custom rounded-[40px]  ",
        renderTotal(totalScore),
        renderSummary(results),
      ),
      renderAttribution()
    )
  }

  def renderTotal(totalScore: Int) = {
    div(
      className := "flex flex-col items-center w-full rounded-b-[30px] ",
      className := "lg:rounded-[30px] lg:h-full lg:w-1/2 lg:justify-center ",
      className := "bg-gradient-to-b from-[#7643FF] to-[#2E2CE9]",
      p(
        "Your Result",
        className := "text-[#D4CAFF] text-lg font-semibold pt-6 pb-4 ",
        className := "lg:text-2xl lg:pb-6 "
      ),
      div(
        className := "rounded-full bg-blue-900 h-36 w-36 flex flex-col justify-center items-center py-10",
        className := "lg:h-[200px] lg:w-[200px] lg:py-14",
        className := "bg-gradient-to-b from-[#4A23CC] to-[#4734f0]",
        p(
          s"$totalScore",
          className := "text-6xl font-extrabold text-white ",
          className := "lg:text-7xl lg:pb-3 "
        ),
        p(
          "of 100",
          className := "text-base font-bold text-[#8a80ff] block px-10",
          className := "lg:font-bold"
        )
      ),
      div(
        className := "flex flex-col items-center",
        p(
          "Great",
          className := "text-white text-2xl font-bold pt-5 pb-2",
          className := "lg:text-3xl lg:p-4"
        ),
        p(
          "You scored higher than 65% of the people who have taken these tests.",
          className := "text-[#B4B6FF] text-base font-semibold text-center pb-8 px-16 ",
          className := "lg:px-14 lg:text-lg lg:font-normal"
        )
      )
    )
  }

  def renderSummary(results: List[CategoryResult]) = {
    div(
      className := "flex flex-col w-full px-7 ",
      className := "lg:w-1/2 lg:h-full lg:rounded-r-[40px] lg:relative lg:px-10 ",
      p("Summary", className := "font-bold text-xl py-6 lg:pt-10 lg:text-2xl"),
      results.map(renderCategoryScore(_)),
      div(
        className := "py-4 w-full h-full",
        className := "lg:pt-8",
        button(
          "Continue",
          className := "text-white text-xl font-bold bg-[#303B59] h-16 w-full rounded-full",
          className := "lg:h-12",
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
          .amend(svg.className := "justify-self-center w-6 h-6 lg:w-5 lg:h-5")
      )
      .startWith(loadingDiv)

    div(
      className := " py-2 w-full", // TODO for some reason no white space between categories without this
      div(
        className := "grid grid-cols-7 w-full h-16 rounded-xl items-center ", // TODO for some reason justify-items-center doesn't work
        styleAttr := s"--custom-bg: ${result.bgColor}; --custom-highlight: ${result.highlightColor}",
        className := "bg-[--custom-bg]",
        className := "lg:rounded-lg lg:h-14",
        child <-- svgIcon,
        p(
          className := "col-span-4 text-lg text-[--custom-highlight] font-bold lg:text-base lg:text-bold",
          result.name
        ),
        div(
          className := "col-span-2 justify-self-center text-lg flex flex-row lg:pr-4 lg:text-base",
          p(s"${result.score}", className := "font-semibold pr-1 "),
          p(" / 100", className := "text-gray-400 font-semibold")
        )
      )
    )
  }

  def renderAttribution() = {
    div(
      className := "attribution flex flex-row pt-1",
      "Challenge by",
      a(
        href := "https://www.frontendmentor.io?ref=challenge",
        target := "_blank",
        "Frontend Mentor"
      ),
      "Coded by",
      a(href := "#", "Your Name Here")
    )

  }
}
