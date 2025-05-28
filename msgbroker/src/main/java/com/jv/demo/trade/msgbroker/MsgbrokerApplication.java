package com.jv.demo.trade.msgbroker;

import com.jv.demo.trade.msgbroker.producer.CreateMsgFromTrader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class MsgbrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgbrokerApplication.class, args);
	}

}
