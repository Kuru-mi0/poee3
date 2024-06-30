import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Objects;

public class Main {

    // Make a generic string arraylist
    static ArrayList<String> devNames = new ArrayList<>();
    static ArrayList<String> taskNames = new ArrayList<>();
    static ArrayList<String> taskIDs = new ArrayList<>();
    static ArrayList<String> taskDurations = new ArrayList<>();
    static ArrayList<String> taskStatuses = new ArrayList<>();

    static int taskNumber = 0;

    public static void main(String[] args) {

        String yourName = JOptionPane.showInputDialog("Enter your name"); //input name
        String surname = JOptionPane.showInputDialog("Enter your surname"); //input surname

        boolean checkUserName = false;
        String username = "";

        while (!checkUserName) {

            username = JOptionPane.showInputDialog("Create your username" + "\n" + "Your username must contain 5 characters and a _");
            int usernameSize = username.length();

            boolean checkUserLength = usernameSize == 5; //checks username length
            boolean checkUserSpecialC = false;

            for (char UsernameC : username.toCharArray()) {
                if ("_".contains(String.valueOf(UsernameC))) { //makes sure it has an underscore
                    checkUserSpecialC = true;
                    break;
                }
            }
            checkUserName = checkUserLength && checkUserSpecialC;

            if (checkUserName) {
                String userMessage = "Username successfully captured";
                JOptionPane.showMessageDialog(null, userMessage); //if username is correct
            } else {
                String userMessageFail = "Username is ot correctly formatted, please ensure that your username contains an underscore and is no longer than 5 characters long.";
                JOptionPane.showMessageDialog(null, userMessageFail); //if username is incorrect
            }
        }
        String password = null;
        boolean checkPasswordComplexity = false;

        boolean checkLogin = false;
        while (!checkPasswordComplexity) {

            password = JOptionPane.showInputDialog("Create your password, must contain: " +
                    "8 characters, a capital letter, a number, a special character.");
            int passwordSize = password.length();

            boolean checkPasswordLength = passwordSize == 8; //checks password length
            boolean checkPasswordSpecial = false;
            boolean checkPasswordCapital = false;
            boolean checkPasswordNumber = false;

            for (char PasswordCharacters : password.toCharArray()) { //checks if password has all the inputs needed
                if ("*&^%$#@!<>".contains(String.valueOf(PasswordCharacters))) {
                    checkPasswordSpecial = true;
                } else if ("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(String.valueOf(PasswordCharacters))) {
                    checkPasswordCapital = true;
                } else if ("1234567890".contains(String.valueOf(PasswordCharacters))) {
                    checkPasswordNumber = true;
                }
            }

            checkPasswordComplexity = checkPasswordLength && checkPasswordSpecial && checkPasswordCapital && checkPasswordNumber;

            if (checkPasswordComplexity) {
                String registerUser = "Password successfully captured";

                JOptionPane.showMessageDialog(null, registerUser);
            } else {
                String registerUser = "Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";

                JOptionPane.showMessageDialog(null, registerUser);

            }

            while (!checkLogin) {

                String UsernameLogin = JOptionPane.showInputDialog("Please enter your username"); //username attempt
                String PasswordLogin = JOptionPane.showInputDialog("Please enter your password"); //password attempt

                boolean UsernameCorrect = Objects.equals(UsernameLogin, username); //checks if username is the same as the saved one
                boolean PasswordCorrect = Objects.equals(PasswordLogin, password); //checks if password is the same as the saved one

                checkLogin = UsernameCorrect && PasswordCorrect;

                if (checkLogin) {
                    String LoginSuccess = "Welcome" + " " + yourName + " " + surname + ", " + "Good to see you again!";
                    JOptionPane.showMessageDialog(null, LoginSuccess); //if login is successful
                } else {
                    String LoginFail = "Username or password is incorrect. Try again."; //if login is unsuccessful
                    JOptionPane.showMessageDialog(null, LoginFail);
                }
            }

        }

        // part 2

        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");

        // Allow the logged-in user to create as many tasks as they want
        // if user has logged in (checkLogin == true) then as how many tasks they must add
        while (checkLogin) {

            // Define an array of custom options for the dialog
            Object[] options = {"1 - Add Tasks", "2 - Show Report", "3 - Quit"};

            // Display an option dialog with custom options
            // The user's choice is stored in the 'choice'
            // variable
            int choice = getOptionInput("Select your option.", "Options", options);

            switch (choice) {
                case 0:

                    createTasks();

                    break;
                case 1:

                    getReporting();

                    break;
                case 2:
                case -1:
                    System.out.println("Goodbye");
                    checkLogin = false;
                    break;
                default:
                    System.out.println("Unknown choice: " + choice);
                    break;
            }

        }
    }

