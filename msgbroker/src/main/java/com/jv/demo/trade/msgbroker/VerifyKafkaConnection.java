package com.jv.demo.trade.msgbroker;

import com.jv.demo.trade.msgbroker.producer.CreateMsgFromTrader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class VerifyKafkaConnection implements CommandLineRunner {

    //@Autowired
    //private CreateMsgFromTrader createMsgFromTrader;

    public static void main(String[] args) {
        System.out.println("STARTING THE APPLICATION");
        //SpringApplication.run(VerifyKafkaConnection.class, args);
        System.out.println("APPLICATION FINISHED");
    }

    //@Override
    public void run(String... args) {
        System.out.println("EXECUTING : command line runner");
        //createMsgFromTrader.sendMessage("topicMsgFromTrader", "Hello World!!!");
    }
}
