package specifications;

import entities.AlienChild;
import interfaces.ISpecification;

public class FinalYearSpecification implements ISpecification<AlienChild> {
    private int finalYear;

    public FinalYearSpecification() {
        this.finalYear = 6;
    }

    @Override
    public boolean isSatisfied(AlienChild item) {
        return item.getYearOfStudy() == finalYear;
    }
}
