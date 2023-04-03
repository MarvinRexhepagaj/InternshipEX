package com.session_1;

import com.session_1.model.Answer;
import com.session_1.model.Question;
import com.session_1.model.Survey;
import com.session_1.service.SurveyService;
import com.session_1.service.inpl.SurveyServiceInpl;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        final SurveyService surveyService = new SurveyServiceInpl();

        Survey survey = null;
        boolean done = false;
        while (!done) {
            System.out.println("\nSURVEY MANAGER MENU");
            System.out.println("1. Create a new survey");
            System.out.println("2. Edit an existing survey");
            System.out.println("3. Take a survey");
            System.out.println("4. Print survey");
            System.out.println("0. Exit the program");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    exitProgram();
                    break;
                case 1:
                    survey = surveyService.createNewSurvey();
                    break;
                case 2:
                    surveyService.editSurvey();
                    break;
                case 3:
                    surveyService.takeSurvey(survey);
                    break;
                case 4:
                    surveyService.printSurveyResults();

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        if (surveyService.validateSurvey(survey)) {
            System.out.println("Survey is valid");
        } else {
            System.out.println("Survey is not valid");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program. Goodbye!");
        System.exit(0);
    }
}