    public static void createTasks() {
        String numTaskMaking = JOptionPane.showInputDialog("Enter how many tasks you want to add");
        int counter = Integer.parseInt(numTaskMaking);

        // Create all the tasks
        while (counter > 0) {

            String taskName = JOptionPane.showInputDialog("Please enter your task name");

            String taskDesc;
            boolean isValidDescription;
            do {
                taskDesc = JOptionPane.showInputDialog("Please enter your task description of 50 characters or less.");
                int descSize = taskDesc.length();
                isValidDescription = descSize <= 50;
                if (!isValidDescription) {
                    String descBad = "Description not valid, it may not exceed 50 characters";
                    JOptionPane.showMessageDialog(null, descBad);
                }

            }

            while (!isValidDescription);
            {

                String enterDevName = JOptionPane.showInputDialog("Please enter your name.");
                String enterDevSurname = JOptionPane.showInputDialog("Please enter your surname.");
                String taskDuration = JOptionPane.showInputDialog("How long will your task take? Enter the time in hours.");
                String taskStatus = JOptionPane.showInputDialog("What is the status of this task? \n1) To Do \n2) Doing \n3) Done");


                if (Objects.equals(taskStatus, "1")) {
                    taskStatus = "To Do";
                } else if (Objects.equals(taskStatus, "2")) {
                    taskStatus = "Doing";
                } else {
                    taskStatus = "Done";
                }

                String taskID = taskName.substring(0, 2) + ":" + taskNumber + ":" + enterDevName.substring(enterDevName.length() - 3);
                taskID = taskID.toUpperCase();
                counter--;

                devNames.add(taskNumber, enterDevName + " " + enterDevSurname);
                taskNames.add(taskNumber, taskName);
                taskIDs.add(taskNumber, taskID);
                taskDurations.add(taskNumber, taskDuration);
                taskStatuses.add(taskNumber, taskStatus);

                String outputDetails = "Your task details:"
                        + "\nTask Status: " + taskStatus
                        + "\nDeveloper Details: " + enterDevName + " " + enterDevSurname
                        + "\nTask Number: " + (taskNumber + 1)
                        + "\nTask Name: " + taskName
                        + "\nTask Description: " + taskDesc
                        + "\nTask ID: " + taskID
                        + "\nTask Duration: " + taskDuration + " hours";
                JOptionPane.showMessageDialog(null, outputDetails);

            }

            taskNumber++;

        }
    }

    // Handle the reporting functionality for the program
    public static void getReporting() {
        Object[] reports = {
                "1 - Get DONE tasks",
                "2 - Get longest duration",
                "3 - Get task by name",
                "4 - Get task by developer",
                "5 - Delete task by name",
                "6 - Get fancy report",
                "7 - Go back"
        };

        int choice = getOptionInput("Please select a report", "Reporting", reports);

        switch (choice) {
            case 0:
                // Get tasks with DONE status
                for (int i = 0; i < taskStatuses.size(); i++) {
                    String taskStatus = taskStatuses.get(i);
                    if (taskStatus.equalsIgnoreCase("done")) {

                        String outputDetails = taskStatusSummary(i, taskStatus);
                        JOptionPane.showMessageDialog(null, outputDetails);
                    }
                }
                break;
            case 1:
                // Get the task with the longest duration (B)
                int index = 0;
                int max;
                try {

                    // From all the tasks find the task with the maximum duration
                    max = Integer.parseInt(taskDurations.get(index));
                    for (int i = 0; i < taskDurations.size(); i++) {
                        int current = Integer.parseInt(taskDurations.get(i));
                        if (current > max) {
                            index = i;
                            max = current;
                        }
                    }

                    // Ensure that we have a task that is not of 0 duration as this means no task was found
                    if (max > 0) {
                        String outputDetails = taskDeveloperSummary(index, String.valueOf(max));
                        JOptionPane.showMessageDialog(null, outputDetails);
                    }

                } catch (Exception e) {
                    System.err.println("There are no tasks to choose from");
                }
                break;
            case 2:
                String taskName = JOptionPane.showInputDialog("Please enter your task name");
                // Get tasks with DONE status
                for (int i = 0; i < taskNames.size(); i++) {
                    String task = taskNames.get(i);
                    if (task.equalsIgnoreCase(taskName)) {

                        String outputDetails = taskNameSummary(i, taskName);
                        JOptionPane.showMessageDialog(null, outputDetails);
                    }
                }
                break;
            case 3:
                String developerName = JOptionPane.showInputDialog("Please enter the developer name");
                // Get tasks with DONE status
                for (int i = 0; i < taskNames.size(); i++) {
                    String task = taskNames.get(i);
                    if (devNames.get(i).equalsIgnoreCase(developerName)) {
                        String outputDetails = taskStatusByNameSummary(i, task);
                        JOptionPane.showMessageDialog(null, outputDetails);
                    }
                }
                break;
            case 4:
                // Delete task by name
                String task = JOptionPane.showInputDialog("Please enter the task name");
                for (int i = 0; i < taskNames.size(); i++) {
                    if (taskNames.get(i).equalsIgnoreCase(task)) {
                        taskNames.remove(i);
                        break;
                    }
                }
                System.err.println("No task with the provided name existed");
                break;
            case 5:
                // Get an overall report of the tasks
                String outputDetails = tasksReport();
                JOptionPane.showMessageDialog(null, outputDetails);
                break;
            case 6:
            case -1:
                break;
            default:
                JOptionPane.showMessageDialog(null, "Coming soon!");
                break;
        }
    }

