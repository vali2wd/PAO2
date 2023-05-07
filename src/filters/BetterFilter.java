package filters;

import entities.AlienChild;
import interfaces.Filter;
import interfaces.ISpecification;

import java.util.stream.Stream;

public class BetterFilter implements Filter<AlienChild> {
    @Override
    public Stream<AlienChild> filter(Stream<AlienChild> items, ISpecification<AlienChild> spec) {
        return items.filter(p -> spec.isSatisfied(p));
    }
}
