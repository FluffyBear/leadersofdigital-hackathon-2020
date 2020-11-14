package rosatom.web

import com.google.common.io.Resources
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import rosatom.Project
import rosatom.RatingDto
import rosatom.web.dto.*

@RestController
@EnableAutoConfiguration
@RequestMapping
class Controller @Autowired constructor() {
    companion object : KLogging()

    @PostMapping(path = ["/analyse"])
    fun analyse(@RequestBody request: AnalyseRequestDto): RatingDto {
        return Project(request).rating()
    }

    @PostMapping(path = ["/analyse-change"])
    fun analyseChange(@RequestBody request: CompareRequestDto): Pair<RatingDto, RatingDto> {
        val project = Project(request.originalProject)
        val originalRating = project.rating()
        project.adjust(request.adjustedJobs.map { it.toJob() })
        val adjustedRating = project.rating()
        return Pair(originalRating, adjustedRating)
    }

    @PostMapping(path = ["/improve"])
    fun improve(@RequestBody request: AnalyseRequestDto): ImproveResponseDto? {
        return null
    }

    @GetMapping(path = ["/render"])
    fun render(@RequestParam num: String): VisualDataDto {
        val request = AnalyseRequestDto.parse(Resources.getResource("analyse-test/$num.json").readText())
        return VisualDataDto(Project(request).jobs)
    }

    @GetMapping(path = ["/render-analyse"])
    fun renderAnalyse(@RequestParam num: String): VisualDataDto {
        val request = AnalyseRequestDto.parse(Resources.getResource("analyse-test/$num.json").readText())
        return Project(request).rating().criticalJobs
    }
}