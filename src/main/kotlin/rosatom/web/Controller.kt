package rosatom.web

import mu.KLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import rosatom.web.dto.AnalyseRequestDto
import rosatom.web.dto.AnalyseResponseDto

@RestController
@EnableAutoConfiguration
@RequestMapping
class Controller @Autowired constructor() {
    companion object : KLogging()

    @GetMapping("/analyse")
    fun render(request: AnalyseRequestDto): AnalyseResponseDto {
        return AnalyseResponseDto(0.0, emptyMap())
    }
}