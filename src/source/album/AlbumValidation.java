package source.album;

import source.alert.AlertHelper;
import source.band.BandRepository;

import java.util.Calendar;

class AlbumValidation {

    private AlbumFormController parent;
    private AlertHelper alertHelper;
    private BandRepository bandRepository;

    AlbumValidation(AlbumFormController parent) {
        this.parent = parent;
        alertHelper = new AlertHelper();
        bandRepository = new BandRepository();
    }

    boolean isInputValid() {
        if (parent.newAlbumBandName.getSelectionModel().getSelectedItem() == null) {
            alertHelper.displayAlert("Select Band");
            return false;
        } else if ("".equals(parent.newAlbumName.getText())) {
            alertHelper.displayAlert("Enter Album Name");
            return false;
        } else if ("".equals(parent.newAlbumReleaseYear.getText())) {
            alertHelper.displayAlert("Enter Album Release Year");
            return false;
        } else if (!parent.newAlbumReleaseYear.getText().matches("[0-9]{4}")) {
            alertHelper.displayAlert("Album Release Year should contains four digits");
            return false;
        } else {
            int bandFormedIn = bandRepository.getBandFormedIn(parent.newAlbumBandName.getSelectionModel().getSelectedItem());
            if (Integer.parseInt(parent.newAlbumReleaseYear.getText()) < bandFormedIn) {
                alertHelper.displayAlert("Album Release Year cannot be less than band formed in year (" + bandFormedIn + ")");
                return false;
            } else if (Integer.parseInt(parent.newAlbumReleaseYear.getText()) > Calendar.getInstance().get(Calendar.YEAR)) {
                alertHelper.displayAlert("Album Release Year cannot be greater than " + Calendar.getInstance().get(Calendar.YEAR));
                return false;
            }
        }
        return true;
    }
}
