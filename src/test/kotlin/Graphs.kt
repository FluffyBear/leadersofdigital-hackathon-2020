import rosatom.FunctionType
import rosatom.Graph
import rosatom.Job

object Graphs {
    val simpleGraph = Graph(listOf(
        Job(1, "1", 1, 2, listOf()),
        Job(2,"2", 1,2, listOf()),
        Job(3,"3", 1,1, listOf()),
        Job(4,"4", 1,2, listOf()),
        Job(5,"5", 3,2, listOf(1,2)),
        Job(6,"6", 3,3, listOf(3,4)),
        Job(7,"7", 6, 1, listOf(5,6))
    ))

}