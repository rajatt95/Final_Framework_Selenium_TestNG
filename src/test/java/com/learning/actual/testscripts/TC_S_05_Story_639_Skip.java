package com.learning.actual.testscripts;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.learning.utilities.Custom_Utilties;

public class TC_S_05_Story_639_Skip extends Custom_Utilties {

	@Test(groups = { "Smoke", "Regression", "Sanity","BVT" })
	public void S_05_Story_639() {

		throw new SkipException("Skipping the Test case");

	}
}