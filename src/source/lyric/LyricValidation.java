package source.lyric;

import source.alert.AlertHelper;

class LyricValidation {

    private LyricFormController parent;
    private AlertHelper alertHelper;

    LyricValidation(LyricFormController parent) {
        this.parent = parent;
        alertHelper = new AlertHelper();
    }

    boolean isInputValid() {
        String selectedBand = parent.newLyricBandName.getSelectionModel().getSelectedItem();
        String selectedAlbum = parent.newLyricAlbumName.getSelectionModel().getSelectedItem();
        String selectedSong = parent.newLyricSongName.getSelectionModel().getSelectedItem();

        if (selectedBand == null || "".equals(selectedBand)) {
            alertHelper.displayAlert("Select Band");
            return false;
        } else if (selectedAlbum == null || "".equals(selectedAlbum)) {
            alertHelper.displayAlert("Select Album");
            return false;
        } else if (selectedSong == null || "".equals(selectedSong)) {
            alertHelper.displayAlert("Select Song");
            return false;
        } else if ("".equals(parent.newLyrics.getText())) {
            alertHelper.displayAlert("Enter Lyrics");
        }
        return true;
    }
}
