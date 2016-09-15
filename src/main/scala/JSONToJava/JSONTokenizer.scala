object JSONTokenizer{

  def hello = println("hello")

  def tokenize(json: String):List[String] = {
    def tokenizeRec(remaining: List[String], acc: List[String], acc2: String): List[String] ={
      if(remaining.isEmpty){
        if(acc2.isEmpty) acc
        else acc2::acc
      }
      else{
        def h = remaining.head
        def t = remaining.tail
        def tokenRecurse = if(acc2.isEmpty) tokenizeRec(t, h::acc, "") else tokenizeRec(t, h::acc2::acc, "")
        def spaceRecurse = if(acc2.isEmpty) tokenizeRec(t, acc, "") else tokenizeRec(t, acc2::acc, "")
        h match{
          case "{" | "}" | "[" | "]" | ":" | "," => tokenRecurse
          case " "  => spaceRecurse
          case  x  => tokenizeRec(t, acc, acc2 + x)
        }
      }
    }
    tokenizeRec(json.split("").toList, List(), "").reverse
  }


}
