DESIGN AND ANALYSIS OF ALGORITHMS
CSC4202- 4



1.0 Introduction
As the modern world continues to grapple with global health challenges like the COVID-19 pandemic, the efficient and equitable distribution of limited resources—especially vaccines—has become one of the greatest challenges to governments and health organizations across the globe. While the situation is dire, the greater question is: how will vaccines be distributed? Because the consequences of little or inefficient distribution may be preventable death, persistent outbreaks, and stretched healthcare infrastructure.
Vaccines are produced in batches and are limited for many reasons during the distribution phase. During the early phases of a pandemic or an outbreak, authorities have to quickly deliver vaccines to disparate areas with differing levels of urgency, needs, and ability to accommodate the many vaccines being delivered.
Meanwhile, 'manual' decision-making or equal distribution (first come, first served or some kind of per-capita distribution system) are frequently uneven and ineffective, and distribution decisions are based on consideration of infection severity, population size, level of efficacy for vaccine(s) in an at-risk population and so on. Therefore, computer- based and data-based decisions through the implementation of the algorithmic optimization of delivery assessments and criteria are critical; from the production of vaccines to demand and eventual illness.

This study investigates the use of Dynamic Programming (DP) as a means to solve a constrained vaccine allocation problem. The problem is formulated as a variant of the 0/1 Knapsack Problem, resulting in a model that ensures: 

Global optimality in lives saved; 

Adequate consideration of logistical constraints such as fixed dose sizes; 

Scalability so decisions can be made in real-time. 

We provide a rigorous, mathematically sound solution that can assist policy makers in maximizing public health impact when faced with constrained resources.


2.0 Problem Statement
In a pandemic situation, assume a central government or public health authority is responsible for distributing a limited number of vaccine doses to a set of cities or regions. Each city has its own set of influencing factors, such as:
A different population size

A unique infection rate, indicating the proportion of people at risk

A vaccine efficacy score, which may vary due to factors like demographics, climate, or logistical capacity

A non-negotiable dose requirement; partial allocation is ineffective or impractical

The goal is to determine which cities should receive vaccine allocations such that:
The total number of lives saved is maximized, and

The total doses distributed do not exceed the central supply.
The problem becomes even more important when considering:
Real-time distribution across changing outbreak zones

Fair distribution among under-resourced regions

Ensuring no vaccine wastage through incomplete allocations

Therefore, solving this problem with a robust algorithm like Dynamic Programming is both a technical necessity and a moral imperative in public health management.

3.0 Scenario 
We consider a scenario where a central authority is tasked with distributing a limited supply of 100,000 vaccine doses across multiple cities. Each city has unique characteristics affecting how impactful the vaccines would be there.
Each city has:
Varying population sizes (ranging from hundreds of thousands to millions)

Different infection rates (reflecting the severity of the outbreak)

Different vaccine efficacy (depending on logistics, climate, and demographic fit)

Fixed vaccine dose requirements (no partial allocation allowed)

Example Scenario :
City
Population
Infection Rate
Efficacy
Doses Needed
City A
1,000,000
10%
90%
50,000
City B
500,000
20%
70%
30,000

4.0 Objective
The primary objective of this project is to develop an algorithm that enables the optimal allocation of a limited supply of vaccine doses across multiple cities, with the aim of maximizing the total number of lives saved.
To achieve this, the solution must:
Select a subset of cities that receive complete vaccine allocations based on a fixed dose requirement. Partial vaccinations are not allowed, meaning a city must receive all required doses or none.

Respect the global constraint of a total vaccine dose limit (e.g., 100,000 doses).

Account for city-specific parameters, including:

Population size

Infection rate (indicating outbreak severity)

Vaccine efficacy (contextual effectiveness in that region)

Calculate the potential number of lives saved for each city based on the formula:
 livesSaved[i]= (population[i]×infectionRate[i]×efficacy[i])  10,000
Ensure fairness and efficiency in vaccine distribution, especially when dealing with diverse regional demands and outbreak intensities.

Secondary Objectives:
Model the problem as a 0/1 Knapsack Problem, leveraging a proven dynamic programming approach to guarantee optimal results.

Minimize vaccine wastage by avoiding partial allocations and optimizing dose utilization.

Support policy-making decisions through a transparent and traceable algorithmic solution that explains why specific cities were selected.

Lay the groundwork for future enhancements such as:

Regional priority weighting

Time-sensitive distribution

Budget or cost constraints

Support for real-time data input

This objective ensures that the solution is not only technically optimal but also ethically responsible and realistically applicable in critical public health scenarios.

