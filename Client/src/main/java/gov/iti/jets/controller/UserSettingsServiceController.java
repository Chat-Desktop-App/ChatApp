package gov.iti.jets.controller;

import gov.iti.jets.RMIConnector;

public class UserSettingsServiceController {
    private static UserSettingsServiceController userSettingsService = (UserSettingsServiceController) RMIConnector.getRmiConnector().getUserSettingsService();
}
