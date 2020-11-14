package rosatom

import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.VisualDataDto

data class Project(
    val jobs: List<Job>,
    val dependencies: List<Pair<Int, Int>>
) {
    constructor(request: AnalyseRequestDto) : this(
        request.jobs.map { it.toJob() }, request.dependencies.map { it.from to it.to }
    )

    private val jobsMap = jobs.associateBy { it.id }

    init {
        dependencies.forEach { (from, to) ->
            jobsMap.getValue(from).dependentJobs.add(jobsMap.getValue(to))
            jobsMap.getValue(to).dependsOnJobs.add(jobsMap.getValue(from))
        }
    }

    fun rating(): RatingDto = RatingDto(
        rating = jobs.map { it.rating }.average(),
        criticalJobs = VisualDataDto(jobs.filter { it.gapAfter < 0 || it.gapBefore < 0 }.associateBy { it.id })
    )
}