5.0 Algorithm 
5.1 Algorithm comparison
Paradigm
Strengths
Weaknesses
Suitability
Sorting
Easy to implement; can prioritize by "lives per dose"
Ignores constraints; not globally optimal
❌ Not suitable
Divide and Conquer (DAC)
Works for independent subproblems
Cannot handle constraints across subproblems
❌ Not suitable
Greedy
Fast; prioritizes high-efficiency cities
May skip combinations that yield higher total lives
⚠️ Suboptimal
Graph (Max Flow)
Great for network flow and capacities
Too complex for this setting; requires flow structures
❌ Overkill
Dynamic Programming (DP)
Guarantees global optimality; handles constraints
Slightly higher time/memory cost
✅ Best fit


5.2 Dynamic Programming
The vaccine allocation problem is a variation of the 0/1 Knapsack Problem, where:

Cities = Items
Doses = Weights
Lives Saved = Values

Dynamic Programming is ideal because:
It guarantees optimality by exploring all valid dose combinations.
It respects constraints (no city can be partially vaccinated).
It allows memoization of overlapping subproblems, improving efficiency.

5.3 Algorithm Explanation
We define a 2D DP table:
dp[d][c] = Maximum lives saved using d doses and the first c cities.
Recurrence Relation : 

dp[d][c] = max(
    dp[d][c - 1],                                     // Skip city c
    dp[d - doses[c]][c - 1] + livesSaved[c]           // Include city c
)

Base case 
dp[0][c] = 0 → No doses = 0 lives saved
dp[d][0] = 0 → No cities = 0 lives saved

Optimization goal
Maximize:
maximize ∑ livesSaved[i]
Subject to:
subject to ∑ doses[i] ≤ totalDoses
Where:
livesSaved[i] = (population × infectionRate × efficacy) / 10000

7.0 Algorithm Correctness Analysis
An established dynamic programming technique based on the 0/1 Knapsack Problem serves as the foundation for the algorithm used to solve this vaccine allocation problem. The following features show that it is correct through the following aspects : 
Base Case Initialization
The DP table dp[d][c] is initialized such that : 
dp[0][c] = 0 → No lives can be saved with 0 availabe doses,regardless of how many cities exist.
Dp[d][0] = 0 → No lives can be saved with 0 cities to consider ,regardless of the number of avaiable doses .
These base cases ensure a proper starting point for the DP recurrence to function correctly.

Recurrence Relation Validity
The recurrence formula:
dp[d][c] = max(
    dp[d][c - 1],                           // Exclude current city
    dp[d - doses[c]][c - 1] + livesSaved[c] // Include current city
)

Skipping the city

Including the city, if enough doses remain

This guarantees that all valid combinations are explored and the maximum value (lives saved) is retained at each state.
Subproblem Overlap
Using DP prevents needless recomputation, preserving accuracy and efficiency because decisions depend on previously calculated values (i.e., overlapping subproblems).

Backtracking
The cities that contribute to the best solution are tracked in a selected[][] table.

By listing precise city allocations, this helps confirm the accuracy and offers a traceable explanation of the outcome.

Proven Optimality
The DP solution guarantees global optimality, as it completely considers all feasible subsets under the constraints. This makes it ideal for critical real-world problems like vaccine allocation, where accuracy and fairness are essential.

8.0  Performance  Analysis
Time Complexity Analysis
Let : 
n = number of cities
d = total number of available vaccine doses

The time and space complexity of the algorithm are analyzed as follows:

Case
Time Complexity
Explanation
Best
Ω(n × d)
To assess potential combinations, every entry in the DP table needs to be taken into account.
Average
Θ(n × d)
Regardless of the data distribution, the DP table is always completely filled.
Worst
O(n × d)
Every city and dose combination requires an evaluation of every cell in the DP table.

Space Complexity : 

To store intermediate results for every city and every possible dose value, the DP table needs O(n × d) space.
However, if only the maximum value is needed (i.e., when tracking the chosen cities is not required), space can be optimized to O(d) using a 1D array.

9.0 Conclusion

This paper suggests that the best algorithmic paradigm to solve the vaccine allocation problem in a constrained environment is dynamic programming. Dynamic programming can explore every unique combination of cities selected while not violating a limit on doses of vaccine. Thus, dynamic programming guarantees optimality, unlike greedy or divide and conquer.

The algorithm allows for the maximization of the number of lives saved by calculating the optimal combination of cities receiving vaccines based on the city's population, infection rate, efficacy, and dose required. Additionally, the functioning of the algorithm is efficient for larger input sizes, guarantees correctness by following a structured recurrence, and logs output for cities selected which can be traced back.

Additionally, the model may be scaled and adjusted to account for requests in the real world features, such as weighting for risk of mortality, prioritization by region, partial vaccination, or budgeting. Dynamic programming is a powerful technique to address public health resource allocation problems because of the flexibility and guaranteed optimality.

