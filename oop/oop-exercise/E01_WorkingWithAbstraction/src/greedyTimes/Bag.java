package greedyTimes;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Bag {

    private long capacity;
    private long totalGold;
    Map<String, Long> gold;
    private long totalGems;
    Map<String, Long> gems;
    private long totalCash;
    Map<String, Long> cash;

    public Bag(long capacity) {
        this.capacity = capacity;
        this.gold = new LinkedHashMap<>();
        this.gems = new LinkedHashMap<>();
        this.cash = new LinkedHashMap<>();
    }

    public void acceptInput(String[] input) {
        for (int i = 0; i < input.length; i+=2) {
            String name = input[i];
            long value = Long.parseLong(input[i+1]);

            if (name.length() == 3) {
              addCash(name, value);
            } else if (name.toLowerCase().endsWith("gem")) {
               addGems(name, value);
            } else if (name.toLowerCase().equals("gold")) {
                addGold(name, value);
            }
        }
    }

    public void addGold(String name, long value) {
        if (bagNotFull(value)) {
            this.gold.putIfAbsent(name, 0L);
            this.gold.put(name, gold.get(name) + value);
            this.totalGold += value;
        }
    }

    public void addGems(String name, long value) {
        if (bagNotFull(value) && this.totalGems + value <= this.totalGold) {
            this.gems.putIfAbsent(name, 0L);
            this.gems.put(name, gems.get(name) + value);
            this.totalGems += value;
        }
    }

    public void addCash(String name, long value) {
        if(bagNotFull(value) && this.totalCash + value <= totalGems) {
            this.cash.putIfAbsent(name, 0L);
            this.cash.put(name, cash.get(name) + value);
            this.totalCash+= value;
        }
    }

    public boolean bagNotFull(long value) {
        if (this.totalGold + this.totalGems + this.totalCash + value <= capacity) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (this.gold.size() > 0) {
            result.append("<Gold> ").append("$").append(this.totalGold).append(System.lineSeparator());
            result.append("##Gold - ").append(this.totalGold).append(System.lineSeparator());

        }
        if (this.gems.size() > 0) {
            result.append("<Gem> ").append("$").append(this.totalGems).append(System.lineSeparator());
            this.gems.entrySet().stream().sorted((l,r) -> {
                int num = r.getKey().compareTo(l.getKey());
                if (num ==0) {
                    num = Long.compare(l.getValue(), r.getValue());
                }
                return num;
            }).forEach(e -> result.append("##").append(e.getKey()).append(" - ").append(e.getValue()).append(System.lineSeparator()));
        }
        if (this.cash.size() > 0) {
            result.append("<Cash> ").append("$").append(this.totalCash).append(System.lineSeparator());
            this.cash.entrySet().stream().sorted((l,r) -> {
                int num = r.getKey().compareTo(l.getKey());
                if (num ==0) {
                    num = Long.compare(l.getValue(), r.getValue());
                }
                return num;
            }).forEach(e -> result.append("##").append(e.getKey()).append(" - ").append(e.getValue()).append(System.lineSeparator()));
        }

        return String.valueOf(result).trim();

    }
}