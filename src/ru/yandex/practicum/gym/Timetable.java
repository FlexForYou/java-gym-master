package ru.yandex.practicum.gym;


import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();
    private final TreeMap<Coach, Integer> coachesCounter = new TreeMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {

        TreeMap<TimeOfDay, List<TrainingSession>> trainingSessionsMap = timetable.getOrDefault(trainingSession.getDayOfWeek(),
                new TreeMap<>());
        List<TrainingSession> trainingSessionList = trainingSessionsMap.getOrDefault(trainingSession.getTimeOfDay(),
                new ArrayList<>());
        trainingSessionList.add(trainingSession);
        trainingSessionsMap.put(trainingSession.getTimeOfDay(), trainingSessionList);

        Coach currentCoach = trainingSession.getCoach();
        coachesCounter.put(currentCoach, coachesCounter.getOrDefault(currentCoach, 0) + 1);

        timetable.put(trainingSession.getDayOfWeek(), trainingSessionsMap);


    }


    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.get(dayOfWeek) == null) {
            return new TreeMap<>();
        }
        return timetable.get(dayOfWeek);
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
        if (timetable.get(dayOfWeek).get(timeOfDay) == null) {
            return new ArrayList<>();
        }
        return timetable.get(dayOfWeek).get(timeOfDay);
    }

    public TreeMap<Coach, Integer> getCountByCoaches() {
        return this.coachesCounter;
    }

}
