package aut.bme.hu.eventr.view;

import java.util.List;

public interface CalendarView {

    void selectDay();

    void navigateToMonth();

    void highlightDays(List<Long> dates);

    void unhighlightDay(Long date);

}
