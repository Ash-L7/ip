package shadow.componentGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import shadow.Shadow;

public class MainWindow extends AnchorPane {
    private Shadow shadowChatbot;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image shadowImage = new Image(this.getClass().getResourceAsStream("/images/Shadow.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Shadow instance */
    public void setShadow(Shadow shadow) {
        this.shadowChatbot = shadow;
        String startup = shadow.getStartupMessage();

        if (startup != null && !startup.isEmpty()) {
            dialogContainer.getChildren().add(
                    DialogBox.getShadowDialog(startup, shadowImage)
            );
        }
    }

    /**
     * Creates a dialog box containing user input, and appends it to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userCommand = userInput.getText();
        String shadowChatbotResponse = shadowChatbot.getResponse(userCommand);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userCommand, userImage),
                DialogBox.getShadowDialog(shadowChatbotResponse, shadowImage)
        );
        userInput.clear();
    }
}
