package com.petr.spring.task.measure_bean.service;

import com.petr.spring.task.measure_bean.bean.MeasureTime;
import org.springframework.stereotype.Service;

@Service
public class TimedService {
    @MeasureTime
    public void methodWith1SecondDelay() throws InterruptedException {
        System.out.println("Executing methodWith1SecondDelay");
        Thread.sleep(1000); // задержка 1 секунда
    }

    @MeasureTime
    public void methodWith3SecondsDelay() throws InterruptedException {
        System.out.println("Executing methodWith3SecondsDelay");
        Thread.sleep(3000); //  задержка 3 секунды
    }

    @MeasureTime
    public void methodWithoutDelay() {
        System.out.println("Executing methodWithoutDelay");
    }
}
