package de.deelthor.isintoticker.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping(path = ["ticker"])
class TickerController(private val restTemplate: RestTemplate = RestTemplate()) {

    @GetMapping
    fun ticker(@RequestParam isin : String): String {
        val request = listOf(FigiMapping("ID_ISIN", isin, "US"))
        val response = restTemplate.postForEntity("https://api.openfigi.com/v3/mapping",
                request, Array<FigiResponse>::class.java)
        val figiResponse = response.body?.get(0)!!
        return figiResponse.data[0].ticker
    }
}