package com.reactive.ReactiveDemo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Locale;

public class ReativeDemo {


    public Flux<String> getAllFlux()
    {
        Flux<String> fluxReturn = Flux.fromIterable(List.of("A", "B"));

        return fluxReturn;

    }



    public Flux<String> getAllFluxLowerCase()
    {
        Flux<String> fluxReturn = Flux.fromIterable(List.of("A", "B"));
      fluxReturn=  fluxReturn.map((each)-> each.toLowerCase(Locale.ROOT));

        return fluxReturn;

    }

    public Mono<String> getMonoData()
    {
        return  Mono.just("Hi Mono");
    }

    public static void main(String[] args) {
       ReativeDemo reativeDemo=new ReativeDemo();
       reativeDemo.getAllFluxLowerCase().subscribe((name)-> System.out.println("name"+name) );

       reativeDemo.getMonoData().subscribe((monoName)-> System.out.println("each Mono"+monoName));
    }
}
