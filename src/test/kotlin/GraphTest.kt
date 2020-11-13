import org.junit.Test
import rosatom.Project

class GraphTest {


    @Test
    fun fillGraph() {
        val testGraph = Graphs.simpleGraph
        val result = Project().fillGraph(testGraph)
        assert(result).equals(true)
    }
}