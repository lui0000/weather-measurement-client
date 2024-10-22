package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.MeasurementDTO;
import org.example.dto.SensorDTO;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
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

//                getResponse.
//
//                double[] xData = new double[] { 0.0, 1.0, 2.0 };
//                double[] yData = new double[] { 2.0, 1.0, 0.0 };
//
//                // Create Chart
//                XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
//
//                // Show it
//                new SwingWrapper(chart).displayChart();


                break;
            case 2:

                for(int i = 0; i < 101; i++) {
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


//                    double[] xData = new double[] { 0.0, 1.0, 2.0 };
//                    double[] yData = new double[] { 2.0, 1.0, 0.0 };
//
//                    // Create Chart
//                    XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", xData, yData);
//
//                    // Show it
//                    new SwingWrapper(chart).displayChart();

                }

                break;
            default:
                System.out.println("Invalid fill mode");
        }


    }




}