package com.session_1;

import com.session_1.model.Question;
import com.session_1.model.Survey;
import com.session_1.service.SurveyService;
import com.session_1.service.inpl.SurveyServiceInpl;

import java.util.List;
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
            System.out.println("3. Delete a survey");
            System.out.println("4. Add a question to a survey");
            System.out.println("5. Remove a question from a survey");
            System.out.println("6. Add a candidate to a survey");
            System.out.println("7. Remove a candidate from a survey");
            System.out.println("8. Find the most given answer for a survey");
            System.out.println("9. Print survey results");
            System.out.println("10. Find the candidate who has taken the most surveys");
            System.out.println("0. Exit the program");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0:
                    exitProgram();
                    break;
                case 1:
                    surveyService.createNewSurvey();
                    break;
                case 2:
                    //editSurvey(survey);
                    break;


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