    // TODO: d.) Search for all task assigned to a developer and display they Task Name and task Status
    // TODO: e.) Delete a task using the Task Name
    // TODO: f.) Display a report that lists the full details of all captured tasks


    // Display the summary of all the tasks with the provided taskStatus
    private static String taskStatusSummary(int i, String taskStatus) {
        String enterDevName = devNames.get(i);
        String taskName = taskNames.get(i);
        String taskDuration = taskDurations.get(i);
        String taskID = taskIDs.get(i);

        return "Your task details:"
                + "\nTask Status: " + taskStatus
                + "\nDeveloper Details: " + enterDevName
                + "\nTask Name: " + taskName
                + "\nTask ID: " + taskID
                + "\nTask Duration: " + taskDuration + " hours";
    }

    private static String taskNameSummary(int i, String taskName) {
        String enterDevName = devNames.get(i);
        String taskStatus = taskStatuses.get(i);

        return "Your task details:"
                + "\nTask Status: " + taskStatus
                + "\nDeveloper Details: " + enterDevName
                + "\nTask Name: " + taskName;
    }

    private static String taskDeveloperSummary(int i, String duration) {
        String enterDevName = devNames.get(i);

        return "Your task details:"
                + "\nDeveloper Details: " + enterDevName
                + "\nTask Duration: " + duration;
    }

    private static String tasksReport() {
        int totalDevs = devNames.size();
        int totalTasks = taskNames.size();

        // Get the total task durations
        int total = 0;
        for (String taskDuration : taskDurations) {
            try {
                total += Integer.parseInt(taskDuration);
            } catch (Exception ex) {
                System.err.println("Invalid task duration presented, skipping summing");
            }
        }

        // Get how many tasks are in what duration:
        // 1) To Do
        int todo = 0;
        // 2) Doing
        int doing = 0;
        // 3) Done
        int done = 0;
        for (String taskStatus : taskStatuses) {
            switch (taskStatus.toLowerCase()) {
                case "to do":
                    todo++;
                    break;
                case "doing":
                    doing++;
                    break;
                case "done":
                    done++;
                    break;
                default:
                    System.err.println("Invalid task status: " + taskStatus);
            }
        }

        return "Total Developers: " + totalDevs + "\n" +
                "Total Tasks: " + totalTasks + "\n" +
                "Total Task Durations: " + total + "\n" +
                "Total TODO Tasks: " + todo + "\n" +
                "Total Doing Tasks: " + doing + "\n" +
                "Total Done Tasks: " + done + "\n";
    }

    /**
     * Retrieves the task name by index i and returns the status and task name.
     *
     * @param i        the index for the taskName for which the task status will be retrieved
     * @param taskName The name of the task found at index i
     * @return The summary of the task that
     */
    private static String taskStatusByNameSummary(int i, String taskName) {
        String status = taskStatuses.get(i);

        return "Your task details:"
                + "\nTask Name: " + taskName
                + "\nTask Status: " + status;
    }

    // Display the JOptionPane options to the user and return the selected option
    public static int getOptionInput(String title, String dialog, Object[] options) {
        return JOptionPane.showOptionDialog(
                null, // Parent component (null means center on screen)
                title, // Message to display
                dialog, // Dialog title
                JOptionPane.OK_CANCEL_OPTION, // Option type (Ok, Cancel)
                JOptionPane.QUESTION_MESSAGE, // Message type (question icon)
                null, // Custom icon (null means no custom icon)
                options, // Custom options array
                options[0] // Initial selection (default is "Cancel")
        );
    }

}
