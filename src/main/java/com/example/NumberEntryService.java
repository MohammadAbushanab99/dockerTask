package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumberEntryService {

    private List<Double> numbers;
    @Autowired
    private NumberEntryRepository numberEntryRepository;


    public void saveNumberEntry(Double value) {
        NumberEntry numberEntry = new NumberEntry();
        numberEntry.setValue(value);
        numberEntryRepository.save(numberEntry);
    }


}