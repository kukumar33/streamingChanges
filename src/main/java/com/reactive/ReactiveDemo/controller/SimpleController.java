package com.reactive.ReactiveDemo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController

@CrossOrigin
public class SimpleController {

    static Map<String,Integer> cachedData=new HashMap<>();


    @GetMapping(value = "/getStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, ResultObject>> getStreamOfData()
    {



        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> generateMap());


    }

   public Map<String, ResultObject> generateMap()
    {
        Map<String,ResultObject> mapData1=new HashMap<>();

        ResultObject loanResult=new ResultObject();
        loanResult.setCompleted(100);
        loanResult.setFailed(10);
        loanResult.setInProgress(20);


        mapData1.put("Loan", loanResult);

        ResultObject commitmentResult=new ResultObject();
        commitmentResult.setCompleted(100);
        commitmentResult.setFailed(10);
        commitmentResult.setInProgress(8);
        mapData1.put("Commitment",commitmentResult);


        ResultObject securityResult=new ResultObject();
        securityResult.setCompleted(100);
        securityResult.setFailed(10);
        securityResult.setInProgress(3);

        mapData1.put("Securities",securityResult);


        List<String> list= List.of("Loan","Commitment","Securities");

        Random random=new Random();
        int random1=   random.nextInt(3);

      ResultObject assetToModify=  mapData1.get(list.get(random1));
        assetToModify.setInProgress(random.nextInt(100));
        assetToModify.setCompleted(random.nextInt(200));
        assetToModify.setInProgress(random.nextInt(500));

      mapData1.put(list.get(random1),assetToModify);

      String changedValue=list.get(random1)+"Changed";

      mapData1.put(changedValue,null);











        return  mapData1;

    }

    private String generateData() {




        List<String> list= List.of("Loan","Commitment","Securities");

        Random random=new Random();
     int random1=   random.nextInt(3);



return  list.get(random1);

    }
}
