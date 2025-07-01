package Models.DTO;

public class UserError {
    private String userNameError;
    private String lastNameError;
    private String passwordError;
    private String duplicatedUserName;

    public UserError() {
    }

    public UserError(String userNameError, String lastNameError, String passwordError, String duplicatedUserName) {
        this.userNameError = userNameError;
        this.lastNameError = lastNameError;
        this.passwordError = passwordError;
        this.duplicatedUserName = duplicatedUserName;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getDuplicatedUserName() {
        return duplicatedUserName;
    }

    public void setDuplicatedUserName(String duplicatedUserName) {
        this.duplicatedUserName = duplicatedUserName;
    }
    
    
}
