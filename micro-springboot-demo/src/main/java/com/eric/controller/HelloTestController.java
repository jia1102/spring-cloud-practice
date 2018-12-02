package com.eric.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class HelloTestController {

    @ResponseBody
    @RequestMapping("/helloWorld")
    public String hello() {
       System.out.println("helloWorld");
       return "helloWorld";
    }


    public static void main(String[] args) {

        ArrayList<JSONObject> listJson = new ArrayList();

//        JSONArray listJson = new JSONArray();
        JSONObject jOb3 = new JSONObject();
        jOb3.put("kpyf", "201807");
        JSONObject jOb4 = new JSONObject();
        jOb4.put("kpyf", "201809");
        JSONObject jOb = new JSONObject();
        jOb.put("kpyf", "201804");
        JSONObject jOb1 = new JSONObject();
        jOb1.put("kpyf", "201805");
        JSONObject jOb2 = new JSONObject();
        jOb2.put("kpyf", "201806");
        listJson.add(jOb);
        listJson.add(jOb1);
        listJson.add(jOb2);
        listJson.add(jOb3);
        listJson.add(jOb4);

        listJson.sort((JSONObject h1, JSONObject h2) -> h2.get("kpyf").toString().compareTo(h1.get("kpyf").toString()));


        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(listJson));
        System.out.println(jsonArray);
    }
}
