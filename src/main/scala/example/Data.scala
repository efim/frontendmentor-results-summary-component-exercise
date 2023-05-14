package example

object Data {
  final case class CategoryResult(
      name: String,
      score: Int,
      iconPath: String,
      backgroundColorClass: String
  )

  val hardcoded = List(
    CategoryResult("Reaction", 80, "./assets/images/icon-reaction.svg", "bg-red-100"),
    CategoryResult("Memory", 92, "./assets/images/icon-memory.svg", "bg-yellow-100"),
    CategoryResult("Verbal", 61, "./assets/images/icon-verbal.svg", "bg-emerald-100"),
    CategoryResult("Visual", 72, "./assets/images/icon-visual.svg", "bg-violet-100")
  )
}
