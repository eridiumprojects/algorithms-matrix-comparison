## Реализация трех алгоритмов
### Бинарный
```    
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
```
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
```
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
```
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
 ```
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
| 1 | 0,005209 | 0,056291 | 0,260834 | 0,002 |
| 2 | 0,006167 | 0,081125 | 0,302667 | 0,001875 |
| 4 | 0,007666 | 0,073666 | 0,471125 | 0,001916 |
| 8 | 0,156083 | 0,136041 | 0,563541 | 0,57323 |
| 16 | 0,149084 | 0,234958 | 0,632958 | 0,189417 |
| 32 | 0,25675 | 0,598583 | 0,728291 | 0,235833 |
| 64 | 0,121166 | 0,631708 | 0,98 | 0,735625 |
| 128 | 0,472 | 0,850292 | 0,622541 | 0,084292 |
| 256 | 0,302875 | 0,460375 | 0,773916 | 0,109125 |
| 512 | 0,029167 | 0,2005 | 0,414708 | 0,003542 |
| 1024 | 0,116 | 0,267125 | 0,287625 | 0,003916 |
| 2048 | 0,394541 | 0,217667 | 0,328375 | 0,394167 |
| 4096 | 1,165709 | 0,939625 | 0,412125 | 0,883042 |
| 8192 | 6,598 | 2,969958 | 1,238791 | 1,951375 |


## Визуализация

### Линейное представление времени работы алгоритмов 
![Linear](https://github.com/pestrikv/algorithms_lab/blob/master/standard_scale.png)

### Логарифмическое представление времени работы алгоритмов 
![Log](https://github.com/pestrikv/algorithms_lab/blob/master/log_scale.png)

## Вывод
#### Исходя из результатов графических представлений, можно увидеть, что до размера M = 10, бинарный и экспоненциальный поиски идут вровень, после же бинарный начинает заметно отставать, а потом и вовсе замедляется. Можно объяснить тем, что бинарный постоянно двигается на единицу влево и тратит логарифмическую единицу операций, в отличие от экспоненциального алгоритма.
#### Во всех матрицах первой генерации сгенерированных из M++ строк, быстрее всего себя показал линейный алгоритм. Такое время можно обосновать его асимптотикой O(M + N), которая в логарифмическую единицу раз меньше. Но, во второй генерации при некоторых случаях этот алгоритм начал проигрывать по времени, так как target становится равным 16 * 2^^13 + 1 = 131073. Тут линейный не может сразу прийти к началу и ему приходится делать большой обход.
