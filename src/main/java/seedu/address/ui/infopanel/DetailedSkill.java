package seedu.address.ui.infopanel;

import seedu.address.model.person.Person;

/**
 * DetailedSkill controller for showing skill details at DetailedInfoPanel.
 */
public class DetailedSkill extends DetailedInfo {

    private static final String FXML = "DetailedSkill.fxml";

    public DetailedSkill(Person protagonist) {
        super(FXML);
    }

}
