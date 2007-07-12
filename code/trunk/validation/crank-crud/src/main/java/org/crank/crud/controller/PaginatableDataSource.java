package org.crank.crud.controller;

import java.util.List;

public interface PaginatableDataSource {
    public List list(int startItem, int numItems);
    public int getCount();
}
