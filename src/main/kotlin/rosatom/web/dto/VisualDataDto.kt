package rosatom.web.dto

import rosatom.Job

data class VisualDataDto(
    val nodes: List<NodeDto>,
    val links: List<LinkDto>
) {
    constructor(jobs: Map<Int, Job>) : this(
        jobs.values.map { NodeDto(it.name, it.rating) },
        jobs.values.flatMap { job ->
            job.dependentJobs.map { it.id }.intersect(jobs.keys)
                .map { jobId -> LinkDto(job.name, jobs.getValue(jobId).name, 1) }
        }
    )
}

data class NodeDto(
    val id: String,
    val group: Double
)

data class LinkDto(
    val source: String,
    var target: String,
    val value: Int
)