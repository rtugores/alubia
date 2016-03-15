package huitca1212.alubia13.utils;

public class DialogParams {
	private String title, message, positiveButton, negativeButton;

	public DialogParams(){

	}

	public DialogParams(String title, String message, String positiveButton, String negativeButton) {
		this.title = title;
		this.message = message;
		this.positiveButton = positiveButton;
		this.negativeButton = negativeButton;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getPositiveButton() {
		return positiveButton;
	}

	public String getNegativeButton() {
		return negativeButton;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPositiveButton(String positiveButton) {
		this.positiveButton = positiveButton;
	}

	public void setNegativeButton(String negativeButton) {
		this.negativeButton = negativeButton;
	}
}

