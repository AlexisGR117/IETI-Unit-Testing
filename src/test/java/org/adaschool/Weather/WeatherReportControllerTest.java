package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class WeatherReportControllerTest {
    @InjectMocks
    private WeatherReportController weatherReportController;

    @Mock
    private WeatherReportService weatherReportService;

    @Test
    void testControllerGetWeatherReport() {
        WeatherReportController weatherReportController = new WeatherReportController(weatherReportService);
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setTemperature(0.0);
        weatherReport.setHumidity(69.0);
        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(weatherReport);
        assertEquals(weatherReport, weatherReportController.getWeatherReport(37.8267, -122.4233));
        verify(weatherReportService).getWeatherReport(37.8267, -122.4233);
    }
}
