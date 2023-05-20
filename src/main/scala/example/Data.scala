package example

object Data {
  final case class CategoryResult(
      name: String,
      score: Int,
      iconPath: String,
      backgroundColorClass: String,
      // highlightColorClass: String
  )


// #FFF6F5
// #DD767D
// #FFFBF2
// #E3B44A
// #F2FBFA
// #29A784
// #F3F3FD
// #0E1987

  val hardcoded = List(
    CategoryResult("Reaction", 80, "./assets/images/icon-reaction.svg", "bg-[#FFF6F5]"),
    CategoryResult("Memory", 92, "./assets/images/icon-memory.svg", "bg-[#FFFBF2]"),
    CategoryResult("Verbal", 61, "./assets/images/icon-verbal.svg", "bg-[#F2FBFA]"),
    CategoryResult("Visual", 72, "./assets/images/icon-visual.svg", "bg-[#F3F3FD]")
  )
}
