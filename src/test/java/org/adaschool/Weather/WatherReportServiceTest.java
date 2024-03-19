package org.adaschool.Weather;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WatherReportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @Test
    void testGetWeatherReport() {
        weatherReportService.setRestTemplate(restTemplate);
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(0.0);
        main.setHumidity(69.0);
        WeatherApiResponse weatherApiResponse = new WeatherApiResponse();
        weatherApiResponse.setMain(main);
        when(restTemplate.getForObject(anyString(), eq(WeatherApiResponse.class))).thenReturn(weatherApiResponse);
        WeatherReport expectedReport = new WeatherReport();
        expectedReport.setTemperature(0.0);
        expectedReport.setHumidity(69.0);
        WeatherReport report = weatherReportService.getWeatherReport(37.8267, -122.4233);
        assertEquals(expectedReport.getHumidity(), report.getHumidity());
        assertEquals(expectedReport.getTemperature(), report.getTemperature());
    }
}
