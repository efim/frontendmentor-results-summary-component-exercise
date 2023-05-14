package example

object Data {
  final case class CategoryResult(
      name: String,
      score: Int,
      iconPath: String
  )

  val hardcoded = List(
    CategoryResult("Reaction", 80, "./assets/images/icon-reaction.svg"),
    CategoryResult("Memory", 92, "./assets/images/icon-memory.svg"),
    CategoryResult("Verbal", 61, "./assets/images/icon-verbal.svg"),
    CategoryResult("Visual", 72, "./assets/images/icon-visual.svg")
  )
}
