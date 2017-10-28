package com.codemate.booklibrary;

import junit.framework.TestCase;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "app/src/test/resources")
public class CucumberTests extends TestCase {
}