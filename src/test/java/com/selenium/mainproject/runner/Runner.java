package com.selenium.mainproject.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="featureFile/userStory.feature",glue="stepDefinitions",dryRun = false,monochrome=true,format={"pretty","html:testoutput","junit:report_xml/cucumber.xml"})

public class Runner {

}
