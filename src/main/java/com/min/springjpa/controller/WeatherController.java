package com.min.springjpa.controller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;

import com.min.springjpa.domain.WeatherDTO;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Controller
public class WeatherController {  

  @GetMapping(value="/weather", produces="application/json")
  public ResponseEntity<WeatherDTO> diaplayWeather() {
    
    WeatherDTO weatherDTO = new WeatherDTO();
    
    try {
      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4161060000");
      String wname = document.getElementsByTagName("wfKor").item(0).getTextContent();      
      String time = document.getElementsByTagName("wfKor").item(0).getTextContent();
      weatherDTO.setTime(time);
      switch(wname) {
      case "구름많음": case "흐림":
        weatherDTO.setImg("images/cloud.png");
        break;
      case "구름조금":
        weatherDTO.setImg("images/cloud_sun.png");
        break;
      case "맑음":
        weatherDTO.setImg("images/sun.png");
        break;
      case "비": case "흐리고 비":
        weatherDTO.setImg("images/rain.png");
        break;
      case "눈":
        weatherDTO.setImg("images/snow.png");
        break;
      default:
        weatherDTO.setImg("images/etc.png");      
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    return ResponseEntity.ok(weatherDTO);
    
  }
  
}