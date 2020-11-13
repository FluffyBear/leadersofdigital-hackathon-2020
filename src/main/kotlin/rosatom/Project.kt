package rosatom

class Project {
    fun rating(graph: Graph) : Map<Int, Rating> {
        val finishJobDates = finishJobDates(graph)
        val rating = mutableMapOf<Int, Rating>()
        graph.jobs.forEach{
            val dependentJobReadyDate = readyToStartDate(it, finishJobDates)
            if (dependentJobReadyDate != null)
                rating[it.id] = Rating(
                    earlyGap = it.startDate - dependentJobReadyDate)
        }
        return rating
    }

    fun fillGraph(graph: Graph) : Boolean {
        val finishJobDates = finishJobDates(graph)
        graph.jobs.forEach{ job ->
            val dependentJobFinishDates = mutableListOf<Int>()
            job.dependentJobs.forEach{
                dependentJobFinishDates.add(finishJobDates[it] as Int)
            }
            if (!isJobStartDateOk(job.startDate, dependentJobFinishDates)) return false
        }
        return true
    }

    private fun isJobStartDateOk(jobStartDate: Int, dependentJobFinishDates: List<Int>): Boolean {
        dependentJobFinishDates.forEach{
            if (it > jobStartDate) return false
        }
        return true
    }

    private fun finishJobDates(graph: Graph): Map<Int, Int> {
        val finishDates = mutableMapOf<Int, Int>()
        graph.jobs.forEach {
            finishDates[it.id] = it.startDate + it.normalDuration
        }
        return finishDates
    }

    private fun readyToStartDate(job: Job, finishJobDates: Map<Int, Int>): Int? {
        val dependentJobsReadyDates = mutableListOf<Int>()
        job.dependentJobs.forEach{
            dependentJobsReadyDates.add(finishJobDates[it] as Int)
        }
        return dependentJobsReadyDates.maxOrNull()
    }

}