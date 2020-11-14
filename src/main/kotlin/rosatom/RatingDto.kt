package rosatom

import rosatom.web.dto.VisualDataDto

data class RatingDto(
    val rating: Double,
    val criticalJobs: VisualDataDto
) {
    val issuesFree = criticalJobs.nodes.isEmpty()
}