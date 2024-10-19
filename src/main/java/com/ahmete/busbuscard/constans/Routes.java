package com.ahmete.busbuscard.constans;


import java.util.List;

public class Routes {
    public static final List<String> ferryRouteList = List.of("AF", "BF");

    public static final List<String> busRouteList = List.of(
            "AB0", "AB1S", "AB1", "AB2S", "AB2", "AB3S",
            "AB3", "AB4S", "AB4", "AB5S", "AB5", "AB6S", "AB6", "ABQ"
    );

    public static final List<String> subwayRouteList = List.of("AS0", "AB1S", "AB2S", "AB3S", "AB4S", "AB5S", "AB6S", "AB6S", "ASQ");
}
