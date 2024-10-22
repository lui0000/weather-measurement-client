package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.dto.MeasurementDTO;
import org.example.dto.SensorDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter 1 for manual entry and 2 for automatic entry");
        int choice = scanner.nextInt();

        Random random = new Random();

        RestTemplate restTemplate = new RestTemplate();
        MeasurementDTO measurementDTO = new MeasurementDTO();
        SensorDTO sensorDTO = new SensorDTO();

        HttpEntity<MeasurementDTO> postRequest = new HttpEntity<>(measurementDTO);
        String url = "http://localhost:8080/measurement";
        String addUrl = "http://localhost:8080/measurement/add";


        switch (choice) {
            case 1:
                sensorDTO.setName("sensor 1");
                measurementDTO.setRaining(false);
                measurementDTO.setTemperature(1);
                measurementDTO.setSensor(sensorDTO);


                String getResponse = restTemplate.getForObject(url, String.class);
                String postResponse = restTemplate.postForObject(addUrl, postRequest, String.class);
                System.out.println(getResponse);
                System.out.println(postResponse);

                doChangeGraph(getResponse);

                break;
            case 2:

                for (int i = 0; i < 101; i++) {
                    int randomNumber = random.nextInt(101) - 100;
                    int randomBoolean = random.nextInt(2);
                    if (randomBoolean == 0) {
                        measurementDTO.setRaining(false);
                        sensorDTO.setName("sensor 1");
                        measurementDTO.setSensor(sensorDTO);
                    } else {
                        measurementDTO.setRaining(true);
                        measurementDTO.setTemperature(randomNumber);
                        sensorDTO.setName("sensor 2");
                        measurementDTO.setSensor(sensorDTO);
                    }

                    String getResponse1 = restTemplate.getForObject(url, String.class);
                    String postResponse1 = restTemplate.postForObject(addUrl, postRequest, String.class);
                    System.out.println(getResponse1);
                    System.out.println(postResponse1);

                    doChangeGraph(getResponse1);

                }

                break;
            default:
                System.out.println("Invalid fill mode");
        }


    }

    public static void doChangeGraph(String jsonResponse) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());

            List<MeasurementDTO> weatherDataList = objectMapper.readValue(jsonResponse, new TypeReference<>() {});
            List<Double> temperature = new ArrayList<>();
            List<Long> timeData = new ArrayList<>();

            for (MeasurementDTO data : weatherDataList) {
                temperature.add(data.getTemperature());
                long millis = Timestamp.valueOf(data.getCreatedAt()).getTime();
                timeData.add(millis);
            }

            // Преобразуем данные в массивы для xChart
            double[] xData = timeData.stream().mapToDouble(Long::doubleValue).toArray();
            double[] yData = temperature.stream().mapToDouble(Double::doubleValue).toArray();

            XYChart chart = QuickChart.getChart("Weather Data", "Time", "Temperature", "Temperature", xData, yData);

            new SwingWrapper(chart).displayChart();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}