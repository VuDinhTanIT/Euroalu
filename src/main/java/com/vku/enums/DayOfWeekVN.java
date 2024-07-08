package com.vku.enums;

public enum DayOfWeekVN {
    THU_HAI(1, "Thứ 2"),
    THU_BA(2, "Thứ 3"),
    THU_TU(3, "Thứ 4"),
    THU_NAM(4, "Thứ 5"),
    THU_SAU(5, "Thứ 6"),
    THU_BAY(6, "Thứ 7"),
    CHU_NHAT(7, "Chủ nhật");

    private final int index;
    private final String name;

    DayOfWeekVN(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}