package interfaces;

import interfaces.ISpecification;

import java.util.stream.Stream;

public interface Filter<T> {
    Stream<T> filter (Stream<T> items, ISpecification<T> spec);
}
