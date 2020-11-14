package rosatom.web.dto

import rosatom.RatingDto

data class ImproveResponseDto(
    val rating: RatingDto,
    val jobToChange: SimplifiedJobDto
)