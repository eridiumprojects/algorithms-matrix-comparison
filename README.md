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
    public static int exponential(int[][] arr, int target) {
        int rows = 0;
        int columns = arr[0].length - 1;
        while (true) {
            if (arr[rows][columns] == target) {
                return columns;
            }
            else if (arr[rows][columns] < target && rows == arr.length - 1) {
                return -1;
            }
            else if (arr[rows][columns] > target) {
                int jmp = 1;
                int l = columns;
                while (arr[rows][l] > target) {
                    l -= jmp;
                    jmp *= 2;
                    if (l < 0) {
                        l = 0;
                        break;
                    }
                }
                columns = entryFirst(target,l,columns,arr[rows]);
            } else {
                rows += 1;
            }
        }
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


## Визуализация

### Линейное представление времени работы алгоритмов 
![Linear](https://github.com/pestrikv/algorithms_lab/blob/master/standard_plot_new.png)

### Логарифмическое представление времени работы алгоритмов 
![Log](https://github.com/pestrikv/algorithms_lab/blob/master/log_plot_new.png)

### Логарифмическое отношение времени двух экспоненциальных кривых 
![Exp_ratio](https://github.com/pestrikv/algorithms-matrix-comparison/blob/master/expdif_plot.png)

## Вывод
Исходя из результатов графических представлений, можно увидеть, что до размера M = 10, бинарный и экспоненциальный поиски идут вровень, после же бинарный начинает заметно отставать, а потом и вовсе замедляется. Можно объяснить тем, что бинарный постоянно двигается на единицу влево и тратит логарифмическую единицу операций, в отличие от экспоненциального алгоритма.

Во всех матрицах первой генерации сгенерированных из M++ строк, быстрее всего себя показал линейный алгоритм. Такое время можно обосновать его асимптотикой O(M + N), которая в логарифмическую единицу раз меньше. Но, во второй генерации при некоторых случаях этот алгоритм начал проигрывать по времени, так как target становится равным 16 * 2^^13 + 1 = 131073. Тут линейный не может сразу прийти к началу и ему приходится делать большой обход.
