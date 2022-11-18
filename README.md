## Реализация трех алгоритмов
### Бинарный
``` java   
public static int binarySearch(int[][] arr, int target) {
        int columns = arr[0].length - 1;
        int rows = 0;
        while (columns >= 0 && rows < arr.length) {
            if (target > arr[rows][columns]) {
                ++rows;
            } else if (target < arr[rows][columns]) {
                columns = entryFirst(target, -1, columns, arr[rows]);
            } else {
                return columns;
            }
        }
        return -1;
    }
```
**Асимптотика: O(Mlog(N))**
### Линейный (обход лестницей)
``` java
    public static int linearSearch(int[][] arr, int target) {
        int columns = arr[0].length - 1;
        int rows = 0;
        while (columns >= 0 && rows < arr.length) {
            if (target > arr[rows][columns]) {
                ++rows;
            } else if (target < arr[rows][columns]) {
                --columns;
            } else {
                return columns;
            }
        }
        return -1;
    }
 ```
**Асимптотика: O(M + N)**
### Экспоненциальный (обход лестницей)
``` java
    public static int exponentialSearch(int[][] arr, int target, int value) {
        int rows = 0;
        int columns = arr[0].length - 1;
        while (columns >= 0 && rows < arr.length) {
            if (arr[rows][columns] > target) {
                if (columns > Math.pow(2, 5)) {
                    while (arr[rows][Math.abs(columns - value)] > target && columns >= value) {
                        if (value == 0) {
                            value++;
                        } else {
                            value *= 2;
                        }
                    }
                    value /= 2;
                } else {
                    value = 0;
                }
                columns = entryFirst(target, -1, columns - value, arr[rows]);
            } else if (arr[rows][columns] < target) {
                ++rows;
            } else {
                return columns;
            }
        }
        return -1;
    }
 ```
**Асимптотика: O(Mlog(N))**

## Реализация двух генераций данных
### Первая генерация (target = 2*N + 1)
## __arr[i][j] = ((columns / rows) * i + j) * 2;__
``` java
    public static int[][] firstMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i + j) * 2;
            }
        }
        return arr;
    }
 ```
 ### Вторая генерация (target = 16*N + 1)
 ## __arr[i][j] = ((columns / rows) * i * j) * 2;__
 ``` java
     public static int[][] secondMatrixGeneration(int rows, int columns) {
        int[][] arr = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                arr[i][j] = ((columns / rows) * i * j) * 2;
            }
        }
        return arr;
    }
 ```

## Результаты запусков (нс)
| M | BIN | EXP_FIRST | STANDARD | EXP_SECOND |
|:----|:----|:----|:----|:---- |
| 2 | 23,54 | 52,12 | 1418,24 | 30,12 |
| 4 | 134,35 | 146,75 | 2124,2 | 72,57 |
| 8 | 298,14 | 317,36 | 2883,17 | 146,4 |
| 16 | 706,06 | 702,8 | 2531,68 | 294,21 |
| 32 | 1435,81 | 1533,23 | 2912,26 | 451,56 |
| 64 | 3378,67 | 3631,59 | 3267,42 | 411,73 |
| 128 | 7372,43 | 8626,22 | 4124,63 | 733,65 |
| 256 | 20446,16 | 18368,57 | 4497,5 | 1557,32 |
| 512 | 37361,3 | 21572,13 | 5731,94 | 2619,41 |
| 1024 | 41112,11 | 33467,32 | 12147,97 | 4903,16 |
| 2048 | 51791,99 | 68421,04 | 22536,21 | 12542,7 |
| 4096 | 104733,46 | 152643,62 | 34864,21 | 32513,63 |
| 8192 | 337165,89 | 220632,31 | 83174,05 | 81572,61 |


## Визуализация

### Линейное представление времени работы алгоритмов 
![Linear](https://github.com/pestrikv/algorithms_lab/blob/master/standard_plot.png)

### Логарифмическое представление времени работы алгоритмов 
![Log](https://github.com/pestrikv/algorithms_lab/blob/master/log_plot.png)

### Логарифмическое отношение времени двух экспоненциальных кривых 
![Exp_ratio](https://github.com/pestrikv/algorithms-matrix-comparison/blob/master/expdif_plot.png)

## Вывод
Исходя из результатов графических представлений, можно увидеть, что до размера M = 10, бинарный и экспоненциальный поиски идут вровень, после же бинарный начинает заметно отставать, а потом и вовсе замедляется. Можно объяснить тем, что бинарный постоянно двигается на единицу влево и тратит логарифмическую единицу операций, в отличие от экспоненциального алгоритма.

Во всех матрицах первой генерации сгенерированных из M++ строк, быстрее всего себя показал линейный алгоритм. Такое время можно обосновать его асимптотикой O(M + N), которая в логарифмическую единицу раз меньше. Но, во второй генерации при некоторых случаях этот алгоритм начал проигрывать по времени, так как target становится равным 16 * 2^^13 + 1 = 131073. Тут линейный не может сразу прийти к началу и ему приходится делать большой обход.
