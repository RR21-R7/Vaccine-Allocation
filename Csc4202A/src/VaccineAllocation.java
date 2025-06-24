
public class VaccineAllocation {

    static class City {
        String name;
        int population, infectionRate, efficacy, doses;

        public City(String name, int population, int infectionRate, int efficacy, int doses) {
            this.name = name;
            this.population = population;
            this.infectionRate = infectionRate;
            this.efficacy = efficacy;
            this.doses = doses;
        }

        public int livesSaved() {
            return (population * infectionRate * efficacy) / 10000;
        }
    }

    public static int allocateVaccines(City[] cities, int totalDoses) {
        int n = cities.length;
        int[][] dp = new int[totalDoses + 1][n + 1];
        boolean[][] selected = new boolean[totalDoses + 1][n + 1];

        // Start timer
        long startTime = System.currentTimeMillis();

        // Fill DP table
        for (int d = 0; d <= totalDoses; d++) {
            for (int c = 1; c <= n; c++) {
                City city = cities[c - 1];
                if (city.doses <= d) {
                    int include = dp[d - city.doses][c - 1] + city.livesSaved();
                    int exclude = dp[d][c - 1];
                    if (include > exclude) {
                        dp[d][c] = include;
                        selected[d][c] = true;
                    } else {
                        dp[d][c] = exclude;
                    }
                } else {
                    dp[d][c] = dp[d][c - 1];
                }
            }
        }

        int maxLivesSaved = dp[totalDoses][n];

        // Backtrack to find selected cities
        int d = totalDoses;
        int c = n;
        int totalUsedDoses = 0;
        System.out.println("Selected Cities:");
        while (c > 0) {
            if (selected[d][c]) {
                City city = cities[c - 1];
                System.out.println("- " + city.name + ": " + city.doses + " doses → " + city.livesSaved() + " lives saved");
                totalUsedDoses += city.doses;
                d -= city.doses;
            }
            c--;
        }

        System.out.println("\nTotal vaccines used (DP): " + totalUsedDoses);
        System.out.println("Max lives saved: " + maxLivesSaved);

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " ms");


        return maxLivesSaved;
    }

    public static void main(String[] args) {
        City[] cities = {
            new City("City A", 1000000, 10, 90, 50000),
            new City("City B", 500000, 20, 70, 30000)
        };

        int totalDoses = 100000;
        allocateVaccines(cities, totalDoses);
    }
}
