package com.xwm.service;


import com.xwm.dao.CarMapper;
import com.xwm.pojo.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true,rollbackFor = Exception.class)
public class CarService {
    @Autowired
    private CarMapper carMapper;

    /**
     * 查询所有汽车信息
     * @return
     */
    public List<Car> queryAll(String name, Date beginDate,Date endDate){
        return carMapper.queryCar(name,beginDate,endDate);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int addCar(Car car){
        return carMapper.addCar(car);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int udpCar(Car car){
        return carMapper.updateCar(car);
    }
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int delCar(int id){
        return  carMapper.delCar(id);
    }

 public Car queryById(int id){
        return  carMapper.queryByid(id);
    }

}
