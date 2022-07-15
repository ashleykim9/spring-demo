package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import com.launchdarkly.sdk.server.*;
import com.launchdarkly.sdk.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) throws IOException{
		SpringApplication.run(DemoApplication.class, args);
		 // Create a new LDClient with your environment-specific SDK key
		 LDClient ldClient = new LDClient("YOUR_SDK_KEY");

		 // Set up the user properties. This user should appear on your
		 // LaunchDarkly users dashboard soon after you run the demo.
		 LDUser user = new LDUser.Builder("UNIQUE IDENTIFIER")
								 .firstName("Bob")
								 .lastName("Loblaw")
								 .custom("groups", LDValue.buildArray().add("beta_testers").build())
								 .build();
	 
		 boolean showFeature = ldClient.boolVariation("YOUR_FLAG_KEY", user, false);

		 System.out.println("SDK successfully connected! The value of test-flag is " + showFeature + " for " + user);
	 
		 // Here we ensure that the SDK shuts down cleanly and has a chance to deliver analytics
		 // events to LaunchDarkly before the program exits. If analytics events are not delivered,
		 // the user properties and flag usage statistics will not appear on your dashboard. In a
		 // normal long-running application, the SDK would continue running and events would be
		 // delivered automatically in the background.
		 ldClient.close();
	}
}
