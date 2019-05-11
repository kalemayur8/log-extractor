package com.prodlogextract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.prodlogextract.util.ExtractLogUtil;

@SpringBootApplication
public class ProdLogExtractApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdLogExtractApplication.class, args);
		ExtractLogUtil.extractLogs(args[0],args[1],args[2],args[3],args[4]);
		//ExtractLogUtil.extractLogs("acp","2018-08-10 15:30","2018-08-10 17:15","local","stag");
	}
}
