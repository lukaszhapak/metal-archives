package source.song;

import source.alert.AlertHelper;

class SongValidation {

    private SongFormController parent;
    private AlertHelper alertHelper;

    SongValidation(SongFormController parent) {
        this.parent = parent;
        alertHelper = new AlertHelper();
    }

    boolean isInputValid() {
        String selectedBand = parent.newSongBandName.getSelectionModel().getSelectedItem();
        if (selectedBand == null || "".equals(selectedBand)) {
            alertHelper.displayAlert("Select Band");
            return false;
        } else {
            String selectedAlbum = parent.newSongAlbumName.getSelectionModel().getSelectedItem();
            if (selectedAlbum == null || "".equals(selectedAlbum)) {
                alertHelper.displayAlert("Select Album");
                return false;
            } else if ("".equals(parent.newSongName.getText())) {
                alertHelper.displayAlert("Enter Song Name");
                return false;
            } else if ("".equals(parent.newSongDuration.getText())) {
                alertHelper.displayAlert("Enter Song Duration");
                return false;
            } else if (!parent.newSongDuration.getText().matches("[0-9]+(\\.[0-9][0-9]?)?")) {
                alertHelper.displayAlert("Song Duration should contains decimal number (dot as a separator, only two digits after dot)");
                return false;
            }
        }
        return true;
    }
}
