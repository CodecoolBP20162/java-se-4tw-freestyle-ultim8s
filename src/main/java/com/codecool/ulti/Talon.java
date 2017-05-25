package com.codecool.ulti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by peter on 2017.05.23..
 */
public class Talon extends CardHolder {
    private static final Logger logger = LoggerFactory.getLogger(Talon.class);

    public Map<Integer, Card> cards = hand;
}
