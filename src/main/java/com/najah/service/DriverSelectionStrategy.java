package com.najah.service;

import com.najah.domain.Driver;
import java.util.List;

public interface DriverSelectionStrategy
{

    Driver selectDriver(List<Driver> availableDrivers);
}