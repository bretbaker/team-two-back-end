package com.agora.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgoraHome {

    @FindBy(id="login-password")
    public WebElement username;
    public WebElement password;
}
