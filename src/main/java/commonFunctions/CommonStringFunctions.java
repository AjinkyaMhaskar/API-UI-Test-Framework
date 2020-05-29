package main.java.commonFunctions;

public class CommonStringFunctions {

	// function to remove special character from a string
	// https://stackoverflow.com/questions/7552253/how-to-remove-special-characters-from-a-string

	public static String removeSpecialChar(String Input) {
		return (Input.replaceAll("[^A-Za-z0-9]", ""));
	}
}
