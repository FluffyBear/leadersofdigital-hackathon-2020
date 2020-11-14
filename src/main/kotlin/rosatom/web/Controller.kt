package rosatom.web

import com.google.common.io.Resources
import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.*
import rosatom.Project
import rosatom.RatingDto
import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.VisualDataDto

@RestController
@EnableAutoConfiguration
@RequestMapping
class Controller @Autowired constructor() {
    companion object : KLogging()

    @PostMapping(path = ["/analyse"])
    fun analyse(@RequestBody request: AnalyseRequestDto): RatingDto {
        return Project(request).rating()
    }

    @GetMapping(path = ["/render"])
    fun render(@RequestParam num: String): VisualDataDto {
        val request = AnalyseRequestDto.parse(Resources.getResource("test/$num.json").readText())
        return VisualDataDto(Project(request).jobs.associateBy { it.id })
    }

    @GetMapping(path = ["/render-analyse"])
    fun renderAnalyse(@RequestParam num: String): VisualDataDto {
        val request = AnalyseRequestDto.parse(Resources.getResource("test/$num.json").readText())
        return Project(request).rating().criticalJobs
    }
}