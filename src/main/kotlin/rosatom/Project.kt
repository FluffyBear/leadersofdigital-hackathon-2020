package rosatom

import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.SimplifiedJobDto
import rosatom.web.dto.VisualDataDto

data class Project(
    val jobs: MutableMap<Int, Job>,
    val dependencies: List<Pair<Int, Int>>
) {
    constructor(request: AnalyseRequestDto) : this(
        request.jobs.associate { it.id to it.toJob() }.toMutableMap(), request.dependencies.map { it.from to it.to }
    )

    init {
        dependencies.forEach { (from, to) ->
            jobs.getValue(from).dependentJobs.add(jobs.getValue(to))
            jobs.getValue(to).dependsOnJobs.add(jobs.getValue(from))
        }
    }

    fun rating(): RatingDto = RatingDto(
        rating = jobs.values.map { it.rating }.average(),
        criticalJobs = VisualDataDto(jobs.values.filter { it.gapAfter < 0 || it.gapBefore < 0 }.associateBy { it.id })
    )

    fun adjust(adjustedJobs: List<Job>) {
        adjustJobs(
            // adjusted Jobs and connected jobs have to be refreshed
            adjustedJobs.plus(adjustedJobs.flatMap { it.dependentJobs.plus(it.dependsOnJobs) })
        )
    }

    private fun adjustJobs(adjustedJobs: List<Job>) {
        adjustedJobs.forEach {adjustedJob ->
            // replace old Job with new One
            val oldJob = jobs.getValue(adjustedJob.id)
            val newJob = oldJob.copy(
                startDate = adjustedJob.startDate,
                normalDuration = adjustedJob.normalDuration
            )
            oldJob.dependentJobs.forEach { dependentJob ->
                dependentJob.dependsOnJobs.remove(oldJob)
                dependentJob.dependsOnJobs.add(newJob)
            }
            oldJob.dependsOnJobs.forEach { dependsOnJob ->
                dependsOnJob.dependentJobs.remove(oldJob)
                dependsOnJob.dependentJobs.add(newJob)
            }
            jobs.replace(oldJob.id, newJob)
        }
    }
}