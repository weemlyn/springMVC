package com.xwm.dao;

import com.xwm.pojo.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CarMapper {


    List<Car> queryCar(@Param("name")String name, @Param("beginDate")Date beginDate, @Param("endDate")Date endDate);

    @Insert("insert into car(name,price,sale_date) values (#{name},#{price},#{salDate})")
    int addCar(Car car);

    @Delete("delete from car where id=#{id}")
    int delCar(int id);

    @Update("update car set name=#{name},price=#{price},sale_date=#{salDate} where id=#{id}")
    int updateCar(Car car);

    @Select("select id,name,price,sale_date salDate from car where id=#{id}")
    Car queryByid(int id);
}
