package com.xwm.controller;

import com.xwm.pojo.Car;
import com.xwm.service.CarService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController//将返回的对象变更成json对象
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)//跨端口跨域注解
public class CarController {

    @Autowired
    private CarService carService;
    @RequestMapping(value = "car",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Car>> query(
            @RequestParam(value = "name")String name,
            @RequestParam(value = "beginDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date beginDate,
            @RequestParam(value = "endDate")@DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate
    ){
        List<Car> carList=carService.queryAll(name, beginDate, endDate);

        if (carList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(carList,HttpStatus.OK);
    }
    @RequestMapping(value ="car",method=RequestMethod.POST )
    public ResponseEntity<?> addCar( @RequestBody Car car){/*@RequestParam(name = "name",required = true)String name,
                                    @RequestParam(name = "price",required = true)String price,
                                    @RequestParam(name = "saleDate",required = true)
                                   String saleDate*/
     /*Car car=new Car();
        car.setName(name);
        car.setPrice(Double.parseDouble(price));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            car.setSalDate(sdf.parse(saleDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/
        int count=carService.addCar(car);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @RequestMapping(value = "car/{id}",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Car> queryById(@PathVariable(value = "id")int id){
        Car car=carService.queryById(id);
        return new ResponseEntity<>(car,HttpStatus.OK);
    }

    @RequestMapping(value = "car/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> delCar(@PathVariable(value = "id")int id){
        int count=carService.delCar(id);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @RequestMapping(value = "car",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updCar(@RequestBody Car car){
      int count=carService.udpCar(car);
      return new ResponseEntity<>(count,HttpStatus.OK);
    }

}
