package OnlineJudge.p11635;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            int cityCount = Integer.parseInt(reader.readLine());

            if (cityCount == 0) {
                break;
            }

            ArrayList<ArrayList<Road>> cityGraph = new ArrayList<>();
            for (int i = 0; i <= cityCount; i++) {
                cityGraph.add(new ArrayList<>());
            }

            String[] parts = reader.readLine().split(" ");
            int hotelCount = Integer.parseInt(parts[0]);
            int[] hotelCities = new int[hotelCount + 2];
            hotelCities[0] = 1;
            for (int i = 1; i <= hotelCount; i++) {
                hotelCities[i] = Integer.parseInt(parts[i]);
            }
            hotelCities[hotelCount + 1] = cityCount;

            ArrayList<ArrayList<Integer>> hotelGraph = new ArrayList<>();
            for (int i = 0; i < hotelCities.length; i++) {
                hotelGraph.add(new ArrayList<>());
            }

            int roadCount = Integer.parseInt(reader.readLine());

            for (int i = 0; i < roadCount; i++) {
                parts = reader.readLine().split(" ");
                int city1 = Integer.parseInt(parts[0]);
                int city2 = Integer.parseInt(parts[1]);
                int time = Integer.parseInt(parts[2]);
                cityGraph.get(city1).add(new Road(city2, time));
                cityGraph.get(city2).add(new Road(city1, time));
            }

            for (int i = 0; i < hotelCities.length; i++) {
                int startCity = hotelCities[i];
                int[] time = new int[cityCount + 1];
                Arrays.fill(time, Integer.MAX_VALUE);

                PriorityQueue<City> priorityQueue = new PriorityQueue<>((first, second) -> Integer.compare(first.time, second.time));
                time[startCity] = 0;
                priorityQueue.add(new City(startCity, 0));

                while (!priorityQueue.isEmpty()) {
                    City current = priorityQueue.poll();

                    if (current.time != time[current.city]) {
                        continue;
                    }

                    for (Road road : cityGraph.get(current.city)) {
                        int newTime = current.time + road.time;

                        if (newTime > 600) {
                            continue;
                        }

                        if (newTime < time[road.node]) {
                            time[road.node] = newTime;
                            priorityQueue.add(new City(road.node, newTime));
                        }
                    }
                }

                for (int j = 0; j < hotelCities.length; j++) {
                    if (i != j && time[hotelCities[j]] <= 600) {
                        hotelGraph.get(i).add(j);
                    }
                }
            }

            int[] hotelsFound = new int[hotelCities.length];
            Arrays.fill(hotelsFound, -1);

            Queue<Integer> queue = new LinkedList<>();
            hotelsFound[0] = 0;
            queue.add(0);

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int next : hotelGraph.get(current)) {
                    if (hotelsFound[next] == -1) {
                        hotelsFound[next] = hotelsFound[current] + 1;
                        queue.add(next);
                    }
                }
            }

            int finalHotel = hotelCities.length - 1;
            if (hotelsFound[finalHotel] == -1) {
                System.out.println(-1);
            } else {
                System.out.println(Math.max(0, hotelsFound[finalHotel] - 1));
            }
        }
    }

    static class Road {
        int node;
        int time;

        Road(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    static class City {
        int city;
        int time;

        City(int city, int time) {
            this.city = city;
            this.time = time;
        }
    }
}
