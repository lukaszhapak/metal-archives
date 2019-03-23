package source.band;

import source.alert.AlertHelper;

import java.util.Calendar;

class BandValidation {

    private BandFormController parent;
    private AlertHelper alertHelper;

    BandValidation(BandFormController parent) {
        this.parent = parent;
        alertHelper = new AlertHelper();
    }

    boolean isInputValid() {
        if ("".equals(parent.newBandName.getText())) {
            alertHelper.displayAlert("Enter Band Name");
            return false;
        } else if ("".equals(parent.newBandCountry.getText())) {
            alertHelper.displayAlert("Enter Band Country");
            return false;
        } else if ("".equals(parent.newBandFormedIn.getText())) {
            alertHelper.displayAlert("Enter Band Formed In");
            return false;
        } else if (!parent.newBandFormedIn.getText().matches("[0-9]{4}")) {
            alertHelper.displayAlert("Band Formed In should contains four digits");
            return false;
        } else if (Integer.parseInt(parent.newBandFormedIn.getText()) < 1960) {
            alertHelper.displayAlert("Band Formed In cannot be less than 1960");
            return false;
        } else if (Integer.parseInt(parent.newBandFormedIn.getText()) > Calendar.getInstance().get(Calendar.YEAR)) {
            alertHelper.displayAlert("Band Formed In cannot be greater than " + Calendar.getInstance().get(Calendar.YEAR));
            return false;
        } else if (parent.newBandGenres.getItems().size() < 1) {
            alertHelper.displayAlert("Select at least one genre");
            return false;
        }
        return true;
    }
}
