package rosatom

class Project {
    fun fillGraph(graph: Graph) {
        val finishDates = finishDates(graph)
        graph.jobs.forEach{
            it
        }
    }

    private fun finishDates(graph: Graph): Map<Job, Int> {
        val finishDates = mutableMapOf<Job, Int>()
        graph.jobs.forEach {
            finishDates[it] = it.startDate + it.normalDuration
        }
        return finishDates
    }
}