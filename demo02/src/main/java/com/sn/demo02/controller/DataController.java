package com.sn.demo02.controller;

import com.sn.demo02.domain.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/data")
public class DataController {
    @RequestMapping("/test")
    public Result test(){
        System.out.println("you get server");
        return Result.ok("you get server","this is the data you need");
    }

    @RequestMapping("/radarData")
    public Result test2(){
        List<int[][]> arrays = new ArrayList<>(3);
        int[][] dataBJ = new int[30][7];
        int[][] dataGZ = new int[30][7];
        int[][] dataSH = new int[30][7];

        int a,b,c;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 7; j++) {
                a = (int)(Math.random()*100);
                b = (int)(Math.random()*100);
                c = (int)(Math.random()*100);
                dataBJ[i][j] = a;
                dataSH[i][j] = b;
                dataGZ[i][j] = c;
            }
        }
        arrays.add(dataBJ);
        arrays.add(dataGZ);
        arrays.add(dataSH);
        return Result.ok("you get server",arrays);
    }
}
