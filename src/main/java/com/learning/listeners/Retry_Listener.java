package com.learning.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class Retry_Listener implements IAnnotationTransformer {

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setRetryAnalyzer(Retry.class);
	}
	/*
	 * public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2,
	 * Method arg3) {
	 * 
	 * IRetryAnalyzer analyzer = arg0.getRetryAnalyzer(); if (analyzer == null) {
	 * arg0.setRetryAnalyzer(Retry.class); }
	 * 
	 * }
	 */
}
